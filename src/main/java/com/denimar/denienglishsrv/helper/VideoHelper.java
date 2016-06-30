package com.denimar.denienglishsrv.helper;

import java.io.BufferedOutputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletResponse;

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
		t08vdo.setDs_url(ds_url);
		t08vdoService.save(t08vdo);
		
		return t08vdo;
	}
	
	public void getImagemBancoDados(HttpServletResponse response, byte[] bt_imagem) throws Exception {
		BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
        try {
        	output.write(bt_imagem);
        	output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public byte[] getBytesFromUriImagem(String uriImagemBase64) throws IOException {
	    String uriImagem = uriImagemBase64;
	    uriImagem = uriImagem.substring("data:image/png;base64,".length());
	    Base64 decoder = new Base64();	    
	    return decoder.decode(uriImagem);  
	}	

}
