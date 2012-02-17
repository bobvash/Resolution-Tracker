package com.bobvash.resolutiontracker.client;

import java.util.Date;

public class ResolutionUtils {

	public static String convertDateToKey(Date date){
		return "" + (date.getYear() + 1900) + " " + (date.getMonth() + 1) + " " + date.getDate();
	}
}
