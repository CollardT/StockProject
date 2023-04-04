package com.example.stockproject.utilities;

public class IsANumber {
	static public boolean isANumber(String str) {
		try {
			int testValue;
			testValue = Integer.parseInt(str);
		    return true;
		} catch (NumberFormatException e) {
		    return false;
		}
	}
}
