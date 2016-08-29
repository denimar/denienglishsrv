package com.denimar.denienglishsrv.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denimar.denienglishsrv.domain.T05EXP;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T50DCI;
import com.denimar.denienglishsrv.domain.T51PRN;

public interface T05EXPService extends JpaRepository<T05EXP, Integer> {

	List<T05EXP> findByT05itm(T05ITM t05itm);
	List<T05EXP> findByT05itmAndT50dci(T05ITM t05itm, T50DCI t50dci);	
	List<T05EXP> findByT05itmAndT51prn(T05ITM t05itm, T51PRN t51prn);	
	
}
