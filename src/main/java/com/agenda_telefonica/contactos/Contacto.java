package com.agenda_telefonica.contactos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa un registro de la agenda de telefonos
 */
@Entity
@Table(name="Contacto")
public class Contacto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

	public String name;

	@Column(name="phone_number")
	public String phoneNumber;
	
	@Column(name = "provincia")
	public String provincia;
	
	@Column(name = "created_at")
	public LocalDate createdAt;

	public Contacto() {}
	
	public Contacto(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.provincia = "";
		this.createdAt = LocalDate.now();
	}
	
	public Long getId() {
		return this.id;
	}
}
