package com.denimar.denienglishsrv.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*------------------------------------------
Videos
-------------------------------------------*/

@Entity
@Table(name = "t08vdo")
public class T08VDO implements java.io.Serializable {

	@Id
	@Column(name = "cd_video")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cdVideo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_item", nullable = false)
	private T05ITM t05itm;
	
	@Column(name = "tx_comentarios")
	private String txComentarios;
	
	@Column(name = "ds_url", length = 255)
	private String dsUrl;
	
	@Column(name = "bl_fazer_revisao", nullable = false)
	private boolean blFazerRevisao = false;

	public int getCdVideo() {
		return cdVideo;
	}

	public void setCdVideo(int cdVideo) {
		this.cdVideo = cdVideo;
	}

	public T05ITM getT05itm() {
		return t05itm;
	}

	public void setT05itm(T05ITM t05itm) {
		this.t05itm = t05itm;
	}

	public String getTxComentarios() {
		return txComentarios;
	}

	public void setTxComentarios(String txComentarios) {
		this.txComentarios = txComentarios;
	}

	public String getDsUrl() {
		return dsUrl;
	}

	public void setDsUrl(String dsUrl) {
		this.dsUrl = dsUrl;
	}

	public boolean isBlFazerRevisao() {
		return blFazerRevisao;
	}

	public void setBlFazerRevisao(boolean blFazerRevisao) {
		this.blFazerRevisao = blFazerRevisao;
	}
	
}
