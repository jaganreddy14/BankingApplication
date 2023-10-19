package com.bankingapp.util;

import org.springframework.stereotype.Component;

@Component
public class EncryptionAndDecryption {

	public String encryptCardNumber(String CardNumber) {
		String randomText = "7678546789324517";

		int middleIndex = randomText.length() / 2;
		String encryptedCardNumber = randomText.substring(0, middleIndex) + CardNumber
				+ randomText.substring(middleIndex);
		return encryptedCardNumber;

	}

	public String decryptCardNumber(String encryptedCardNumber) {
		int firstHalf = (encryptedCardNumber.length() / 2) - 8;
		int secondHalf = firstHalf + 16;

		String decryptedCardNumber = encryptedCardNumber.substring(firstHalf, secondHalf);
		return decryptedCardNumber;

	}

	
}
