package com.denimar.denienglishsrv.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05ITM;

@Transactional
public interface T05ITMService extends JpaRepository<T05ITM, Long> {
	
	List<T05ITM> findByT02ctgOrderByDsItemAsc(T02CTG t02ctg);
	List<T05ITM> findByT02ctgAndBlFavoriteOrderByDsItemAsc(T02CTG t02ctg, boolean blFavorite);
	List<T05ITM> findByT02ctgAndBlFazerRevisaoAndDtLastRevisionLessThan(T02CTG t02ctg, boolean blFazerRevizao, Date dtLastRevision);	
	
	@Query("SELECT a FROM T05ITM a INNER JOIN FETCH a.t02ctg WHERE a.cdItem = :cdItem")
	T05ITM findOneFetchingT02ctg(@Param("cdItem") long cdItem);
	
}
