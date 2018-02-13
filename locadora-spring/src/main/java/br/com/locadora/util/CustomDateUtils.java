package br.com.locadora.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

@Component
public abstract class CustomDateUtils extends DateUtils {
	
	public static final String HOUR_MASK = "HH:mm";

	public static final String HOUR_COMPLETE_MASK = CustomStringUtils.generateStringBuilderToString(HOUR_MASK,":ss");
	
	public static final String SLASH_DATE_MASK = "dd/MM/yyyy";
	
	public static final String DASH_DATE_MASK = "yyyy-MM-dd";
	
	public static final String SLASH_DATETIME_MASK = CustomStringUtils.generateStringBuilderToString(SLASH_DATE_MASK," ",HOUR_MASK);

	public static final String DASH_DATETIME_MASK = CustomStringUtils.generateStringBuilderToString(DASH_DATE_MASK," ",HOUR_MASK);
	
	public static final String SLASH_DATETIME_COMPLETE_MASK = CustomStringUtils.generateStringBuilderToString(SLASH_DATE_MASK," ",HOUR_COMPLETE_MASK);

	public static final String DASH_DATETIME_COMPLETE_MASK = CustomStringUtils.generateStringBuilderToString(DASH_DATE_MASK," ",HOUR_COMPLETE_MASK);
	
	
	
	
	/**
	 * Converte um objeto do tipo Date para String com acompanhamento de mascara
	 * @param date
	 * @param mask
	 * @return String(?)
	 */
	public static String formateDateToString(Date date, String mask) {
		if(mask != null) {
			try {
				return new SimpleDateFormat(identifyMaskDate(mask)).format(date);
			}catch(IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Converte uma String com acompanhamento de mascara para um objeto do tipo Date
	 * @param dateString
	 * @param mask
	 * @return Date
	 */
	public static Date convertStringDateToDateType(String dateString, String mask) {
		if(mask != null) {
			try {
				return new SimpleDateFormat(identifyMaskDate(mask)).parse(dateString);
			}catch(ParseException | IllegalArgumentException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Converte um objeto do tipo Date para String com formatação em barras
	 * @param date
	 * @return String("dd/MM/yyyy")
	 */
	public static String formateDateSlashToString(Date date) {
		return formateDateToString(date, SLASH_DATE_MASK);
	}
	/**
	 * Converte um objeto do tipo Date para String com formatação em traços
	 * @param date
	 * @return String("yyyy-MM-dd")
	 */
	public static String formateDateDashToString(Date date) {
		return formateDateToString(date,DASH_DATE_MASK);
	}
	
	/**
	 * Converte um objeto do tipo Date para String com formatação em barras com horas
	 * @param date
	 * @return String("dd/MM/yyyy")
	 */
	public static String formateDateTimeSlashToString(Date dateTime) {
		return formateDateToString(dateTime, SLASH_DATETIME_MASK);
	}
	/**
	 * Converte um objeto do tipo Date para String com formatação em traços
	 * @param date
	 * @return String("yyyy-MM-dd")
	 */
	public static String formateDateTimeDashToString(Date dateTime) {
		return formateDateToString(dateTime,DASH_DATETIME_MASK);
	}
	
	/**
	 * Converte um objeto do tipo Date para String com formatação em barras com horas
	 * @param date
	 * @return String("dd/MM/yyyy")
	 */
	public static String formateDateTimeCompleteSlashToString(Date dateTime) {
		return formateDateToString(dateTime, SLASH_DATETIME_COMPLETE_MASK);
	}
	/**
	 * Converte um objeto do tipo Date para String com formatação em traços
	 * @param date
	 * @return String("yyyy-MM-dd")
	 */
	public static String formateDateTimeCompleteDashToString(Date dateTime) {
		return formateDateToString(dateTime,DASH_DATETIME_COMPLETE_MASK);
	}
	
	
	/**
	 * Converte uma String com formatação em barras para um objeto do tipo Date
	 * @param dateString
	 * @return Date
	 */
	public static Date convertStringDateSlashToDateType(String dateString) {
		return convertStringDateToDateType(dateString,SLASH_DATE_MASK);
	}
	
	/**
	 * Converte uma String com formatação em traços para um objeto do tipo Date
	 * @param dateString
	 * @return Date
	 */
	public static Date convertStringDateDashToDateType(String dateString) {
		return convertStringDateToDateType(dateString,DASH_DATE_MASK);
	}
	
	/**
	 * Converte uma String com formatação em barras para um objeto do tipo Date
	 * @param dateString
	 * @return Date
	 */
	public static Date convertStringDateTimeSlashToDateType(String dateTimeString) {
		return convertStringDateToDateType(dateTimeString,(
				isDateTimeCompleteLength(dateTimeString))?SLASH_DATETIME_COMPLETE_MASK:SLASH_DATETIME_MASK);
	}
	
	/**
	 * Converte uma String com formatação em traços para um objeto do tipo Date
	 * @param dateString
	 * @return Date
	 */
	public static Date convertStringDateTimeDashToDateType(String dateTimeString) {
		return convertStringDateToDateType(dateTimeString,
				(isDateTimeCompleteLength(dateTimeString))?DASH_DATETIME_COMPLETE_MASK:DASH_DATETIME_MASK);
	}
	
	/**
	 * Identifica a mascara inserida e a retorna caso a encontra
	 * @param mask
	 * @return String
	 * @throws IllegalArgumentException
	 */
	public static String identifyMaskDate(String mask) throws IllegalArgumentException {
		if(SLASH_DATE_MASK.equalsIgnoreCase(mask)) {
			return SLASH_DATE_MASK;
		}else if(DASH_DATE_MASK.equalsIgnoreCase(mask)) {
			return DASH_DATE_MASK;
		}else if(SLASH_DATETIME_MASK.equalsIgnoreCase(mask)) {
			return SLASH_DATETIME_MASK;
		}else if(DASH_DATETIME_MASK.equalsIgnoreCase(mask)) {
			return DASH_DATETIME_MASK;
		}else if(SLASH_DATETIME_COMPLETE_MASK.equalsIgnoreCase(mask)) {
			return SLASH_DATETIME_COMPLETE_MASK;
		}else if(DASH_DATETIME_COMPLETE_MASK.equalsIgnoreCase(mask)) {
			return DASH_DATETIME_COMPLETE_MASK;
		}
		throw new IllegalArgumentException("Invalid Mask Date or DateTime");
	}
	
	public static boolean isDateTimeCompleteLength(String dateString) {
		if(dateString != null) {
			return DASH_DATETIME_COMPLETE_MASK.length() == dateString.length();
		}
		return false;
	}
	
	
	
	
}
