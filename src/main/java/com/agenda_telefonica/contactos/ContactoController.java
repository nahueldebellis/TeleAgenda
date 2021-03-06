package com.agenda_telefonica.contactos;

import java.time.LocalDate;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.agenda_telefonica.contactos.Exceptions.EmptyResourcesException;
import com.agenda_telefonica.contactos.Exceptions.FormatException;
import com.agenda_telefonica.contactos.records.JsonContactoPost;


@RestController
@CrossOrigin(origins="*")
public class ContactoController {
	@Autowired
	private ContactoServicio contactServicio;

	@PostMapping(path="/contacto", headers = "content-type=application/json")
	public @ResponseBody String add(@RequestBody JsonContactoPost telefono) throws FormatException {
		return contactServicio.create(telefono).toString();
	}
	
	@GetMapping(path="/contactos")
	public @ResponseBody Iterable<Contacto> list(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "2") int size,
		@RequestParam(name = "name", required = false) String name,
		@RequestParam(name = "provincia", required = false) String provincia,
		@RequestParam(name = "createdFrom", required = false) String createdFrom
	) {
		Pageable pageable = PageRequest.of(page, size);
		return contactServicio.findAllBy(name, provincia, Strings.isEmpty(createdFrom) ? null : LocalDate.parse(createdFrom), pageable);
	}

	@GetMapping(path="/contacto/{name}")
	public Contacto get(@PathVariable String name) {
		try {
			return contactServicio.getByName(name);
		} catch(EmptyResourcesException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
}
