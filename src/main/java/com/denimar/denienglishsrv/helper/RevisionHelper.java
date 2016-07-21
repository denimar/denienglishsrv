package com.denimar.denienglishsrv.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T05REV;
import com.denimar.denienglishsrv.domain.T07CTD;
import com.denimar.denienglishsrv.domain.T07TXT;
import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.domain.T08VIS;
import com.denimar.denienglishsrv.domain.T50DCI;
import com.denimar.denienglishsrv.domain.T51PRN;
import com.denimar.denienglishsrv.dto.ExpressionResponseDTO;
import com.denimar.denienglishsrv.service.T05REVService;
import com.denimar.denienglishsrv.service.T07CTDService;
import com.denimar.denienglishsrv.service.T07TXTService;
import com.denimar.denienglishsrv.service.T08VDOService;
import com.denimar.denienglishsrv.service.T08VISService;
import com.denimar.denienglishsrv.service.T50DCIService;
import com.denimar.denienglishsrv.service.T51PRNService;

@Component
public class RevisionHelper {

	@Autowired	 
	private T05REVService t05revService;
	@Autowired
	T08VDOService t08vdoService;
	@Autowired
	T07TXTService t07txtService;
	@Autowired
	T07CTDService t07ctdService;
	@Autowired
	T08VISService t08visService;
	@Autowired
	T50DCIService t50dciService;
	@Autowired
	T51PRNService t51prnService;
	@Autowired
	CategoryHelper categoryHelper;
	
	
	public List<ExpressionResponseDTO> getExpressionsByItem(T05ITM t05itm) {
		List<ExpressionResponseDTO> listReturn = new ArrayList<ExpressionResponseDTO>();
		
		T08VDO t08vdo = t08vdoService.findByT05itm(t05itm);
		StringBuilder text = new StringBuilder(); //Text in which will be seached for expressions
		
		//Video
		if (t08vdo != null) {
			List<T08VIS> t08visList = t08visService.findByT08vdo(t08vdo);
			for (T08VIS t08vis : t08visList) {
				text.append("\n");				
				text.append(t08vis.getDsTexto());
			}
			
		} else {
			//text
			List<T07TXT> t07txtList = t07txtService.findByT05itm(t05itm);
			for (T07TXT t07txt : t07txtList) {
				T07CTD t07ctd = t07ctdService.findByT07txt(t07txt);
				text.append("\n");				
				text.append(t07ctd.getTxConteudo());
			}
		}
		
		String[] expressionsText = text.toString().split("\\s");
		List<String> expressionsList = Arrays.asList(expressionsText);

		//Searching within the Dictionary		
		List<T50DCI> t50dciList = t50dciService.findAll();
		for (T50DCI t50dci : t50dciList) {
			if (expressionsList.contains(t50dci.getDsExpressao())) {
				ExpressionResponseDTO item = new ExpressionResponseDTO();
				item.setCdDicionario(t50dci.getCdDicionario());
				item.setDsExpressao(t50dci.getDsExpressao());
				item.setNrLevelOfLearning(t50dci.getNrLevelOfLearning());
				
				listReturn.add(item);
			}
		}
		
		//Searching within the Pronunciations
		List<T51PRN> t51prnList = t51prnService.findAll();
		for (T51PRN t51prn : t51prnList) {
			if (expressionsList.contains(t51prn.getDsExpressao())) {
				ExpressionResponseDTO item = new ExpressionResponseDTO();
				item.setCdPronuncia(t51prn.getCdPronuncia());
				item.setDsExpressao(t51prn.getDsExpressao());
				item.setNrLevelOfLearning(t51prn.getNrLevelOfLearning());
				
				listReturn.add(item);
			}
		}
		
		return listReturn;		
	}
	
	public List<T05ITM> getItemsToReviewByCategoryAll(T02CTG t02ctg) {
		List<T02CTG> t02ctgList = categoryHelper.getAllCategoryChildren(t02ctg);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7 * -1);        	
		
		List<T05ITM> t05itmList = new ArrayList<T05ITM>();		
		
		for (T02CTG category : t02ctgList) {
			List<T05REV> t05revList = t05revService.findByDhRevisaoLessThanAndT05itm_BlFazerRevisaoAndT05itm_T02ctg(cal.getTime(), true, category);
			
			for (T05REV t05rev : t05revList) {
				if (t05itmList.indexOf(t05rev.getT05itm()) == -1) {
					t05itmList.add(t05rev.getT05itm());				
				}
			}
		}
		
		return t05itmList;
	}
	

}
