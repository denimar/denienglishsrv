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
	private int cdImagem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_item")
	private T05ITM t05itm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_video")
	private T08VDO t08vdo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_categoria")
	private T02CTG t02ctg;
	
	@Column(name = "bt_imagem")
	private byte[] btImagem;

	public int getCdImagem() {
		return cdImagem;
	}

	public void setCdImagem(int cdImagem) {
		this.cdImagem = cdImagem;
	}

	public T05ITM getT05itm() {
		return t05itm;
	}

	public void setT05itm(T05ITM t05itm) {
		this.t05itm = t05itm;
	}

	public T08VDO getT08vdo() {
		return t08vdo;
	}

	public void setT08vdo(T08VDO t08vdo) {
		this.t08vdo = t08vdo;
	}

	public byte[] getBtImagem() {
		return btImagem;
	}

	public void setBtImagem(byte[] btImagem) {
		this.btImagem = btImagem;
	}

	public T02CTG getT02ctg() {
		return t02ctg;
	}

	public void setT02ctg(T02CTG t02ctg) {
		this.t02ctg = t02ctg;
	}
	

}
