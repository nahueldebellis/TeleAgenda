package com.agenda_telefonica.contactos.Exceptions;

/**
 * Excepcion para indicar que en la cadena de Rules algo no salio como esperaba
 * @author debel
 *
 */
public class FormatException extends Exception {
	public FormatException(String msg) {
		super(msg);
	}
}
