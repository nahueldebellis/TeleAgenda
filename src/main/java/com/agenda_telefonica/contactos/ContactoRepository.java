package com.agenda_telefonica.contactos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ContactoRepository extends CrudRepository<Contacto, Long>, ContactoRepositoryCustom, JpaSpecificationExecutor<Contacto> {
	default List<Contacto> findBy(String name, String provincia, LocalDate createdAt, Pageable pageable) {
		Page<Contacto> pageData = findAll(ContactoSpecification.findBy(name, provincia, createdAt), pageable);
		return pageData.getContent();
	}
}
