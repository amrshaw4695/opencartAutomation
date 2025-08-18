package testBase;

import java.security.SecureRandom;

public class RandomDataGenerator {

	private static final SecureRandom random = new SecureRandom();
	private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static String getRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
		}
		return sb.toString();
	}

	public static String getRandomNumber(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(10)); // digits 0–9
		}
		return sb.toString();
	}

	public static String getRandomAlphaNumeric(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
		}
		return sb.toString();
	}
}
