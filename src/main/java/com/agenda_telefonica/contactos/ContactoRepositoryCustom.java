package com.agenda_telefonica.contactos;

import java.util.List;
import java.util.Optional;

import com.agenda_telefonica.contactos.records.FiltroList;

public interface ContactoRepositoryCustom {
	public Optional<Contacto> findByName(String name);
	//public List<Contacto> findBy(FiltroList filtro);
}
