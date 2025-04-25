package com.thegoldenbook.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	
	public static final Date getDate(int ano, int mes, int dia) {
		
		Calendar c = new GregorianCalendar(ano, mes, dia);
		return c.getTime();
	}
	
	public static final Date getDateTime (int ano, int mes, int dia, int hora, int min, int sec) {
		
		Calendar c = new GregorianCalendar(ano, mes, dia, hora, min, sec);
		
		return c.getTime();
	}
}