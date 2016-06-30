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
	private int cd_video;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_item", nullable = false)
	private T05ITM t05itm;
	
	@Column(name = "tx_comentarios")
	private String tx_comentarios;
	
	@Column(name = "ds_url", length = 255)
	private String ds_url;
	
	@Column(name = "bl_fazer_revisao", nullable = false)
	private boolean bl_fazer_revisao = false;
	
	public final int getCd_video() {
		return cd_video;
	}

	public final void setCd_video(int cd_video) {
		this.cd_video = cd_video;
	}

	public final T05ITM getT05itm() {
		return t05itm;
	}

	public final void setT05itm(T05ITM t05itm) {
		this.t05itm = t05itm;
	}

	public final String getTx_comentarios() {
		return tx_comentarios;
	}

	public final void setTx_comentarios(String tx_comentarios) {
		this.tx_comentarios = tx_comentarios;
	}

	public String getDs_url() {
		return ds_url;
	}

	public void setDs_url(String ds_url) {
		this.ds_url = ds_url;
	}

	public final boolean isBl_fazer_revisao() {
		return bl_fazer_revisao;
	}

	public final void setBl_fazer_revisao(boolean bl_fazer_revisao) {
		this.bl_fazer_revisao = bl_fazer_revisao;
	}
	
}
