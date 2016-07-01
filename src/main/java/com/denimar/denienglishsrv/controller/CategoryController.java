package com.denimar.denienglishsrv.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.domain.T01TPO;
import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.service.T01TPOService;
import com.denimar.denienglishsrv.service.T02CTGService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	T01TPOService t01tpoService;

	@Autowired
	T02CTGService t02ctgService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T02CTG> listCategories(@RequestParam("cd_tipo") final int cd_tipo)  {
		T01TPO t01tpo = new T01TPO(cd_tipo, "");
		return new RestDefaultReturn<T02CTG>(true, t02ctgService.findByT01tpo(t01tpo));
	}	
	
	@RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestDefaultReturn<T02CTG> getCategoria(@RequestParam("cd_categoria") final int cd_categoria)  {
		T02CTG t02ctg = t02ctgService.findOne(cd_categoria);
		if (t02ctg == null) {
			return new RestDefaultReturn<T02CTG>(false, "Record not found!");			
		} else {
			return new RestDefaultReturn<T02CTG>(true, t02ctg);
		}	
	}
	
	@RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T02CTG> addCategory(@RequestParam("cd_tipo") final int cd_tipo, @RequestParam(defaultValue="-1", name="cd_categoria_pai", required=false) final int cd_categoria_pai, final String ds_categoria) {
		try {
			T01TPO t01tpo = t01tpoService.findOne(cd_tipo);
			
			//Adiciona a categoria
			T02CTG t02ctg = new T02CTG();
			t02ctg.setT01tpo(t01tpo);
			t02ctg.setT02ctg(t02ctgService.findOne(cd_categoria_pai));
			t02ctg.setDsCategoria(ds_categoria);
			t02ctgService.save(t02ctg);
			return new RestDefaultReturn<T02CTG>(true, t02ctg);
		} catch (Exception e) {
			return new RestDefaultReturn<T02CTG>(false, e.getMessage());			
		}	
	}
	
	@RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T02CTG> delCategory(@RequestParam("cd_categoria") final int cd_categoria) throws IOException  {
		T02CTG t02ctg = t02ctgService.findOne(cd_categoria);
		
		if (t02ctg == null) {
			return new RestDefaultReturn<T02CTG>(false, "Record not found!");
		} else {	
			t02ctgService.delete(t02ctg);
			return new RestDefaultReturn<T02CTG>(true, t02ctg);
		}	
	}
	
	@RequestMapping(value = "/upd", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T02CTG> updCategory(@RequestParam("cd_categoria") final int cd_categoria, final String ds_categoria) throws IOException  {
		T02CTG t02ctg = t02ctgService.findOne(cd_categoria);
		
		if (t02ctg == null) {
			return new RestDefaultReturn<T02CTG>(false, "Record not found!");
		} else {	
			//Edita a categoria
			t02ctg.setDsCategoria(ds_categoria);
			t02ctg.setDhAlteracao(new Date());
			t02ctgService.save(t02ctg);
			
			return new RestDefaultReturn<T02CTG>(true, t02ctg);
		}	
	}
	
	
}
