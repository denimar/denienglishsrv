package com.denimar.denienglishsrv.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.domain.T08VIS;

public interface T08VISService extends CrudRepository<T08VIS, Integer>{
	
	public List<T08VIS> findByT08vdo(T08VDO t08vdo);
	public List<T08VIS> findByT08vdo_t05itmOrderByNrStart(T05ITM t05itm);
	
}
