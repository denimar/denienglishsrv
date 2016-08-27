package com.denimar.denienglishsrv.controller;

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
import com.denimar.denienglishsrv.domain.T05EXP;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.dto.ItemRevisionInfoResponseDTO;
import com.denimar.denienglishsrv.helper.CategoryHelper;
import com.denimar.denienglishsrv.helper.RevisionHelper;
import com.denimar.denienglishsrv.service.T02CTGService;
import com.denimar.denienglishsrv.service.T05ITMService;
import com.denimar.denienglishsrv.service.T50DCIService;
import com.denimar.denienglishsrv.service.T51PRNService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@RestController
@RequestMapping("/revision")
@CrossOrigin
public class RevisionController {
	
	@Autowired	 
	private T05ITMService t05itmService;
	@Autowired	 
	private T02CTGService t02ctgService;
	@Autowired
	private T50DCIService t50dciService;
	@Autowired
	private T51PRNService t51prnService;
	@Autowired
	private CategoryHelper categoryHelper;
	@Autowired
	private RevisionHelper revisionHelper;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RestDefaultReturn<T05ITM> getItemsRevisar(@RequestParam("cd_categoria") int cdCategoria, @RequestParam("days") int days, @RequestParam("pendente") boolean pendente)  {
		//Category was informed?			
		T02CTG t02ctg = t02ctgService.findOne(cdCategoria);
		if (t02ctg == null) {
			return new RestDefaultReturn<T05ITM>(false, "Category not found!");			
		} else {
			return new RestDefaultReturn<T05ITM>(true, revisionHelper.getItemsToReviewByCategoryAll(t02ctg));
		} 
	}

	@RequestMapping(value = "/markasreviewed", produces = MediaType.APPLICATION_JSON_VALUE )
	public RestDefaultReturn<T05ITM> markAsRevied(@RequestParam("cd_item") long cdItem) {
		T05ITM t05itm = t05itmService.findOneFetchingT02ctg(cdItem);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Item not found!");			
		} else {
			return new RestDefaultReturn<T05ITM>(true, revisionHelper.markItemAsReviewed(t05itm));
		}
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

	@RequestMapping(value = "/expressions/get", produces = MediaType.APPLICATION_JSON_VALUE )	
	public RestDefaultReturn<T05EXP> getExpressionsByItem(@RequestParam("cd_item") long cdItem, @RequestParam("onlyVisible") final boolean onlyVisible) {
		T05ITM t05itm = t05itmService.findOneFetchingT02ctg(cdItem);
		if (t05itm == null) {
			return new RestDefaultReturn<T05EXP>(false, "Item not found!");			
		} else {
			return new RestDefaultReturn<T05EXP>(true, revisionHelper.getExpressionsByItem(t05itm, onlyVisible));
		}	
	}

	@RequestMapping(value = "/expressions/upd", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST )	
	public RestDefaultReturn<T05EXP> setExpressionsByItem(@RequestParam("cd_item") long cdItem, @RequestBody List<T05EXP> expressions) {
		T05ITM t05itm = t05itmService.findOneFetchingT02ctg(cdItem);
		if (t05itm == null) {
			return new RestDefaultReturn<T05EXP>(false, "Item not found!");			
		} else {
			return new RestDefaultReturn<T05EXP>(true, revisionHelper.updExpressionsByItem(t05itm, expressions));
		}	
	}
	
}
