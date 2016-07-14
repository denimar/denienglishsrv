package com.denimar.denienglishsrv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.domain.T51PRN;
import com.denimar.denienglishsrv.service.T51PRNService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@RestController
@RequestMapping("/pronunciation")
@CrossOrigin
public class PronunciationController {

	@Autowired	 
	private T51PRNService t51prnService;
	
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T51PRN> listPronuncia()  {
		return new RestDefaultReturn<T51PRN>(true, t51prnService.findAll());
	}	
	
	@RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T51PRN> addPronuncia(@RequestParam("ds_expressao") final String ds_expressao)  {
		T51PRN t51prn = new T51PRN();
		t51prn.setDsExpressao(ds_expressao);
		t51prnService.save(t51prn);
		return new RestDefaultReturn<T51PRN>(true, t51prn);
	}
	
	@RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T51PRN> delPronuncia(@RequestParam("cd_pronuncia") final int cd_pronuncia)  {
		T51PRN t51prn = t51prnService.findOne(cd_pronuncia);
		if (t51prn == null) {
			return new RestDefaultReturn<T51PRN>(false, "Record not found!");
		} else {
			t51prnService.delete(t51prn);
			return new RestDefaultReturn<T51PRN>(true, t51prn);
		}	
	}	
	
	@RequestMapping(value = "/learned/toogle", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T51PRN> setAprendidoPronuncia(@RequestParam("cd_pronuncia") final int cd_pronuncia)  {
		T51PRN t51prn = t51prnService.findOne(cd_pronuncia);
		if (t51prn == null) {
			return new RestDefaultReturn<T51PRN>(false, "Record not found!");
		} else {
			t51prn.setBlAprendido(!t51prn.isBlAprendido());
			t51prnService.save(t51prn);
			return new RestDefaultReturn<T51PRN>(true, t51prn);
		}	
	}	
	
}
