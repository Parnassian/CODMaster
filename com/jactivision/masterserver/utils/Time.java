package com.jactivision.masterserver.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 * @author Parnassian
 *
 */
public class Time {
	private static Calendar cal = Calendar.getInstance();
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * Gets time in HH:mm:ss format
	 * @return time
	 */
	public static String get() {
		return sdf.format(cal.getTime());
	}
}
