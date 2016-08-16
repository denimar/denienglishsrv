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

import com.denimar.denienglishsrv.domain.enums.VIDEO_TYPE_ENUM;

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

	@Column(name = "tp_video")
	private VIDEO_TYPE_ENUM tpVideo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_item", nullable = false)
	private T05ITM t05itm;
	
	@Column(name = "tx_comentarios")
	private String txComentarios;
	
	@Column(name = "id_video", length = 50)
	private String idVideo;

	public int getCdVideo() {
		return cdVideo;
	}

	public VIDEO_TYPE_ENUM getTpVideo() {
		return tpVideo;
	}

	public void setTpVideo(VIDEO_TYPE_ENUM tpVideo) {
		this.tpVideo = tpVideo;
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

	public String getIdVideo() {
		return idVideo;
	}

	public void setIdVideo(String idVideo) {
		this.idVideo = idVideo;
	}
	
}
