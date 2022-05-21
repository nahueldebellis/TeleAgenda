package com.agenda_telefonica.contactos;

import javax.persistence.*;

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

	public Contacto() {}
	
	public Contacto(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public Long getId() {
		return this.id;
	}
}
