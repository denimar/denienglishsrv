package com.denimar.denienglishsrv.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T51PRN;

public interface T51PRNService extends JpaRepository<T51PRN, Integer> {
	
	public List<T51PRN> findAllByOrderByDsExpressao();
	
}
