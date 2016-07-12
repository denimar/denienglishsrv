package com.denimar.denienglishsrv.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.domain.T01TPO;
import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T90IMG;
import com.denimar.denienglishsrv.dto.ImageRequestDTO;
import com.denimar.denienglishsrv.helper.ImageHelper;
import com.denimar.denienglishsrv.helper.ItemHelper;
import com.denimar.denienglishsrv.service.T02CTGService;
import com.denimar.denienglishsrv.service.T05ITMService;
import com.denimar.denienglishsrv.service.T90IMGService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@CrossOrigin
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
	@Autowired
	private ImageHelper imageHelper;
	
	@RequestMapping("/list")
	public RestDefaultReturn<T05ITM> getItemsItemsByCategory(@RequestParam("cd_categoria") final int cd_categoria) {
		T02CTG t02ctg = t02ctgService.findOne(cd_categoria);
		if (t02ctg == null) {
			return new RestDefaultReturn<T05ITM>(false, "Category not found!");			
		} else {
			return new RestDefaultReturn<T05ITM>(true, itemHelper.getItemsByCategory(t02ctg));
		}	
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
	public RestDefaultReturn<T05ITM> addItem(@RequestParam("cd_categoria") final int cd_categoria, @RequestParam("ds_item") final String ds_item, final @RequestBody ImageRequestDTO imageRequestDTO) throws IOException  {
		try {
			T05ITM t05itm = itemHelper.createItem(cd_categoria, ds_item, imageRequestDTO.getBt_imagem());
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

	@RequestMapping(value = "/upd", method = RequestMethod.POST)	
	public RestDefaultReturn<T05ITM> updItem(@RequestParam("cd_item") final long cd_item, @RequestParam("cd_categoria") final int cd_categoria, final String ds_item, final @RequestBody ImageRequestDTO imageRequestDTO) throws IOException  {
		try {
			T05ITM t05itm = itemHelper.updateItem(cd_item, cd_categoria, ds_item, imageRequestDTO.getBt_imagem());
			return new RestDefaultReturn<T05ITM>(true, t05itm);
		} catch (Exception e) {
			return new RestDefaultReturn<T05ITM>(false, e.getMessage());			
		}	
	}	
	
	@RequestMapping(value = "/rename", method = RequestMethod.POST)	
	public RestDefaultReturn<T05ITM> renameItem(@RequestParam("cd_item") final long cd_item, final String ds_item) throws IOException  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Record not found!");
		} else {
			t05itm.setDsItem(ds_item);
			t05itm.setDtAlteracao(new Date());		
			t05itmService.save(t05itm);
			return new RestDefaultReturn<T05ITM>(true, t05itm);
		}	
	}	

	@RequestMapping(value = "/image/upd", method = RequestMethod.POST)	
	public RestDefaultReturn<T05ITM> updImageItem(@RequestParam("cd_item") final long cd_item, final @RequestBody String bt_imagem_item) throws IOException  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Record not found!");
		} else {
			t05itm.setDtAlteracao(new Date());		
			t05itmService.save(t05itm);
			
			//Edita a imagem da subcategoria
			T90IMG t90img = t90imgService.findByT05itm(t05itm);
			byte[] imagem = ImageHelper.getBytesFromUriImagem(bt_imagem_item);
			t90img.setBtImagem(imagem);
			t90imgService.save(t90img);
			
			return new RestDefaultReturn<T05ITM>(true, t05itm);
		}	
	}	

	@RequestMapping(value = "/image/get")
	public void getImage(@RequestParam("cd_item") final long cd_item, @RequestParam("time") final String time, HttpServletResponse response) throws Exception {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			throw new Exception("Item not found!");
		} else {
			T90IMG t90img = t90imgService.findByT05itm(t05itm);
			imageHelper.getImagemBancoDados(response, t90img.getBtImagem());
		}
	}
	
}
