package encryptionAndDecryption;

import java.util.ArrayList;
import java.util.Random;

public class Guts {

	public static String encrypt(String messageInput) {
		String message = messageInput;
		message = message.concat(" ");
		String encryptedMessage = "";
		ArrayList<String> wordsInMessage = new ArrayList<String>();
		separateWords(wordsInMessage, message);
		int keyNum = 0;
		int charNum = 0;
		int levelNum = 0;
		int specifNum = 0;
		char c;
		Random r = new Random();
		for (int i = 0; i < wordsInMessage.size(); i++) {
			keyNum = (int) Math.round(Math.random() * 26) + 1;
			encryptedMessage += genEncryptedChar(r);
			encryptedMessage = encryptedMessage + keyNum;
			encryptedMessage += genEncryptedChar(r);
			for (int j = 0; j < wordsInMessage.get(i).length(); j++) {
				encryptedMessage += genEncryptedChar(r);
				c = wordsInMessage.get(i).charAt(j);
				charNum = (int) c;
				levelNum = charNum / keyNum;
				specifNum = charNum % keyNum;
				encryptedMessage = encryptedMessage + levelNum;
				encryptedMessage += genEncryptedChar(r);
				encryptedMessage = encryptedMessage + specifNum;

			}
		}
		return encryptedMessage;
	}

	public static String decrypt(String messageInput) {
		String message = messageInput;
		String decryptedMessage = "";
		message = message.concat(" ");
		int keyNumber = 0;
		int[] letterNumbers = new int[2];
		for (int i = 0; i < message.length() - 1; i++) {
			for (int j = i + 1; j < message.length(); j++) {
				if (!Character.isDigit(message.charAt(i))
						&& !Character.isDigit(message.charAt(j))) {

					if (Character.isDigit(message.charAt(i - 2))) {
						keyNumber = Integer.parseInt(message
								.substring(i - 2, i));

					} else {
						keyNumber = message.charAt(i - 1) - 48;
					}
					decryptedMessage = decryptedMessage + " ";

				} else if (i > 3) {
					if (!Character.isDigit(message.charAt(i))
							&& Character.isDigit(message.charAt(i - 1))) {
						if (message.substring(i - 2, i).matches("\\d+")) {

							letterNumbers[0] = Integer.parseInt(message
									.substring(i - 2, i));
						} else {
							letterNumbers[0] = message.charAt(i - 1) - 48;
						}

						if (message.substring(j, j + 2).matches("\\d+")) {

							letterNumbers[1] = Integer.parseInt(message
									.substring(j, j + 2));
						} else {
							letterNumbers[1] = message.charAt(j) - 48;
						}

						int letter = keyNumber * (letterNumbers[0])
								+ (letterNumbers[1]);

						decryptedMessage = decryptedMessage
								+ Character.toString((char) letter);
						i += 3;

					}
				}

				break;
			}
		}
		decryptedMessage = decryptedMessage.substring(1);
		return decryptedMessage;
	}

	public static ArrayList<String> separateWords(
			ArrayList<String> wordsInMessageInput, String messageInput) {
		ArrayList<String> wordsInMessage = wordsInMessageInput;
		String message = messageInput;
		int wordBeginning = 0;
		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) == ' ' || i == message.length()) {
				wordsInMessage.add(message.substring(wordBeginning, i));
				wordBeginning = i + 1;
			}
		}
		return wordsInMessage;
	}

	public static String genEncryptedChar(Random rInput) {
		Random r = rInput;
		char c = '0';
		while (Character.isDigit(c)) {
			c = (char) (r.nextInt(93) + '!');
		}
		return Character.toString(c);
	}
}
