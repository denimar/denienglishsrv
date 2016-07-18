package com.denimar.denienglishsrv.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T05REV;
import com.denimar.denienglishsrv.dto.ItemRevisionInfoResponseDTO;
import com.denimar.denienglishsrv.dto.RevisionRequestDTO;
import com.denimar.denienglishsrv.helper.CategoryHelper;
import com.denimar.denienglishsrv.service.T02CTGService;
import com.denimar.denienglishsrv.service.T05ITMService;
import com.denimar.denienglishsrv.service.T05REVService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/revision")
@CrossOrigin
public class RevisionController {
	
	@Autowired	 
	private T05REVService t05revService;
	@Autowired	 
	private T05ITMService t05itmService;
	@Autowired	 
	private T02CTGService t02ctgService;
	@Autowired
	private CategoryHelper categoryHelper;
	
	/*
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T05REV> getItemsRevisar(@RequestParam("pendente") final boolean pendente, @RequestParam("days") final int numberOfDays) throws ParseException  {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, numberOfDays * -1);        	

		List<T05REV> list = t05revService.findByDhRevisaoLessThanAndT05itm_BlFazerRevisao(cal.getTime(), true);
		
		return new RestDefaultReturn<T05REV>(true, list); 
	}
	*/
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public RestDefaultReturn<T05REV> getItemsRevisar(@RequestBody RevisionRequestDTO revisionRequestDTO) throws ParseException  {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, revisionRequestDTO.getNrDays() * -1);        	

		List<T05REV> list = null;
		
		//Item was informed?
		if (revisionRequestDTO.getCdItem() != 0) {
			T05ITM t05itm = t05itmService.findOne(revisionRequestDTO.getCdItem());
			if (t05itm == null) {
				return new RestDefaultReturn<T05REV>(false, "Item not found!");			
			} else {
				list = t05revService.findByDhRevisaoLessThanAndT05itm_BlFazerRevisaoAndT05itm(cal.getTime(), true, t05itm);
			}	
			
		//Category was informed?			
		} else if (revisionRequestDTO.getCdCategoria() != 0) {
			T02CTG t02ctg = t02ctgService.findOne(revisionRequestDTO.getCdCategoria());
			if (t02ctg == null) {
				return new RestDefaultReturn<T05REV>(false, "Category not found!");			
			} else {
				list = t05revService.findByDhRevisaoLessThanAndT05itm_BlFazerRevisaoAndT05itm_T02ctg(cal.getTime(), true, t02ctg);
			}	
			
		//So	
		} else {
			t05revService.findByDhRevisaoLessThanAndT05itm_BlFazerRevisao(cal.getTime(), true);			
		}
		
		return new RestDefaultReturn<T05REV>(true, list); 
	}

	@RequestMapping(value = "/item/info", produces = MediaType.APPLICATION_JSON_VALUE )
	public RestDefaultReturn<ItemRevisionInfoResponseDTO> getItemRevisionInfo(@RequestParam("cd_item") long cdItem) {
		T05ITM t05itm = t05itmService.findOneFetchingT02ctg(cdItem);
		if (t05itm == null) {
			return new RestDefaultReturn<ItemRevisionInfoResponseDTO>(false, "Item not found!");			
		} else {
			ItemRevisionInfoResponseDTO itemRevisionInfoResponseDTO = new ItemRevisionInfoResponseDTO();
			itemRevisionInfoResponseDTO.setDsItem(t05itm.getDsItem());
			itemRevisionInfoResponseDTO.setDsBreadCrumbPath(categoryHelper.getBreadCrumbPath(t05itm.getT02ctg()));
			return new RestDefaultReturn<ItemRevisionInfoResponseDTO>(true, itemRevisionInfoResponseDTO);
		}
	} 
	
}
