package com.denimar.denienglishsrv.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T01TPO;
import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05ITM;

public interface T05ITMService extends JpaRepository<T05ITM, Long> {
	
	List<T05ITM> findByT02ctg_T01tpo(T01TPO t01tpo);
	List<T05ITM> findByT02ctg(T02CTG t02ctg);
	
}
