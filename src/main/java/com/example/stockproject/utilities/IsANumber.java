package com.example.stockproject.utilities;

/**
 * Fonction qui permet de tester si la valeur reçue est un nombre ou non
 * 
 * @author admin
 *
 */
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
