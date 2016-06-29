package com.denimar.denienglishsrv.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.domain.T08VIS;

public interface T08VISService extends JpaRepository<T08VIS, Integer>{
	
	public List<T08VIS> findByT08vdo(T08VDO t08vdo);
	
}
