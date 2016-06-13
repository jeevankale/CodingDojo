package com.giffgaff.dojo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCalculatorUtil {

	/**
	 * Tests {@linkplain CalculatorUtil#isLeapYear(int)}.
	 */
	@Test
	public void testLeapYear() {
		assertTrue(CalculatorUtil.isLeapYear(0));
		assertTrue(CalculatorUtil.isLeapYear(1600));
		assertTrue(CalculatorUtil.isLeapYear(2000));
		assertTrue(CalculatorUtil.isLeapYear(2004));

		assertFalse(CalculatorUtil.isLeapYear(1999));
		assertFalse(CalculatorUtil.isLeapYear(1900));
		assertFalse(CalculatorUtil.isLeapYear(1700));
		assertFalse(CalculatorUtil.isLeapYear(1800));
	}
	
	
	/**
	 * Tests {@linkplain CalculatorUtil#isValidDateFormat(String)}.
	 */
	@Test
	public void testValidDateFormat() {
		assertTrue(CalculatorUtil.isValidDateFormat("1/2/1200"));
		assertTrue(CalculatorUtil.isValidDateFormat("1/12/2100"));
		assertTrue(CalculatorUtil.isValidDateFormat("31/3/2004"));
		assertTrue(CalculatorUtil.isValidDateFormat("13/06/1200"));
		assertTrue(CalculatorUtil.isValidDateFormat("1/01/1200"));
		assertTrue(CalculatorUtil.isValidDateFormat("1/1/11"));
		assertTrue(CalculatorUtil.isValidDateFormat("11/11/11"));
		assertTrue(CalculatorUtil.isValidDateFormat("30/09/04"));
		
		assertFalse(CalculatorUtil.isValidDateFormat("32/3/1970"));
		assertFalse(CalculatorUtil.isValidDateFormat("21/13/2000"));
		assertFalse(CalculatorUtil.isValidDateFormat("1/2/3000"));
		assertFalse(CalculatorUtil.isValidDateFormat("1/2/4200"));
		assertFalse(CalculatorUtil.isValidDateFormat("1/23/99"));
		assertFalse(CalculatorUtil.isValidDateFormat("35/3/42"));
		assertFalse(CalculatorUtil.isValidDateFormat("00/6/2004"));
		assertFalse(CalculatorUtil.isValidDateFormat("5/3/4"));
		assertFalse(CalculatorUtil.isValidDateFormat("16/6/004"));
	}
	
	
	/**
	 * Tests {@linkplain CalculatorUtil#isValidDate(String)}.
	 */
	@Test
	public void testValidDate() {
		assertNull("Date", CalculatorUtil.isValidDate("29/02/2000"));
		assertNull("Date", CalculatorUtil.isValidDate("29/02/04")); // Handles the 2 digit year as well
		assertNull("Date", CalculatorUtil.isValidDate("31/01/2013"));
		assertNull("Date", CalculatorUtil.isValidDate("20/05/2014"));
		assertNull("Date", CalculatorUtil.isValidDate("01/07/2014"));
		assertNull("Date", CalculatorUtil.isValidDate("30/11/2011"));
		
		assertEquals("Date", String.format(CalculatorUtil.INVALID_DAYS, 2, 2000, 30), CalculatorUtil.isValidDate("30/02/2000"));
		assertEquals("Date", String.format(CalculatorUtil.INVALID_DAYS, 2, 1999, 29), CalculatorUtil.isValidDate("29/02/1999")); // Non-leap year
		assertEquals("Date", String.format(CalculatorUtil.INVALID_DAYS, 4, 2013, 31), CalculatorUtil.isValidDate("31/04/2013"));
		assertEquals("Date", String.format(CalculatorUtil.INVALID_DAYS, 11, 2011, 31), CalculatorUtil.isValidDate("31/11/2011"));
	}

	
	@Test
	public void testDaysBetween() {
		assertEquals("Days between the dates", 54, CalculatorUtil.daysBetween("31/03/2001", "24/5/2001"));
		assertEquals("Days between the dates", -54, CalculatorUtil.daysBetween("24/5/2001", "31/03/2001"));
		assertEquals("Days between the dates", 3, CalculatorUtil.daysBetween("30/12/2001", "2/01/2002"));
		assertEquals("Days between the dates", 9, CalculatorUtil.daysBetween("26/02/2004", "06/03/2004"));
		assertEquals("Days between the dates", 5, CalculatorUtil.daysBetween("26/09/2004", "01/10/2004"));
		assertEquals("Days between the dates", 48, CalculatorUtil.daysBetween("12/02/2001", "01/04/2001"));
		assertEquals("Days between the dates", 742, CalculatorUtil.daysBetween("20/12/2001", "01/01/2004"));
		assertEquals("Days between the dates", -737, CalculatorUtil.daysBetween("01/01/2004", "25/12/2001"));
	}
}
