package com.denimar.denienglishsrv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.domain.T50DCI;
import com.denimar.denienglishsrv.domain.T50DEF;
import com.denimar.denienglishsrv.dto.DictionaryDefinitionRequestDTO;
import com.denimar.denienglishsrv.service.T50DCIService;
import com.denimar.denienglishsrv.service.T50DEFService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@RestController
@RequestMapping("/dictionary")
@CrossOrigin
public class DictionaryController {
	
	@Autowired	 
	private T50DCIService t50dciService;
	@Autowired	 
	private T50DEFService t50defService;
	
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T50DCI> listPronuncia()  {
		return new RestDefaultReturn<T50DCI>(true, t50dciService.findAll());
	}	

	@RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T50DCI> addPronuncia(@RequestParam("ds_expressao") final String ds_expressao, @RequestParam("ds_tags") final String ds_tags)  {
		T50DCI t50dci = new T50DCI();
		t50dci.setDsExpressao(ds_expressao);
		t50dci.setDsTags(ds_tags);		
		t50dciService.save(t50dci);
		return new RestDefaultReturn<T50DCI>(true, t50dci);
	}
	
	@RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T50DCI> delDicionario(@RequestParam("cd_dicionario") final int cd_dicionario)  {
		T50DCI t50dci = t50dciService.findOne(cd_dicionario);
		if (t50dci == null) {
			return new RestDefaultReturn<T50DCI>(false, "Record not found!");
		} else {
			t50dciService.delete(t50dci);
			return new RestDefaultReturn<T50DCI>(true, t50dci);
		}	
	}	
	
	@RequestMapping(value = "/learned/toogle", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T50DCI> setAprendidoPronuncia(@RequestParam("cd_dicionario") final int cd_dicionario)  {
		T50DCI t50dci = t50dciService.findOne(cd_dicionario);
		if (t50dci == null) {
			return new RestDefaultReturn<T50DCI>(false, "Record not found!");
		} else {
			t50dci.setBlAprendido(!t50dci.isBlAprendido());
			t50dciService.save(t50dci);
			return new RestDefaultReturn<T50DCI>(true, t50dci);
		}	
	}	
	
	@RequestMapping(value = "/definition/set", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T50DEF> setDefinicaoDicionario(@RequestBody DictionaryDefinitionRequestDTO dictionaryDefinition)  {
		T50DCI t50dci = t50dciService.findOne(dictionaryDefinition.getCd_dicionario());
		if (t50dci == null) {
			return new RestDefaultReturn<T50DEF>(false, "Record not found!");
		} else {
			T50DEF t50def = t50defService.findByT50dci(t50dci);
			if (t50def == null) {
				t50def = new T50DEF();
				t50def.setT50dci(t50dci);
			}
			t50def.setTxDefinicao(dictionaryDefinition.getTx_definicao());
			t50defService.save(t50def);
			return new RestDefaultReturn<T50DEF>(true, t50def);
		}	
	}
	
	@RequestMapping(value = "/definition/get", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T50DEF> getDefinicaoDicionario(@RequestParam("cd_dicionario") final int cd_dicionario)  {
		T50DCI t50dci = t50dciService.findOne(cd_dicionario);
		if (t50dci == null) {
			return new RestDefaultReturn<T50DEF>(false, "Record not found!");
		} else {
			return new RestDefaultReturn<T50DEF>(true, t50defService.findByT50dci(t50dci));
		}	
	}		

}
