package com.denimar.denienglishsrv.dto;

import com.denimar.denienglishsrv.domain.enums.VIDEO_TYPE_ENUM;

public class CreateVideoRequestDTO {
	
	private int cd_categoria;
	private VIDEO_TYPE_ENUM tp_video;
	private String id_video;	
	private String ds_item;
	
	public int getCd_categoria() {
		return cd_categoria;
	}
	public void setCd_categoria(int cd_categoria) {
		this.cd_categoria = cd_categoria;
	}
	
	public VIDEO_TYPE_ENUM getTp_video() {
		return tp_video;
	}
	public void setTp_video(VIDEO_TYPE_ENUM tp_video) {
		this.tp_video = tp_video;
	}
	
	public String getDs_item() {
		return ds_item;
	}
	public void setDs_item(String ds_item) {
		this.ds_item = ds_item;
	}
	public String getId_Video() {
		return id_video;
	}
	public void setId_video(String id_video) {
		this.id_video = id_video;
	}
}
