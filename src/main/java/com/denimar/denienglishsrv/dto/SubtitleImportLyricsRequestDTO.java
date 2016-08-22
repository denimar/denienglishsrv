package com.denimar.denienglishsrv.dto;

public class SubtitleImportLyricsRequestDTO {

	private long cdItem;
	private String lyrics;
	
	public long getCdItem() {
		return cdItem;
	}
	public void setCdItem(long cdItem) {
		this.cdItem = cdItem;
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	
}
