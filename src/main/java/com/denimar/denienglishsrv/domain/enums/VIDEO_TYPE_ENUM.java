package com.denimar.denienglishsrv.domain.enums;

public enum VIDEO_TYPE_ENUM {

	YOUTUBE(0),
	
	GOOGLE_DRIVE(1);
	
	private int videoType;
	
	VIDEO_TYPE_ENUM(int videoType) {
		this.videoType = videoType;
	}
	
    public int getVideoType() {
        return this.videoType;
    }		
	
}
