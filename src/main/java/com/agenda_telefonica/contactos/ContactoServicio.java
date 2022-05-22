package com.agenda_telefonica.contactos;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agenda_telefonica.contactos.records.FiltroList;
import com.agenda_telefonica.contactos.records.JsonContactoPost;

@Service
public class ContactoServicio {

	@Autowired
	private ContactoRepository contactRepository;

	public Long create(JsonContactoPost telefono) {
		try {
			if(Strings.isEmpty(telefono.name()) || Strings.isEmpty(telefono.phone()))
				throw new IllegalArgumentException("cuerpo incorrecto. El formato es {name: \"\", phone: \"\"}");

			Contacto t = new Contacto(
				telefono.name(),
				ContactoServicio.formatNumber(ContactoServicio.parseLettersToNumbers(telefono.phone()))
			);

			this.contactRepository.save(t);
			return t.getId();
		} catch (Exception e) {
			throw new IllegalArgumentException("El numero de telefono esta mal formado.");
		}
	}

	public Iterable<Contacto> findAll(int pageable) {
		return contactRepository.findAll();
	}

	public List<Contacto> findAllBy(String name, String provincia, LocalDate createdAt, Pageable pageable) {
		return contactRepository.findBy(name, provincia, createdAt, pageable);
	}

	public Contacto getByName(String name) throws Exception {
		return contactRepository.findByName(name).orElseThrow(() -> new Exception("No existe el contacto"));
	}

	/**
	 * 
	 * primer aproach remplazar cada uno por su valor
	 * @param phoneLetter
	 * @return
	 */
	public static String parseLettersToNumbers(String phoneLetter) {
		String temporalFormat = phoneLetter.replaceAll("A|B|C", "2");
		temporalFormat = temporalFormat.replaceAll("D|E|F", "3");
		temporalFormat = temporalFormat.replaceAll("G|H|I", "4");
		temporalFormat = temporalFormat.replaceAll("J|K|L", "5");
		temporalFormat = temporalFormat.replaceAll("M|N|O", "6");
		temporalFormat = temporalFormat.replaceAll("P|Q|R|S", "7");
		temporalFormat = temporalFormat.replaceAll("T|U|V", "8");
		temporalFormat = temporalFormat.replaceAll("W|X|Y|Z", "9");

		return temporalFormat;
	}
	
	/**
	 * Creo una expresion regular para matchear el caso en el que viene el codigo de area
	 * y otra para cuando no lo tenga.
	 * Si tiene codigo de area el numero lo formateo de la siguiente forma:
	 * <codigoArea> <primerosCuatroNumeros>-<ultimosCuatroNumeros>
	 * Si no lo tiene lo formateo asi:
	 * <primerosCuatroNumeros>-<ultimosCuatroNumeros>
	 * Esto funciona por ahora, pero para usar codigo de area de otras provincias necesito hacer un helper
	 * @param phone
	 * @return
	 */
	public static String formatNumber(String phone) throws Exception {
		Pattern withAreaCode = Pattern.compile("(\\d{2})(\\d{4})(\\d{4})");
		Pattern onlyNumber = Pattern.compile("(\\d{4})(\\d{4})");

		Matcher matchAreaCode = withAreaCode.matcher(phone);
		Matcher matchNumber = onlyNumber.matcher(phone);

		if(matchAreaCode.find())
			return matchAreaCode.group(1)+" "+matchAreaCode.group(2)+"-"+matchAreaCode.group(3);
		else if(matchNumber.find())
			return matchNumber.group(1)+"-"+matchNumber.group(2);

		throw new Exception("numero mal formado");//throw new BadNumberException();
	}
}
