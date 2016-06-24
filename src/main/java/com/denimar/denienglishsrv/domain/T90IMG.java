package com.denimar.denienglishsrv.domain;

import java.io.Serializable;

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
Imagens
-------------------------------------------*/

@Entity
@Table(name = "t90img")
public class T90IMG implements Serializable {
	
	@Id
	@Column(name = "cd_imagem")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cd_imagem;
	
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "cd_tipo")
	private T01TPO t01tpo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_item")
	private T05ITM t05itm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_video")
	private T08VDO t08vdo;
	
	@Column(name = "bt_imagem")
	private byte[] bt_imagem;
	
	public final int getCd_imagem() {
		return cd_imagem;
	}

	public final void setCd_imagem(int cd_imagem) {
		this.cd_imagem = cd_imagem;
	}

	public final T01TPO getT01tpo() {
		return t01tpo;
	}

	public final void setT01tpo(T01TPO t01tpo) {
		this.t01tpo = t01tpo;
	}

	public final T05ITM getT05itm() {
		return t05itm;
	}

	public final void setT05itm(T05ITM t05itm) {
		this.t05itm = t05itm;
	}

	public T08VDO getT08vdo() {
		return t08vdo;
	}

	public void setT08vdo(T08VDO t08vdo) {
		this.t08vdo = t08vdo;
	}
	
	public final byte[] getBt_imagem() {
		return bt_imagem;
	}

	public final void setBt_imagem(byte[] bt_imagem) {
		this.bt_imagem = bt_imagem;
	}

}
