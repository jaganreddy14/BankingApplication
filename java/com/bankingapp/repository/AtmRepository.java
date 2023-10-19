package com.bankingapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingapp.model.Atm;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Long> {

	Atm getById(Long id);

}
