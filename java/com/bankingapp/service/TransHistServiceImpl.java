package com.bankingapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.Exception.NoTransactionFoundWithThatAccountNumber;
import com.bankingapp.dto.TransactionDto;
import com.bankingapp.enums.PaymentStatus;
import com.bankingapp.enums.TransactionType;
import com.bankingapp.model.Atm;
import com.bankingapp.model.Transaction;
import com.bankingapp.model.TransactionHistories;
import com.bankingapp.model.User;
import com.bankingapp.repository.AtmRepository;
import com.bankingapp.repository.TransHistoryRepo;
import com.bankingapp.repository.TransactionRepo;
import com.bankingapp.repository.UserRepo;

@Service
public class TransHistServiceImpl implements TransHistService {

	@Autowired
	private TransHistoryRepo transHistRepository;

	@Autowired
	private TransactionRepo transactionRepo;

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private AtmRepository atmrepo;

	@Override
	public TransactionHistories insertintoTransactionHistories(TransactionDto dto) {
		User user = userRepository.findByAccountNumber(dto.getAccountNumber());
		if (dto != null) {
			TransactionHistories transhist = new TransactionHistories();
			transhist.setTransactionId(dto.getTransactionId());
			transhist.setUserName(user.getUserName());
			transhist.setTransactionAmount(dto.getTransactionAmount());
			transhist.setAccountNumber(dto.getAccountNumber());
			transhist.setTransactionStatus(PaymentStatus.of(dto.getTransactionStatus()));
			transhist.setTransactionType(TransactionType.of(dto.getTransactionType()));
			Atm findAtm = atmrepo.findById(dto.getAtm()).orElse(null);
			transhist.setAtm(findAtm);
			transhist.setCreatedDate(new Date());
			transHistRepository.save(transhist);
		}
		return null;
	}

	@Override
	public List<TransactionHistories> listAll() {
		return transHistRepository.findAll();
	}

	@Override
	public List<TransactionHistories> getTransactionHistoriesByUserName(String userName) {
		return transHistRepository.findByUserName(userName);
	}

	@Override
	public List<TransactionHistories> filterTransactions(Long accountNumber, String from, String to) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<TransactionHistories> findtransactions = new ArrayList<TransactionHistories>();
		try {
			Date startDate = dateFormat.parse(from);
			Date endDate = dateFormat.parse(to);

			findtransactions = transHistRepository.findByCreatedDate(accountNumber, startDate, endDate);

			if (findtransactions == null) {
				throw new NoTransactionFoundWithThatAccountNumber("No transaction records for this account number");
			}
		} catch (NoTransactionFoundWithThatAccountNumber e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return findtransactions;
	}

	@Override
	public List<Transaction> getTransactionWithAccountNumber(Long AccountNumber) {
		return transactionRepo.findByAccountNumber(AccountNumber);
	}
}
