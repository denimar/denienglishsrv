package com.denimar.denienglishsrv.dto;

public class SubtitleRequestDTO {

	private int cd_item_subtitle;
	private int cd_video;		
	private long nr_start;
	private long nr_end;
	private String ds_texto;
	
	public int getCd_item_subtitle() {
		return cd_item_subtitle;
	}
	public void setCd_item_subtitle(int cd_item_subtitle) {
		this.cd_item_subtitle = cd_item_subtitle;
	}
	public int getCd_video() {
		return cd_video;
	}
	public void setCd_video(int cd_video) {
		this.cd_video = cd_video;
	}
	public long getNr_start() {
		return nr_start;
	}
	public void setNr_start(long nr_start) {
		this.nr_start = nr_start;
	}
	public long getNr_end() {
		return nr_end;
	}
	public void setNr_end(long nr_end) {
		this.nr_end = nr_end;
	}
	public String getDs_texto() {
		return ds_texto;
	}
	public void setDs_texto(String ds_texto) {
		this.ds_texto = ds_texto;
	}
	
}
