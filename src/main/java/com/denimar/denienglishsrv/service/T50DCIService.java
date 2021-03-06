package com.denimar.denienglishsrv.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T50DCI;

public interface T50DCIService extends JpaRepository<T50DCI, Integer> {
	
	public List<T50DCI> findAllByOrderByDsExpressao();
	
}
