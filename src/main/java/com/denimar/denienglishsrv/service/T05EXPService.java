package com.denimar.denienglishsrv.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T05EXP;
import com.denimar.denienglishsrv.domain.T05ITM;

public interface T05EXPService extends JpaRepository<T05EXP, Integer> {

	List<T05EXP> findByT05itm(T05ITM t05itm);
	
}
