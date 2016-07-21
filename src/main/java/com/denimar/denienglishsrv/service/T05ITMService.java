package com.denimar.denienglishsrv.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.denimar.denienglishsrv.domain.T01TPO;
import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05ITM;

@Transactional
public interface T05ITMService extends JpaRepository<T05ITM, Long> {
	
	List<T05ITM> findByT02ctg_T01tpo(T01TPO t01tpo);
	List<T05ITM> findByT02ctgOrderByDsItemAsc(T02CTG t02ctg);
	List<T05ITM> findByT02ctgAndBlFavoriteOrderByDsItemAsc(T02CTG t02ctg, boolean blFavorite);
	
	@Query("SELECT a FROM T05ITM a INNER JOIN FETCH a.t02ctg WHERE a.cdItem = :cdItem")
	T05ITM findOneFetchingT02ctg(@Param("cdItem") long cdItem);
	
}
