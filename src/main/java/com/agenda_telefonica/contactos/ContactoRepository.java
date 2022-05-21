package com.agenda_telefonica.contactos;

import org.springframework.data.repository.CrudRepository;

public interface ContactoRepository extends CrudRepository<Contacto, Long>, ContactoRepositoryCustom { 
}
