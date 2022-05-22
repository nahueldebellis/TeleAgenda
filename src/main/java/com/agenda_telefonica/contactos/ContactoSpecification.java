package com.agenda_telefonica.contactos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import com.agenda_telefonica.contactos.dict.Contacto_;

public final class ContactoSpecification {
	public static Specification<Contacto> findByName(String name) {
		return (Root<Contacto> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
			return builder.equal(root.get(Contacto_.name), name);
		};
	}

	public static Specification<Contacto> findBy(String name, String provincia, LocalDate createdAt) {
		return (Root<Contacto> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
			List<Predicate> cond = new ArrayList<>();

			if(!Strings.isEmpty(name)) {
				cond.add(builder.like(
					root.get(Contacto_.name), "%"+name+"%"
				));
			}

			if(!Strings.isEmpty(provincia)) {
				cond.add(builder.like(
					root.get(Contacto_.provincia), "%"+provincia+"%"
				));
			}

			if(createdAt != null) {
				cond.add(builder.greaterThan(
					root.get(Contacto_.createdAt), createdAt
				));
			}

			return builder.and(cond.toArray(new Predicate[cond.size()]));
		};
	}
}
