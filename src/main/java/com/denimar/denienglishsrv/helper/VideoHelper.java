package com.denimar.denienglishsrv.helper;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.service.T08VDOService;

@Component
public class VideoHelper {
	
	@Autowired	 
	private T08VDOService t08vdoService;
	
	public T08VDO createVideo(T05ITM t05itm, String ds_url) {
		T08VDO t08vdo = new T08VDO();
		t08vdo.setT05itm(t05itm);
		t08vdo.setDsUrl(ds_url);
		t08vdoService.save(t08vdo);
		return t08vdo;
	}
	
	public byte[] getBytesFromUriImagem(String uriImagemBase64) throws IOException {
	    String uriImagem = uriImagemBase64;
	    uriImagem = uriImagem.substring("data:image/png;base64,".length());
	    Base64 decoder = new Base64();	    
	    return decoder.decode(uriImagem);  
	}	

}
