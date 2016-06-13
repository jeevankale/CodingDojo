package com.giffgaff.dojo;

import com.giffgaff.dojo.util.CalculatorUtil;

public class DojoDate {

	private int day;
	private int month;
	private int year;
	
	public DojoDate(String date) {
		String[] dateFragments = date.split("/");
		day = Integer.valueOf(dateFragments[0]);
		month = Integer.valueOf(dateFragments[1]);
		year = Integer.valueOf(dateFragments[2]);
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
	
	/**
	 * Check if the current date is after the date provided.
	 * @param compareTo Compare with this
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean isAfter(DojoDate compareTo) {
		if (year > compareTo.getYear()) {
			return true;
		} else if (month > compareTo.getMonth() && year >= compareTo.getYear()) {
			return true;
		} else if (day > compareTo.getDay() && month >= compareTo.getMonth() && year >= compareTo.getYear()) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Checks to see if current date is same as the date provided.
	 * @param compareTo Check with this
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean equals(DojoDate compareTo) {
		if (year == compareTo.getYear() && month == compareTo.getMonth() && day == compareTo.getDay()) {
			return true;
		}
		
		return false;
	}
	
	
	public DojoDate addDay() {
		day++;
		
		if ((month == 2 && 
			((CalculatorUtil.isLeapYear(year) && day > CalculatorUtil.MAX_LEAP_FEB_DAYES) ||
			((!CalculatorUtil.isLeapYear(year) && day > CalculatorUtil.MAX_FEB_DAYS)))) ||
			(month != 2 && day > CalculatorUtil.MAX_ALLOWED_DAYS.get(month))) {
			
			day = 1;
			addMonth();
		}
		
		return this;
	}
	
	
	private void addMonth() {
		month++;
		
		if (month > 12) {
			month = 1;
			addYear();
		}
	}
	
	private void addYear() {
		year++;
	}
	
	
	/**
	 * @return DD/MM/YYYY date
	 */
	public String getDate() {
		String stDay = String.valueOf(day);
		String stMonth = String.valueOf(month);
		String stYear = String.valueOf(year);
		
		stDay = stDay.length() == 1 ? "0"+stDay : stDay;
		stMonth = stMonth.length() == 1 ? "0"+stMonth : stMonth;
		stYear = stYear.length() == 2 ? "20"+stYear : stYear;
		
		return stDay + "/" + stMonth + "/" + stYear;
	}
}
