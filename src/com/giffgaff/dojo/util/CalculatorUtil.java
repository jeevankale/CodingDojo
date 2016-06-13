package com.giffgaff.dojo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.giffgaff.dojo.DojoDate;

public class CalculatorUtil {

	public static Map<Integer, Integer> MAX_ALLOWED_DAYS = new HashMap<>();
	public static String INVALID_DAYS = "Month [%d] in the year [%d] can have maximum [%d] days.";
	public static Integer MAX_FEB_DAYS = 28;
	public static Integer MAX_LEAP_FEB_DAYES = 29; 
	
	static {
		MAX_ALLOWED_DAYS.put(1, 31);
		// February can be 28 or 29 depending on if its a leap year
		MAX_ALLOWED_DAYS.put(3, 31);
		MAX_ALLOWED_DAYS.put(4, 30);
		MAX_ALLOWED_DAYS.put(5, 31);
		MAX_ALLOWED_DAYS.put(6, 30);
		MAX_ALLOWED_DAYS.put(7, 31);
		MAX_ALLOWED_DAYS.put(8, 31);
		MAX_ALLOWED_DAYS.put(9, 30);
		MAX_ALLOWED_DAYS.put(10, 31);
		MAX_ALLOWED_DAYS.put(11, 30);
		MAX_ALLOWED_DAYS.put(12, 31);
	}
	
	/**
	 * Check to see if the year passed in is a leap year.
	 * @param year to be checked
	 * @return <code>true</code> / <code>false</code>
	 */
	public static boolean isLeapYear(int year) {
		if (year % 400 == 0 || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Make sure if the date entered is in the valid format.</br>
	 * Valid date formats are : DD/MM/YY, D/M/YY, DD/MM/YYYY. </br>
	 * Date and Months can be 1-2 digits and Year can be 2 or 4 digits.</br>
	 * Allows max value for Date as 31, Month as 12 and Year to be 2 or 4 digits.</br>
	 * For 4 digit year, it has to start with either 1 or 2.</br></br>
	 *  
	 * @param date to validate
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public static boolean isValidDateFormat(String date) {
		Pattern pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/](0?[1-9]|1[012])[\\/]([12]\\d{3}|\\d{2})$");
		Matcher matcher = pattern.matcher(date);
		
		if (matcher.matches()) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Verify the max days allowed for the month.</br></br>
	 * Before calling this function call {@linkplain #isValidDateFormat(String)}.</br></br>
	 * 
	 * @param date to validate
	 * @return <code>true</code> or <code>false</code>.
	 */
	public static String isValidDate(String date) {
		String errorMsg = null;
		
		String[] dateFragments = date.split("/");
		int days = Integer.valueOf(dateFragments[0]); 
		int month = Integer.valueOf(dateFragments[1]);
		int year = Integer.valueOf(makeYearFourDigit(dateFragments[2]));
		
		// February needs checking for leap year
		if (month == 2) {
			if ((isLeapYear(year) && days > MAX_LEAP_FEB_DAYES) || (!isLeapYear(year) && days > MAX_FEB_DAYS)) {
				return String.format(INVALID_DAYS, month, year, days);
			}
		} else {
			if (days > MAX_ALLOWED_DAYS.get(month)) {
				return String.format(INVALID_DAYS, month, year, days);
			}
		}
		
		return errorMsg;
	}
	
	
	/**
	 * If the year is 2 digit make this into 4 digit by pre-pending 20 to it.</br>
	 * e.g. if year = "04", "2004" will be returned.</br>
	 * Else, return back the year as it is. </br></br>
	 * Before calling this function call {@linkplain #isValidDateFormat(String)}.</br></br>
	 * 
	 * @param year 
	 * @return the 4 digit year if its only 2 digit.
	 */
	public static String makeYearFourDigit(String year) {
		if (year.length() == 2) {
			return "20" + year;
		}
		
		return year;
	}
	
	
	public static int daysBetween(String date1, String date2) {
		int daysBetween = 0;
		boolean reverseDates = false; // Flag to say if the dates were supplied with bigger date followed by smaller
		DojoDate fromDate = new DojoDate(date1);
		DojoDate toDate = new DojoDate(date2);
		
		if (fromDate.isAfter(toDate)) {
			fromDate = new DojoDate(date2);
			toDate = new DojoDate(date1);
			reverseDates = true;
		} 
		
		while (!fromDate.equals(toDate)) {
			fromDate = fromDate.addDay();
			daysBetween++;
		}
		
		return (reverseDates ? (-1 * daysBetween) : daysBetween);
	}
}
