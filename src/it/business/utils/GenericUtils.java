package it.business.utils;

/**
 * Classe di utility generica.
 * 
 * @author Simone Lungarella
 */
public class GenericUtils {

	/**
	 * Collezione dei caratteri da utilizzare per la creazione di stringhe casuali.
	 */
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	/**
	 * Crea una stringa alfanumerica casuale di dimensione richiesta e la restituisce.
	 * 
	 * @param count Dimensione della stringa casuale da generare.
	 * @return Stringa generata.  
	 */
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	/**
	 * Crea una stringa valida come digest.
	 * 
	 * @return Digest creato.
	 */
	public static String getDigest() {
		int count = 40;
		final String ALPHA_NUMERIC_STRING = "abcdef0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
