package com.denimar.denienglishsrv.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.domain.T05REV;
import com.denimar.denienglishsrv.service.T05REVService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@RestController
@RequestMapping("/revision")
public class RevisionController {
	
	@Autowired	 
	private T05REVService t05revService;
	
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T05REV> getItemsRevisar(@RequestParam final boolean pendente) throws ParseException  {
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = " 2016-02-02";
		Date data = sdf.parse(dateString); // Handle the ParseException here

		List<T05REV> list = t05revService.findByDhrevisaoGreaterThan(data);
		
		System.out.println(list.size());
		
		return null;
		/*
		StringBuilder hql = new StringBuilder();
		hql.append("from T05REV as rev\n");
		hql.append("right join fetch rev.t05itm itm\n");
		hql.append("where\n");
		hql.append("  rev.cd_revisao_item = (select max(rev2.cd_revisao_item) from T05REV rev2 where rev2.t05itm = rev.t05itm)\n");
		hql.append("  and itm.bl_fazer_revisao = true\n");
		
		if (pendente) {
			hql.append("  and rev.dh_revisao <= :dh_revisao\n");
		} else {
			hql.append("  and rev.dh_revisao > :dh_revisao\n");			
		}
		
		hql.append("order by\n");
		hql.append("  rev.dh_revisao desc");		
		
        try {
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.DATE, -7);        	
        	Query qry = sessionDaoImpl.getCurrentSession().createQuery(hql.toString());
        	qry.setDate("dh_revisao", cal.getTime());
        	xLista = qry.list();
        	return xLista;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }		
		
		List<T05REV> lista = t05revService.findAll();
		return new RestDefaultReturn<T05REV>(true, lista);
		*/
	}	

}
