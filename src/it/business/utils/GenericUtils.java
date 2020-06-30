package it.business.utils;

/**
 * @author Simone Lungarella
 * */

public class GenericUtils {

	// Metodo che crea una stringa alfanumerica casuale di dimensione richiesta
	public static String randomAlphaNumeric(int count) {
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
