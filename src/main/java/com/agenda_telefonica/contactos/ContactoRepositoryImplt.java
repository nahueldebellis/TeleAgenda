package com.agenda_telefonica.contactos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.agenda_telefonica.contactos.dict.Contacto_;
import com.agenda_telefonica.contactos.records.FiltroList;


@Service
public class ContactoRepositoryImplt implements ContactoRepositoryCustom {

	@Autowired
	private EntityManager entity;

	public ContactoRepositoryImplt(EntityManager em) {
		this.entity = em;
	}

	@Override
	public Optional<Contacto> findByName(String name) {
		CriteriaBuilder builder = entity.getCriteriaBuilder();
		CriteriaQuery<Contacto> criteria = builder.createQuery(Contacto.class);
		Root<Contacto> root = criteria.from(Contacto.class);
		
		Predicate nameCondition = builder.equal(
			root.get(Contacto_.name), name
		);

		criteria.where(nameCondition);
		Contacto contactResult = entity.createQuery(criteria).getSingleResult();
		
		return Optional.ofNullable(contactResult);
	}
}
