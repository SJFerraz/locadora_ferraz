package br.com.locadora.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public abstract class CustomStringUtils extends StringUtils{
	
	public static String generateStringBuilderToString(String... strings) {
		StringBuilder stringBuilder = new StringBuilder();
		for(String string : strings) {
			stringBuilder.append(string);
		}
		
		return stringBuilder.toString();
	}
	
}
