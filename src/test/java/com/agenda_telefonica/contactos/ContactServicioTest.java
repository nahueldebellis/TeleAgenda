package com.agenda_telefonica.contactos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.agenda_telefonica.contactos.Exceptions.FormatException;

import formatter.GroupRule;
import formatter.LettersRule;

public class ContactServicioTest {

	@Test
	public void returnAWellParsedNumberWithFirstAndLastElementAsLetters() throws Exception {
		String phoneTest = "A123456K";
		String phoneExpected = "2123-4565";
		assertEquals(new GroupRule().run(new LettersRule().run(phoneTest)), phoneExpected);
	}

	@Test
	public void returnAWellParsedNumber() throws Exception {
		String phoneTest = "ABGTHOEX";
		String phoneExpected = "2248-4639";
		assertEquals(new GroupRule().run(new LettersRule().run(phoneTest)), phoneExpected);
	}
	
	@Test
	public void formatOnlyMatch() throws Exception {
		String phoneTest = "22484639";
		String phoneExpected = "2248-4639";

		assertEquals(phoneExpected, new GroupRule().run(phoneTest));
	}
	
	@Test
	public void throwErrorForBadInput() throws Exception {
		String phoneTest = "    nannsnsna----:'[]";

		assertThrowsExactly(FormatException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				new GroupRule().run(phoneTest);
			}
		});
	}

	@Test
	public void throwErrorForMiddleSpace() throws Exception {
		String phoneTest = "12341234 1343";

		assertThrowsExactly(FormatException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				new GroupRule().run(phoneTest);
			}
		});
	}

	@Test
	public void throwErrorForSpaces() throws Exception {
		String phoneTest = " 11233456";

		assertThrowsExactly(FormatException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				new GroupRule().run(phoneTest);
			}
		});
	}
}
