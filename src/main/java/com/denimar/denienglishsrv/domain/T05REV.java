package com.denimar.denienglishsrv.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*------------------------------------------
Items - Revisoes Espacadas
-------------------------------------------*/

@Entity
@Table(name = "t05rev")
public class T05REV implements java.io.Serializable {

	@Id
	@Column(name = "cd_revisao_item")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cd_revisao_item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_item", nullable = false)
	private T05ITM t05itm;
	
	@Column(name = "cd_texto")
	private Integer cd_texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_revisao", nullable = false)
	private Date dhrevisao;

	public final int getCd_revisao_item() {
		return cd_revisao_item;
	}

	public final void setCd_revisao_item(int cd_revisao_item) {
		this.cd_revisao_item = cd_revisao_item;
	}

	public final T05ITM getT05itm() {
		return t05itm;
	}

	public final void setT05itm(T05ITM t05itm) {
		this.t05itm = t05itm;
	}

	public final Integer getCd_texto() {
		return cd_texto;
	}

	public final void setCd_texto(Integer cd_texto) {
		this.cd_texto = cd_texto;
	}

	public Date getDhrevisao() {
		return dhrevisao;
	}

	public void setDhrevisao(Date dhrevisao) {
		this.dhrevisao = dhrevisao;
	}

}