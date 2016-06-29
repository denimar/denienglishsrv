package com.denimar.denienglishsrv.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T50DCI;
import com.denimar.denienglishsrv.domain.T50DEF;

public interface T50DEFService extends JpaRepository<T50DEF, Integer> {
	
	public T50DEF findByT50dci(T50DCI t50dci);
	
}

