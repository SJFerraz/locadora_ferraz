package br.com.locadora.util;

public abstract class ValidatorMessagesUtils {
	
	public final static String getMessageFieldValidation(String fieldName, String classeName, String finalMessageValidation) {
		return "O campo '"+fieldName+"' para '"+classeName+"' "+finalMessageValidation+".";
	}
	
	public final static String getMessageFieldIfEmpty(String fieldName, String classeName) {
		return getMessageFieldValidation(fieldName, classeName, "é obrigatório");
	}
	
	public final static String getMessageFieldIfInvalid(String fieldName, String classeName) {
		return getMessageFieldValidation(fieldName, classeName, "não é valido");
	}
	
	public final static String getMessageFieldValidation(String fieldName, Class classe, String finalMessageValidation) {
		return getMessageFieldValidation(fieldName, classe.getSimpleName().toLowerCase(), finalMessageValidation);
	}
	
	public final static String getMessageFieldIfEmpty(String fieldName, Class classe) {
		return getMessageFieldIfEmpty(fieldName, classe.getSimpleName().toLowerCase());
	}
	
	public final static String getMessageFieldIfInvalid(String fieldName, Class classe) {
		return getMessageFieldIfInvalid(fieldName, classe.getSimpleName().toLowerCase());
	}
	
}
