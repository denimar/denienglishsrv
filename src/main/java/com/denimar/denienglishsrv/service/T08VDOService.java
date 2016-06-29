package com.denimar.denienglishsrv.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T08VDO;

public interface T08VDOService extends JpaRepository<T08VDO, Integer> {
	
	T08VDO findByT05itm(T05ITM t05itm);
}
