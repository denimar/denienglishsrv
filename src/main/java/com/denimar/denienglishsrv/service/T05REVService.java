package com.denimar.denienglishsrv.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T05REV;

public interface T05REVService extends JpaRepository<T05REV, Integer> {

	List<T05REV> findByDhrevisaoLessThanAndT05itm_BlFazerRevisao(Date dh_revisao, boolean blFazerRevisao);
	
}
