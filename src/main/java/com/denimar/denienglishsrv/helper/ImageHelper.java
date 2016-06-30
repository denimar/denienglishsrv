package com.denimar.denienglishsrv.helper;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

public class ImageHelper {

	public void getImagemBancoDados(HttpServletResponse response, byte[] bt_imagem) throws Exception {
		//String bt_imagem = BeanUtils.getProperty(objeto, "bt_imagem");
		//byte[] decoded = org.apache.commons. commons.codec.binary.Base64.decodeBase64(bt_imagem.getBytes());
		
		BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
        try {
        	output.write(bt_imagem);
        	output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static byte[] getBytesFromUriImagem(String uriImagemBase64) throws IOException {
	    String uriImagem = uriImagemBase64;
	    uriImagem = uriImagem.substring("data:image/png;base64,".length());
	    Base64 decoder = new Base64();	    
	    return decoder.decode(uriImagem);  
	}
	
	
}
