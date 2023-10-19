package com.bankingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.model.Atm;
import com.bankingapp.repository.AtmRepository;

@Service
public class AtmServiceImpl implements AtmService{
	
	
	@Autowired
	private AtmRepository atmRepository;

	@Override
	public Atm findByAtmId(Long id) {
		return atmRepository.getById(id);
	}
	
	

	
}
