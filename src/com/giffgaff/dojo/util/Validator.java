package com.giffgaff.dojo.util;

public class Validator {
	public static String USAGE_STRING = "Usage: CalcDateTool DD/MM/YY[YY] DD/MM/YY[YY]\n" +
										"\tMax day value is 31\n" +
										"\tMax month value is 12\n" +
									    "\tFour digit years must begin with either 1 or 2.\n" +
										"\tTwo digit years are assumed in the 21st century. 04 become 2004";
	public static String INVALID_ARGS = "Invalid number of arguments."; 
	public static String INVALID_DATE_FORMAT = "Date entered [%s] is not in valid format";
	public static String INVALID_DATE = "Date entered [%s] is not valid";
	public static String NEWLINE = "\n";
	
	/**
	 * Validates the user inputs as:
	 * <ol>
	 * <li>There must be 2 arguments</li>
	 * <li>Every argument entered is a valid date format according to {@linkplain CalculatorUtil#isValidDateFormat(String)}</li> 
	 * </ol>
	 * 
	 * <b>Note:</b></br> 
	 * It will not check if the date is meaningful. </br>
	 * e.g. 30/02/1999 and 31/04/2004 is valid according to this function as this is just checking the format.</br></br>
	 * 
	 * @param args User inputs to validate
	 * @return <code>null</code> if the arguments are valid, else an error message detailing the error.
	 */
	public static String isValidInput(String args[]) {
		String errorMsg = null;
		
		if (args.length != 2) {
			errorMsg = INVALID_ARGS + NEWLINE + USAGE_STRING;
		} else {
			for (String arg : args) {
				if (!CalculatorUtil.isValidDateFormat(arg)) {
					errorMsg = String.format(INVALID_DATE_FORMAT, arg) + NEWLINE + USAGE_STRING;
					break;
				} else if (CalculatorUtil.isValidDate(arg) != null) {
					errorMsg = String.format(INVALID_DATE, arg) + NEWLINE + USAGE_STRING;;
				}
			}
		}
		
		return errorMsg;
	}
}
