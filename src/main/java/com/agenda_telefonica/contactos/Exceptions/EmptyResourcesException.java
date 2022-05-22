package com.agenda_telefonica.contactos.Exceptions;

/**
 * Exception para indicar que no existe el recurso que se esta buscando
 * @author debel
 *
 */
public class EmptyResourcesException extends Exception {
	public EmptyResourcesException(String msg) {
		super(msg);
	}
}
