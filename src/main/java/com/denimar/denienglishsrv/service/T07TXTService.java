package com.denimar.denienglishsrv.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T07TXT;

public interface T07TXTService extends JpaRepository<T07TXT, Integer> {
	
	public List<T07TXT> findByT05itmOrderByNrSeq(T05ITM t05itm);
	public List<T07TXT> findByT05itm_CdItemOrderByNrSeq(long cdItem);	
	
}
