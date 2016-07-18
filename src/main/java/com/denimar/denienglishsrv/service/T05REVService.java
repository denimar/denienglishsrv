package com.denimar.denienglishsrv.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T05REV;

public interface T05REVService extends JpaRepository<T05REV, Integer> {

	List<T05REV> findByDhRevisaoLessThanAndT05itm_BlFazerRevisao(Date dhRevisao, boolean blFazerRevisao);
	List<T05REV> findByDhRevisaoLessThanAndT05itm_BlFazerRevisaoAndT05itm(Date dhRevisao, boolean blFazerRevisao, T05ITM t05itm);	
	List<T05REV> findByDhRevisaoLessThanAndT05itm_BlFazerRevisaoAndT05itm_T02ctg(Date dhRevisao, boolean blFazerRevisao, T02CTG t02ctg);	
	
}
