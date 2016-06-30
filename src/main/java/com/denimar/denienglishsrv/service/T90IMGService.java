package com.denimar.denienglishsrv.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T90IMG;

public interface T90IMGService extends JpaRepository<T90IMG, Long> {
	
	public T90IMG findByT05itm(T05ITM t05itm);
}
