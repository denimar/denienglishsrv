package com.denimar.denienglishsrv.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T90IMG;
import com.denimar.denienglishsrv.service.T02CTGService;
import com.denimar.denienglishsrv.service.T05ITMService;
import com.denimar.denienglishsrv.service.T90IMGService;

@Component
public class ItemHelper {
	
	@Autowired	 
	private T02CTGService t02ctgService;
	@Autowired	 
	private T05ITMService t05itmService;
	@Autowired	 
	private T90IMGService t90imgService;
	
	public T05ITM createItem(final int cd_categoria, final String ds_item, final String bt_imagem_item) throws Exception {
		T02CTG t02ctg = t02ctgService.findOne(cd_categoria);
		if (t02ctg == null) {
			throw new Exception("Category not found!");
		}	
		T05ITM t05itm = new T05ITM();
		t05itm.setT02ctg(t02ctg);
		t05itm.setDs_item(ds_item);
		t05itmService.save(t05itm);
		
		//Edita a imagem da subcategoria
		T90IMG t90img = new T90IMG();
		byte[] imagem = ImageHelper.getBytesFromUriImagem(bt_imagem_item);
		t90img.setBt_imagem(imagem);
		t90img.setT05itm(t05itm);
		t90imgService.save(t90img);
		
		return t05itm;
	}

}
