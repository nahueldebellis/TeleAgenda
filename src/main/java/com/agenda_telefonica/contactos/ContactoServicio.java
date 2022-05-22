package com.agenda_telefonica.contactos;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agenda_telefonica.contactos.Exceptions.EmptyResourcesException;
import com.agenda_telefonica.contactos.Exceptions.FormatException;
import com.agenda_telefonica.contactos.records.JsonContactoPost;

import formatter.Formatter;
import formatter.TelefonoFormatter;

@Service
public class ContactoServicio {
	@Autowired
	private ContactoRepository contactRepository;

	public Long create(JsonContactoPost contacto) throws FormatException {
		if(Strings.isEmpty(contacto.name()) || Strings.isEmpty(contacto.phone()))
			throw new IllegalArgumentException("cuerpo incorrecto. El formato es {name: \"\", phone: \"\"}");

		Formatter<String> telefonoFormat = new TelefonoFormatter();

		Contacto t = new Contacto(
			contacto.name(),
			telefonoFormat.format(contacto.phone()),
			contacto.codArea().orElse("")
		);

		this.contactRepository.save(t);
		return t.getId();
	}

	public List<Contacto> findAllBy(String name, String provincia, LocalDate createdAt, Pageable pageable) {
		return contactRepository.findBy(name, provincia, createdAt, pageable);
	}

	public Contacto getByName(String name) throws EmptyResourcesException {
		return contactRepository.findByName(name).orElseThrow(() -> new EmptyResourcesException("No existe el contacto"));
	}
}
