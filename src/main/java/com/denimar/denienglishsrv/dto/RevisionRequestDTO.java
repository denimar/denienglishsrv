package com.denimar.denienglishsrv.dto;

public class RevisionRequestDTO {
	
	private int cdCategoria;
	private long cdItem;
	private boolean blPendente;
	private int nrDays;
	
	public int getCdCategoria() {
		return cdCategoria;
	}
	public void setCdCategoria(int cdCategoria) {
		this.cdCategoria = cdCategoria;
	}
	public long getCdItem() {
		return cdItem;
	}
	public void setCdItem(long cdItem) {
		this.cdItem = cdItem;
	}
	public boolean isBlPendente() {
		return blPendente;
	}
	public void setBlPendente(boolean blPendente) {
		this.blPendente = blPendente;
	}
	public int getNrDays() {
		return nrDays;
	}
	public void setNrDays(int nrDays) {
		this.nrDays = nrDays;
	}

}
