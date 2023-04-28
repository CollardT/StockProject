package com.example.stockproject.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateNewDate {
	static public String createNewDate() {
		String pattern = "yyyyMMddHHmmss";
		DateFormat df = new SimpleDateFormat(pattern);
		Date today = Calendar.getInstance().getTime();
		String todayAsString = df.format(today);
		return todayAsString;
	}
}
