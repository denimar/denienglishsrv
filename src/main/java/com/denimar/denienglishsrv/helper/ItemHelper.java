package com.denimar.denienglishsrv.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		t05itm.setDsItem(ds_item);
		t05itmService.save(t05itm);
		
		//Edita a imagem da subcategoria
		T90IMG t90img = new T90IMG();
		byte[] imagem = ImageHelper.getBytesFromUriImagem(bt_imagem_item);
		t90img.setBtImagem(imagem);
		t90img.setT05itm(t05itm);
		t90imgService.save(t90img);
		
		return t05itm;
	}
	
	public T05ITM updateItem(final long cd_item, final int cd_categoria, final String ds_item, final String bt_imagem_item) throws Exception {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			throw new Exception("Item not found!");
		} else {
			T02CTG t02ctg = t02ctgService.findOne(cd_categoria);
			if (t02ctg == null) {
				throw new Exception("Category not found!");
			} else {
				t05itm.setT02ctg(t02ctg);				
				t05itm.setDsItem(ds_item);
				t05itm.setDtAlteracao(new Date());		
				t05itmService.save(t05itm);
				
				//Edita a imagem da subcategoria
				T90IMG t90img = t90imgService.findByT05itm(t05itm);
				if (t90img == null) {
					t90img = new T90IMG();
				}
				byte[] imagem = ImageHelper.getBytesFromUriImagem(bt_imagem_item);
				t90img.setBtImagem(imagem);
				t90img.setT05itm(t05itm);
				t90imgService.save(t90img);
				
				return t05itm; 
			}
		}	
	}
	
	
	public List<T05ITM> getItemsByCategory(final T02CTG category) {
		return getItemsByCategory(category, true);
	}
	
	public List<T05ITM> getItemsByCategory(final T02CTG category, final boolean deep) {
		List<T05ITM> list = new ArrayList<T05ITM>();
		
		list.addAll(t05itmService.findByT02ctgOrderByDsItemAsc(category));  
		List<T02CTG> children = t02ctgService.findByT02ctg(category);
		
		for (T02CTG child : children) {
			list.addAll(getItemsByCategory(child, false));
		}
			
		return list;
	}

}
