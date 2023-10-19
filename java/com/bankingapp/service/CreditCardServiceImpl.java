package com.bankingapp.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.Exception.CreditScoreNotFount;
import com.bankingapp.model.BankDetails;
import com.bankingapp.model.CreditCard;
import com.bankingapp.repository.BankDetailsRepo;
import com.bankingapp.repository.CreditCardRepository;
import com.bankingapp.util.EncryptionAndDecryption;

@Service
public class CreditCardServiceImpl implements CreditCardServive {

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Autowired
	private BankDetailsRepo bankDetailsRepo;

	@Autowired
	private EncryptionAndDecryption encyptDecrypt;

	@Override
	public CreditCard CheckEligibilityAndGenerateCredit(Integer creditScore, String userName, String bankName) {

		if (creditScore == null) {
			try {
				throw new CreditScoreNotFount("No credit score found");
			} catch (CreditScoreNotFount e) {
				e.printStackTrace();
			}
		}

		Long generateCredit = null;
		StringBuilder cardNumber = new StringBuilder();
		StringBuilder cvv = new StringBuilder();

		if (creditScore >= 730) {
			generateCredit = (long) Math.floor(Math.random() * 1000000);

			for (int i = 0; i < 16; i++) {
				int digit = (int) (Math.random() * 10);
				cardNumber.append(digit);
			}

			for (int i = 0; i < 3; i++) {
				int digit = (int) (Math.random() * 3
						);
				cvv.append(digit);
			}

			CreditCard newCreditcard = new CreditCard();
			newCreditcard.setCardHolderName(userName);
			newCreditcard.setCardNumber(encyptDecrypt.encryptCardNumber(cardNumber.toString()));
			newCreditcard.setCreatedDate(new Date());

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.YEAR, 3);

			Date expDate = calendar.getTime();

			newCreditcard.setExpirationDate(expDate);
			newCreditcard.setCreditLimit(generateCredit);
			newCreditcard.setCvv(encyptDecrypt.encryptCardNumber(cvv.toString()));

			BankDetails findBank = bankDetailsRepo.findByBankName(bankName);
			newCreditcard.setIssuedBank(findBank);

			return creditCardRepository.save(newCreditcard);

		}
		return null;
	}

	@Override
	public Boolean cancleCreditCardOrHotlistCreditCard(String cardNumber) {

		if (cardNumber.isEmpty()) {
			try {
				throw new CreditScoreNotFount("No credit score found");
			} catch (CreditScoreNotFount e) {
				e.printStackTrace();
			}
		}

		CreditCard foundCard = creditCardRepository.findByCardNumber(encyptDecrypt.decryptCardNumber(cardNumber));

		if (foundCard != null) {
			foundCard.setCancleCard(true);
			creditCardRepository.save(foundCard);
			return true;
		}

		return false;
	}

	@Override
	public Boolean payDueAmount(String cardNumber, Long amount) {

		if (cardNumber.isEmpty()) {
			try {
				throw new CreditScoreNotFount("No credit score found");
			} catch (CreditScoreNotFount e) {
				e.printStackTrace();
			}
		}

		CreditCard foundCard = creditCardRepository.findByCardNumber(encyptDecrypt.decryptCardNumber(cardNumber));

		if (foundCard != null && foundCard.getUserdLimit() == amount) {

			foundCard.setBalance(foundCard.getCreditLimit());
			creditCardRepository.save(foundCard);
			return true;

		}
		return false;
	}

	@Override
	public Long checkCreditCardBalance(String cardNumber) {

		if (cardNumber.isEmpty()) {
			try {
				throw new CreditScoreNotFount("No credit score found");
			} catch (CreditScoreNotFount e) {
				e.printStackTrace();
			}
		}

		CreditCard foundCard = creditCardRepository.findByCardNumber(encyptDecrypt.decryptCardNumber(cardNumber));

		if (foundCard != null) {
			Long currentCreditBalance = foundCard.getBalance();
			return currentCreditBalance;
		}

		return null;
	}

	@Override
	public CreditCard findByCardNumber(String cardNumber) {
		return creditCardRepository.findByCardNumber(cardNumber);
	}

}
