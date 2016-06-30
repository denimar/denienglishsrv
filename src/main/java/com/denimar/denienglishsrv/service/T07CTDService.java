package com.denimar.denienglishsrv.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T07CTD;
import com.denimar.denienglishsrv.domain.T07TXT;

public interface T07CTDService extends JpaRepository<T07CTD, Integer> {
	
	public T07CTD findByT07txt(T07TXT t07txt);
	public T07CTD findByT07txt_CdTexto(int cd_texto);	
	
}
