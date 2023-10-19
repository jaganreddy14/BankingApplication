package com.bankingapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.Exception.CardDoesNotExists;
import com.bankingapp.dto.DebitCardDto;
import com.bankingapp.model.DebitDetails;
import com.bankingapp.model.User;
import com.bankingapp.model.UserAccountInfo;
import com.bankingapp.repository.DebitCardRepository;
import com.bankingapp.repository.UserAccountInfoRepo;
import com.bankingapp.repository.UserRepo;
import com.bankingapp.util.EncryptionAndDecryption;

@Service
public class DebitCardDetailsServiceImpl implements DebitCardDetailsService {

	@Autowired
	private DebitCardRepository cardRepository;

	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private EncryptionAndDecryption encryptionAndDecryption;

	@Autowired
	private UserAccountInfoRepo userAccountRepo;

	@Override
	public Boolean isCardHotlisted(DebitCardDto dto) {

		DebitDetails foundCard = cardRepository.findByCardNumber(dto.getCardNumber());
		if (foundCard != null && foundCard.getHotlistingCard() == true) {
			return true;
		}

		return false;

	}

	@Override
	public DebitDetails registerNewCard(DebitCardDto carDetailsDto, Long accountNumber) {

		DebitDetails newCard = new DebitDetails();
		newCard.setCardNumber(encryptionAndDecryption.encryptCardNumber(carDetailsDto.getCardNumber()));
		newCard.setAppliedDate(new Date());
		newCard.setPin(carDetailsDto.getPin());
		newCard.setRewardPoints(1000);
		newCard.setCardHolderName(carDetailsDto.getCardHolder());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 2);

		Date updatedDate = calendar.getTime();

		newCard.setCardExpiration(updatedDate);
		newCard.setCardHolderName(carDetailsDto.getCardHolder());

		UserAccountInfo accountInfo = userAccountRepo.findByAccountNumber(accountNumber);
		newCard.setAccountDetails(accountInfo);

		return cardRepository.save(newCard);

	}

	@Override
	public DebitDetails blockExistingCard(DebitCardDto cardDetailsDto) {

		DebitDetails findCard = cardRepository.findByCardNumber(cardDetailsDto.getCardNumber());

		return null;
	}

	@Override
	public DebitDetails renewUserCard(DebitCardDto cardDetailsDto) {

		try {
			DebitDetails foundCard = cardRepository.findByCardNumber(cardDetailsDto.getCardNumber());
			if (foundCard != null && foundCard.getIsExpired() == true) {

				foundCard.setCardNumber(cardDetailsDto.getCardNumber());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.YEAR, 2);

				Date updatedDate = calendar.getTime();

				foundCard.setCardExpiration(updatedDate);
				foundCard.setIsExpired(false);
				cardRepository.save(foundCard);

			} else {
				throw new CardDoesNotExists("card does not Exists");
			}
		} catch (CardDoesNotExists e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public DebitDetails changeCardPin(Long accountNumber, String dob,String newPin) {
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = (Date)dateFormat.parse(dob);
			User findUser = userRepository.findByAccountNumberAndDob(accountNumber, date);
			UserAccountInfo findAccountInfo = findUser.getAccountDetails();

			if (findUser != null) {

				DebitDetails findcard = cardRepository.findByAccountDetails(findAccountInfo);
				findcard.setPin(newPin);
				return cardRepository.save(findcard);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}
		

	

	@Override
	public DebitDetails findCardDetails(Long accountNumber, String dob) {
		
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = (Date)dateFormat.parse(dob);
			User findUser = userRepository.findByAccountNumberAndDob(accountNumber, date);
			UserAccountInfo findAccountInfo = findUser.getAccountDetails();
			if (findUser != null) {

				DebitDetails findcard = cardRepository.findByAccountDetails(findAccountInfo);
				String cardNumber=encryptionAndDecryption.decryptCardNumber(findcard.getCardNumber());
				String privacy="XXXX XXXX XX".concat(cardNumber.substring(cardNumber.length()-6));
				findcard.setCardNumber(privacy);
				return findcard;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public boolean checkCardCredentials(String cardNumber, String pin) {
		
		String encryptedCard=encryptionAndDecryption.encryptCardNumber(cardNumber);
		
		DebitDetails foundCard= cardRepository.findByCardNumber(encryptedCard);
	
		String card=foundCard.getCardNumber();
		String decryptedCard= encryptionAndDecryption.decryptCardNumber(card);
		
		if( decryptedCard.equals(cardNumber) && foundCard.getPin().equals(pin)){
			return true;
		}
		return false;
	}

}
