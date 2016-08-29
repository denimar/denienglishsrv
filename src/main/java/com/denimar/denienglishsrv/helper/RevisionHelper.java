package com.denimar.denienglishsrv.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05EXP;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T05REV;
import com.denimar.denienglishsrv.domain.T07CTD;
import com.denimar.denienglishsrv.domain.T07TXT;
import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.domain.T08VIS;
import com.denimar.denienglishsrv.domain.T50DCI;
import com.denimar.denienglishsrv.domain.T51PRN;
import com.denimar.denienglishsrv.dto.ExpressionResponseDTO;
import com.denimar.denienglishsrv.service.T05EXPService;
import com.denimar.denienglishsrv.service.T05ITMService;
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
	private T05ITMService t05itmService;
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
	T05EXPService t05expService;
	@Autowired
	CategoryHelper categoryHelper;
	
	
	public List<T05EXP> getExpressionsByItem(T05ITM t05itm, boolean onlyVisible) {
		List<T05EXP> t05expList = t05expService.findByT05itm(t05itm);
		if (t05expList.size() > 0) {
			if (onlyVisible) {
				for (int index = 0 ; index < t05expList.size() ; index++) {
					T05EXP t05exp = t05expList.get(index);
					if (!t05exp.isBlMostrar()) {
						t05expList.remove(t05exp);
						index--;
					}
				}
			}
		} else {	
			String allTextsFromItem = getAllTextsFromItem(t05itm);
			
			//add the dictionary expressions
			List<T50DCI> dictionaryExpressionsInText = getDictionaryExpressionsInText(allTextsFromItem);
			for (T50DCI t50dci : dictionaryExpressionsInText) {
				T05EXP t05exp = new T05EXP();
				t05exp.setT05itm(t05itm);
				t05exp.setT50dci(t50dci);
				t05expService.save(t05exp);
			}
			
			//add the pronunciation expressions
			List<T51PRN> pronunciationExpressionsInText = getPronunciationExpressionsInText(allTextsFromItem);
			for (T51PRN t51prn : pronunciationExpressionsInText) {
				T05EXP t05exp = new T05EXP();
				t05exp.setT05itm(t05itm);
				t05exp.setT51prn(t51prn);
				t05expService.save(t05exp);
			}
			
			t05expList = getExpressionsByItem(t05itm, onlyVisible);
		}
		
		return t05expList;
	}

	public List<T05EXP> updExpressionsByItem(T05ITM t05itm, List<T05EXP> expressions) {

		for (T05EXP expression : expressions) {
			T05EXP t05exp = t05expService.findOne(expression.getCdItemExpressao());
			t05exp.setBlMostrar(expression.isBlMostrar());
			t05expService.save(t05exp);
		}		
		return expressions;
	}
	
	/*
	 * When the text of videos (subtitles) or texts itself are changed, its revision expressions had to be changed too, respecting the current content
	 */
	public List<T05EXP> updText(T05ITM t05itm, boolean returnOnlyVisible) {
		List<T05EXP> t05expList = t05expService.findByT05itm(t05itm);
		if (t05expList.size() == 0) {
			return getExpressionsByItem(t05itm, returnOnlyVisible);
		} else {
			//
			t05expService.delete(t05expService.findByT05itm(t05itm));
			
			//
			String allTextsFromItem = getAllTextsFromItem(t05itm);
			
			//add the dictionary expressions
			List<T50DCI> dictionaryExpressionsInText = getDictionaryExpressionsInText(allTextsFromItem);
			for (T50DCI t50dci : dictionaryExpressionsInText) {
				T05EXP t05exp = new T05EXP();
				t05exp.setT05itm(t05itm);
				t05exp.setT50dci(t50dci);
				for (int index = 0 ; index < t05expList.size() ; index++) {
					T05EXP item = t05expList.get(index);
					T50DCI t50dciItem = item.getT50dci();  
					
					if ((t50dciItem != null) && (t50dciItem.equals(t50dci))) {
						t05exp.setBlMostrar(item.isBlMostrar());
						break;
					}
				}				
				t05expService.save(t05exp);				
			}
			
			//add the pronunciation expressions
			List<T51PRN> pronunciationExpressionsInText = getPronunciationExpressionsInText(allTextsFromItem);
			for (T51PRN t51prn : pronunciationExpressionsInText) {
				T05EXP t05exp = new T05EXP();
				t05exp.setT05itm(t05itm);
				t05exp.setT51prn(t51prn);
				for (int index = 0 ; index < t05expList.size() ; index++) {
					T05EXP item = t05expList.get(index);
					T51PRN t51prnItem = item.getT51prn();  
					
					if ((t51prnItem != null) && (t51prnItem.equals(t51prn))) {
						t05exp.setBlMostrar(item.isBlMostrar());
						break;
					}
				}				
				t05expService.save(t05exp);				
			}
			
			return getExpressionsByItem(t05itm, returnOnlyVisible);
			
		}	
	}
	
	private String getAllTextsFromItem(T05ITM t05itm) {
		StringBuilder text = new StringBuilder(); //Text in which will be seached for expressions
		
		T08VDO t08vdo = t08vdoService.findByT05itm(t05itm);		
		
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
		
		return text.toString().trim();
	}
	
	private List<T50DCI> getDictionaryExpressionsInText(String text) {
		List<T50DCI> listReturn = new ArrayList<T50DCI>();
		
		//Searching within the Dictionary		
		List<T50DCI> t50dciList = t50dciService.findAll();
		for (T50DCI t50dci : t50dciList) {
			List<String> expressionsDicionaryList = new ArrayList<String>();			
			expressionsDicionaryList.add(t50dci.getDsExpressao());

			if ((t50dci.getDsTags() != null) && (!t50dci.getDsTags().isEmpty())) {
				String[] tags = t50dci.getDsTags().split(",");
				for (int i = 0; i < tags.length; i++) {
					expressionsDicionaryList.add(tags[i]);		
				}
			}
			
			for (String expressionDictionary : expressionsDicionaryList) {
				if (isContainExactWord(text, expressionDictionary)) {
					listReturn.add(t50dci);
					break;
				}
			}	
			
		}
		
		return listReturn;
	}

	private List<T51PRN> getPronunciationExpressionsInText(String text) {
		List<T51PRN> listReturn = new ArrayList<T51PRN>();
	
		//Searching within the Pronunciations
		List<T51PRN> t51prnList = t51prnService.findAll();
		for (T51PRN t51prn : t51prnList) {
			if (isContainExactWord(text, t51prn.getDsExpressao())) {
				listReturn.add(t51prn);
			}
		}
		
		return listReturn;
	}	
	
	private boolean isContainExactWord(String fullString, String partWord){
	    String pattern = "\\b" + partWord + "\\b";
	    Pattern p = Pattern.compile(pattern);
	    Matcher m = p.matcher(fullString);
	    return m.find();
	}	
	
	public List<T05ITM> getItemsToReviewByCategoryAll(T02CTG t02ctg) {
		List<T02CTG> allCategoriesChildren = categoryHelper.getAllCategoryChildren(t02ctg);
		return t05itmService.findByT02ctgInAndBlFazerRevisaoOrderByDtLastRevisionAscDtInclusaoAsc(allCategoriesChildren, true);
	}
	
	public T05ITM markItemAsReviewed(T05ITM t05itm) {
		T05REV t05rev = new T05REV();
		t05rev.setT05itm(t05itm);
		t05revService.save(t05rev);
		
		t05itm.setDtLastRevision(t05rev.getDhRevisao());
		t05itmService.save(t05itm);
		
		return t05itm;
	}
	

}
