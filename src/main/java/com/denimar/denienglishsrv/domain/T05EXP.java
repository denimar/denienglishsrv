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
Items - Expressoes por Items (Used in Spaced Revision)
-------------------------------------------*/

@Entity
@Table(name = "t05exp")
public class T05EXP {
	@Id
	@Column(name = "cd_item_expressao")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cdItemExpressao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_item", nullable = false)
	private T05ITM t05itm;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_dicionario")
	private T50DCI t50dci;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_pronuncia")
	private T51PRN t51prn;	
	
	@Column(name = "bl_mostrar")
	private boolean blMostrar = true;
	
	public int getCdItemExpressao() {
		return cdItemExpressao;
	}

	public void setCdItemExpressao(int cdItemExpressao) {
		this.cdItemExpressao = cdItemExpressao;
	}

	public T05ITM getT05itm() {
		return t05itm;
	}

	public void setT05itm(T05ITM t05itm) {
		this.t05itm = t05itm;
	}

	public T50DCI getT50dci() {
		return t50dci;
	}

	public void setT50dci(T50DCI t50dci) {
		this.t50dci = t50dci;
	}

	public T51PRN getT51prn() {
		return t51prn;
	}

	public void setT51prn(T51PRN t51prn) {
		this.t51prn = t51prn;
	}

	public boolean isBlMostrar() {
		return blMostrar;
	}

	public void setBlMostrar(boolean blMostrar) {
		this.blMostrar = blMostrar;
	}

}
