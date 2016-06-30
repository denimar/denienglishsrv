package com.denimar.denienglishsrv.domain.enums;

public enum ENUM_TIPO_VIDEO {

	GOOGLE_DRIVE(1),
	
	YOUTUBE(2),
	
	VIMEO(3);	
	
	private int tipoVideo;
	
	ENUM_TIPO_VIDEO(int tipoVideo) {
		this.tipoVideo = tipoVideo;
	}
	
    public int getTipoVideo() {
        return this.tipoVideo;
    }		
	
}
