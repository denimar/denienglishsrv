package com.denimar.denienglishsrv.helper;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denimar.denienglishsrv.domain.enums.VIDEO_TYPE_ENUM;
import com.denimar.denienglishsrv.service.T08VDOService;

@Component
public class VideoHelper {
	
	@Autowired
	private T08VDOService t08vdoService;
	@Autowired
	private GeneralHelper generalHelper;	

	public byte[] getVideoImage(VIDEO_TYPE_ENUM tpVideo, String idVideo) throws IOException {
		
		String urlImage = null;
		if (tpVideo == VIDEO_TYPE_ENUM.YOUTUBE) {
			urlImage = "http://img.youtube.com/vi/" + idVideo + "/default.jpg";
		} else if (tpVideo == VIDEO_TYPE_ENUM.GOOGLE_DRIVE) {
			urlImage = "https://docs.google.com/vt?id=" + idVideo;
		}
		
		return generalHelper.downloadFileBitesArray(new URL(urlImage));
	}
	
	public byte[] getBytesFromUriImagem(String uriImagemBase64) throws IOException {
	    String uriImagem = uriImagemBase64;
	    uriImagem = uriImagem.substring("data:image/png;base64,".length());
	    Base64 decoder = new Base64();	    
	    return decoder.decode(uriImagem);  
	}	

}
