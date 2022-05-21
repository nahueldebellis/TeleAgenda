package com.agenda_telefonica.contactos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.agenda_telefonica.contactos.ContactoServicio;

public class ContactServicioTest {

	@Test
	public void returnAWellParsedNumber() throws Exception {
		String phoneTest = "ABABGTHOEX";
		String phoneExpected = "2222484639";
		assertEquals(ContactoServicio.parseLettersToNumbers(phoneTest), phoneExpected);
	}
	
	@Test
	public void formatFirstCaseCodeArea() throws Exception {
		String phoneTest = "2222484639";
		String phoneExpected = "22 2248-4639";

		assertEquals(phoneExpected, ContactoServicio.formatNumber(phoneTest));
	}

	@Test
	public void formatSecondCaseCodeArea() throws Exception {
		String phoneTest = "22484639";
		String phoneExpected = "2248-4639";

		assertEquals(phoneExpected, ContactoServicio.formatNumber(phoneTest));
	}

	@Test
	public void validateFormatOtherPlace() throws Exception {
		String phoneTest = "1222484639";
		String phoneExpected = "122 248-4639";

		assertEquals(phoneExpected, ContactoServicio.formatNumber(phoneTest));
	}

	@Test
	public void throwErrorForBadInput() throws Exception {
		String phoneTest = "    nannsnsna----:'[]";

		assertThrowsExactly(Exception.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				ContactoServicio.formatNumber(phoneTest);
				
			}
		});
	}
}
