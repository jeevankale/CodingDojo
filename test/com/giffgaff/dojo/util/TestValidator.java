package com.giffgaff.dojo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestValidator {
	
	@Test
	public void testInvalidNumberOfArguments() {
		String expectedError = Validator.INVALID_ARGS + Validator.NEWLINE + Validator.USAGE_STRING;
		
		assertEquals("Arguments", expectedError, Validator.isValidInput(new String[]{"12/12/2000"}));
		
		assertEquals("Arguments", expectedError, Validator.isValidInput(new String[]{"12/12/2000", "02/02/2003", "12/09/06"}));
	}
	
	
	@Test
	public void testInvalidDateFormat() {
		String expectedError = Validator.INVALID_DATE_FORMAT + Validator.NEWLINE + Validator.USAGE_STRING;
		
		String invalidDate = "dd/mm/yyyy";
		assertEquals("Date", String.format(expectedError, invalidDate), Validator.isValidInput(new String[]{invalidDate, "12/12/2000"}));

		invalidDate = "35/4/2000";
		assertEquals("Date", String.format(expectedError, invalidDate), Validator.isValidInput(new String[]{"12/12/2000", invalidDate}));
		
		invalidDate = "23/15/2000";
		assertEquals("Date", String.format(expectedError, invalidDate), Validator.isValidInput(new String[]{invalidDate, "12/12/2000"}));
		
		invalidDate = "23/15/3000";
		assertEquals("Date", String.format(expectedError, invalidDate), Validator.isValidInput(new String[]{"12/12/2000", invalidDate}));
	}
	
	
	@Test
	public void testInvalidDate() {
		String expectedError = Validator.INVALID_DATE + Validator.NEWLINE + Validator.USAGE_STRING;
		
		String invalidDate = "31/04/2004";
		assertEquals("Date", String.format(expectedError, invalidDate), Validator.isValidInput(new String[]{"30/06/1999", invalidDate}));
		
		invalidDate = "30/02/1999";
		assertEquals("Date", String.format(expectedError, invalidDate), Validator.isValidInput(new String[]{invalidDate, "31/12/2004"}));
	}
	
	
	@Test
	public void testValidInput() {
		assertNull(Validator.isValidInput(new String[]{"10/01/1999", "15/02/1999"}));
		assertNull(Validator.isValidInput(new String[]{"10/01/1999", "15/02/1999"}));
	}
}
