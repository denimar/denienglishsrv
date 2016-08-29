package com.denimar.denienglishsrv.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T07CTD;
import com.denimar.denienglishsrv.domain.T07TXT;
import com.denimar.denienglishsrv.service.T07CTDService;
import com.denimar.denienglishsrv.service.T07TXTService;

@Component
public class TextHelper {
	
	@Autowired
	private T07TXTService t07txtService;
	@Autowired
	private T07CTDService t07ctdService;
	@Autowired
	private RevisionHelper revisionHelper;
	
	public T07TXT createNewEmptyText(T05ITM t05itm) {
		T07TXT t07txt = new T07TXT();
		t07txt.setT05itm(t05itm);
		t07txt.setNrSeq((short) 1);
		t07txt.setDsTitulo("default");
		t07txt.setBlFazerRevisao(true);
		t07txtService.save(t07txt);
		
		T07CTD t07ctd  = new T07CTD();
		t07ctd.setT07txt(t07txt);
		t07ctd.setTxConteudo("");
		t07ctdService.save(t07ctd);
		
		return t07txt;
	}
	
	public void updSpacedRevision(T07TXT t07txt) {
		revisionHelper.updText(t07txt.getT05itm(), true);
	}

}
