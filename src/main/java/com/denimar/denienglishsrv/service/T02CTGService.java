package com.denimar.denienglishsrv.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.denimar.denienglishsrv.domain.T01TPO;
import com.denimar.denienglishsrv.domain.T02CTG;

public interface T02CTGService extends JpaRepository<T02CTG, Integer>, CrudRepository<T02CTG, Integer> {
	
	 public List<T02CTG> findByT01tpo(T01TPO t01tpo);
	 public List<T02CTG> findByT02ctg(T02CTG t02ctg);
	
}
