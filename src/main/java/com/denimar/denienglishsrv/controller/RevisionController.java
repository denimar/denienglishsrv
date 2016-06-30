package com.denimar.denienglishsrv.controller;

import java.text.ParseException;
import java.util.Calendar;
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
	public RestDefaultReturn<T05REV> getItemsRevisar(@RequestParam("pendente") final boolean pendente, @RequestParam("days") final int numberOfDays) throws ParseException  {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, numberOfDays * -1);        	

		List<T05REV> list = t05revService.findByDhrevisaoLessThanAndT05itm_BlFazerRevisao(cal.getTime(), true);
		
		return new RestDefaultReturn<T05REV>(true, list); 
	}	

}
