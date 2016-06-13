package com.giffgaff.dojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestDojoDate {
	
	
	@Test
	public void testIsDateAfter() {
		assertTrue("Date after", new DojoDate("29/04/1978").isAfter(new DojoDate("28/04/1978")));
		assertTrue("Date after", new DojoDate("29/05/1978").isAfter(new DojoDate("28/04/1978")));
		assertTrue("Date after", new DojoDate("29/04/1980").isAfter(new DojoDate("28/04/1978")));
		
		assertFalse("Date after", new DojoDate("29/04/1978").isAfter(new DojoDate("29/04/1978")));
		assertFalse("Date after", new DojoDate("29/04/1978").isAfter(new DojoDate("30/04/1978")));
		assertFalse("Date after", new DojoDate("29/04/1978").isAfter(new DojoDate("29/10/1978")));
		assertFalse("Date after", new DojoDate("29/04/1977").isAfter(new DojoDate("29/04/1978")));
	}
	
	
	@Test
	public void testEqualTo() {
		assertTrue("Date Equal", new DojoDate("29/04/1978").equals(new DojoDate("29/04/1978")));
		
		assertFalse("Date Equal", new DojoDate("30/04/1978").equals(new DojoDate("29/04/1978")));
		assertFalse("Date Equal", new DojoDate("28/04/1978").equals(new DojoDate("29/04/1978")));
		assertFalse("Date Equal", new DojoDate("29/05/1978").equals(new DojoDate("29/04/1978")));
		assertFalse("Date Equal", new DojoDate("29/04/1979").equals(new DojoDate("29/04/1978")));
	}
	
	
	@Test
	public void testAddDay() {
		DojoDate date = new DojoDate("27/02/2004");
		
		assertEquals("Added date", "28/02/2004", date.addDay().getDate());
		assertEquals("Added date", "29/02/2004", date.addDay().getDate());
		assertEquals("Added date", "01/03/2004", date.addDay().getDate());
		
		date = new DojoDate("28/02/2001");
		assertEquals("Added date", "01/03/2001", date.addDay().getDate());
		assertEquals("Added date", "02/03/2001", date.addDay().getDate());
		
		date = new DojoDate("29/05/2003");
		assertEquals("Added date", "30/05/2003", date.addDay().getDate());
		assertEquals("Added date", "31/05/2003", date.addDay().getDate());
		assertEquals("Added date", "01/06/2003", date.addDay().getDate());
		
		date = new DojoDate("29/12/2003");
		assertEquals("Added date", "30/12/2003", date.addDay().getDate());
		assertEquals("Added date", "31/12/2003", date.addDay().getDate());
		assertEquals("Added date", "01/01/2004", date.addDay().getDate());
	}
}
