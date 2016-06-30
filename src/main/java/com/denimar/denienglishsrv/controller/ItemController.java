package com.denimar.denienglishsrv.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.domain.T01TPO;
import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T90IMG;
import com.denimar.denienglishsrv.helper.ImageHelper;
import com.denimar.denienglishsrv.helper.ItemHelper;
import com.denimar.denienglishsrv.service.T02CTGService;
import com.denimar.denienglishsrv.service.T05ITMService;
import com.denimar.denienglishsrv.service.T90IMGService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired	 
	private T02CTGService t02ctgService;
	@Autowired	 
	private T05ITMService t05itmService;
	@Autowired	 
	private T90IMGService t90imgService;
	@Autowired
	private ItemHelper itemHelper;
	
	@RequestMapping("/listall")
	public RestDefaultReturn<T05ITM> getAllItems(@RequestParam("cd_tipo") final int cd_tipo) {
		T01TPO t01tpo = new T01TPO(cd_tipo, "");
		return new RestDefaultReturn<T05ITM>(true, t05itmService.findByT02ctg_T01tpo(t01tpo));
		
	}
	
	@RequestMapping("/list")
	public RestDefaultReturn<T05ITM> getItems(@RequestParam("cd_categoria") final int cd_categoria) {
		T02CTG t02ctg = t02ctgService.findOne(cd_categoria);
		List<T05ITM> lista = t05itmService.findByT02ctg(t02ctg);
		return new RestDefaultReturn<T05ITM>(true, lista);
	}
	
	@RequestMapping(value = "/get")
	public RestDefaultReturn<T05ITM> getItem(@RequestParam("cd_item") final long cd_item)  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Record not found!");			
		} else {
			return new RestDefaultReturn<T05ITM>(true, t05itmService.findOne(cd_item));
		}	
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public RestDefaultReturn<T05ITM> addItem(@RequestParam("cd_categoria") final int cd_categoria, @RequestParam("ds_item") final String ds_item, final @RequestBody String bt_imagem_item) throws IOException  {
		try {
			T05ITM t05itm = itemHelper.createItem(cd_categoria, ds_item, bt_imagem_item);
			return new RestDefaultReturn<T05ITM>(true, t05itm);
		} catch (Exception e) {
			return new RestDefaultReturn<T05ITM>(false, e.getMessage());			
		}	
	}
	
	@RequestMapping(value = "/del")
	public RestDefaultReturn<T05ITM> delItem(@RequestParam("cd_item") final long cd_item)  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Record not found!");
		} else {
			t05itmService.delete(t05itm);
			return new RestDefaultReturn<T05ITM>(true, t05itm);			
		}
	}
	
	@RequestMapping(value = "/rename", method = RequestMethod.POST)	
	public RestDefaultReturn<T05ITM> renameItem(@RequestParam("cd_item") final long cd_item, final String ds_item) throws IOException  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Record not found!");
		} else {
			t05itm.setDs_item(ds_item);
			t05itm.setDt_alteracao(new Date());		
			t05itmService.save(t05itm);
			return new RestDefaultReturn<T05ITM>(true, t05itm);
		}	
	}	

	@RequestMapping(value = "/upd-image", method = RequestMethod.POST)	
	public RestDefaultReturn<T05ITM> updImageItem(@RequestParam("cd_item") final long cd_item, final @RequestBody String bt_imagem_item) throws IOException  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Record not found!");
		} else {
			t05itm.setDt_alteracao(new Date());		
			t05itmService.save(t05itm);
			
			//Edita a imagem da subcategoria
			T90IMG t90img = t90imgService.findByT05itm(t05itm);
			byte[] imagem = ImageHelper.getBytesFromUriImagem(bt_imagem_item);
			t90img.setBt_imagem(imagem);
			t90imgService.save(t90img);
			
			return new RestDefaultReturn<T05ITM>(true, t05itm);
		}	
	}	
	
}
