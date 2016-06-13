package com.giffgaff.dojo;

import com.giffgaff.dojo.util.CalculatorUtil;
import com.giffgaff.dojo.util.Validator;

public class CalcDateTool {
	private static String DAYS_BETWEEN = "There are [%d] days between [%s] and [%s].";
	
	public static void main(String args[]) {
		
		String validInput = Validator.isValidInput(args);
		if (validInput != null) {
			System.out.println(validInput);
		} else {
			System.out.println(String.format(DAYS_BETWEEN, CalculatorUtil.daysBetween(args[0], args[1]), args[0], args[1]));
		}
	}
}
