package com.denimar.denienglishsrv.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.domain.T01TPO;
import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.dto.CategoryTreeListResponseDTO;
import com.denimar.denienglishsrv.helper.CategoryHelper;
import com.denimar.denienglishsrv.helper.ImageHelper;
import com.denimar.denienglishsrv.service.T01TPOService;
import com.denimar.denienglishsrv.service.T02CTGService;
import com.denimar.denienglishsrv.service.T90IMGService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
	
	@Autowired
	T01TPOService t01tpoService;
	@Autowired
	T02CTGService t02ctgService;
	@Autowired	 
	private T90IMGService t90imgService;
	@Autowired
	CategoryHelper categoryHelper; 
	@Autowired
	private ImageHelper imageHelper;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T02CTG> listCategories(@RequestParam("cd_tipo") final int cd_tipo)  {
		T01TPO t01tpo = new T01TPO(cd_tipo, "");
		return new RestDefaultReturn<T02CTG>(true, t02ctgService.findByT01tpo(t01tpo));
	}
	
	@RequestMapping(value = "/category/tree/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody CategoryTreeListResponseDTO[] getCategoryTreeList()  {
		return categoryHelper.getCategoryTreeList().getChildren();
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
	public RestDefaultReturn<T02CTG> addCategory(@RequestParam("cd_categoria_pai") final int cd_categoria_pai, final String ds_categoria) {
		try {
			//Get the parent category
			T02CTG parentT02CTG = t02ctgService.findOne(cd_categoria_pai);
			if (parentT02CTG == null) {
				return new RestDefaultReturn<T02CTG>(false, "Parent category not found!");	
			}
			
			//Adiciona a categoria
			T02CTG t02ctg = new T02CTG();
			t02ctg.setT02ctg(parentT02CTG);
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
	
	@RequestMapping(value = "/image/get")
	public void getImage(@RequestParam("cd_categoria") final int cd_categoria, HttpServletResponse response) throws Exception {
		T02CTG t02ctg = t02ctgService.findOne(cd_categoria);
		if (t02ctg == null) {
			throw new Exception("Category not found!");
		} else {
			imageHelper.getImagemBancoDados(response, categoryHelper.getImage(t02ctg));
		}
	}
	
}
