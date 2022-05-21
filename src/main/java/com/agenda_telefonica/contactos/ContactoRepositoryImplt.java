package com.agenda_telefonica.contactos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agenda_telefonica.contactos.dict.Contacto_;
import com.agenda_telefonica.contactos.records.FiltroList;


@Repository
public class ContactoRepositoryImplt implements ContactoRepositoryCustom {

	@Autowired
	private EntityManager entity;

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

	/*
	@Override
	public List<Contacto> findBy(FiltroList filtro) {
		CriteriaBuilder builder = entity.getCriteriaBuilder();
		CriteriaQuery<Contacto> criteria = builder.createQuery(Contacto.class);
		Root<Contacto> root = criteria.from(Contacto.class);

		List<Predicate> cond = new ArrayList<>();

		filtro.name().ifPresent(n -> cond.add(builder.like(
			root.get(Contacto_.name), n
		)));

		filtro.provincia().ifPresent(p -> cond.add(builder.like(
			root.get(Contacto_.provincia), p
		)));

		criteria.where((Predicate[]) cond.toArray());
		List<Contacto> contactsResult = entity.createQuery(criteria).getResultList();
		
		return contactsResult;
	}
	*/
}
