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
import javax.persistence.UniqueConstraint;

/*------------------------------------------
Items
-------------------------------------------*/

@Entity
@Table(name = "t05itm", uniqueConstraints = @UniqueConstraint(columnNames = {"cd_categoria", "ds_item" }))
public class T05ITM implements java.io.Serializable {

	@Id
	@Column(name = "cd_item")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cdItem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_categoria", nullable = false)
	private T02CTG t02ctg;
	
	@Column(name = "ds_item", nullable = false)
	private String dsItem;
	
	@Column(name = "bl_fazer_revisao", nullable = false)
	private boolean blFazerRevisao;

	@Column(name = "bl_favorite", nullable = false)
	private boolean blFavorite = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dtInclusao = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao", nullable = false)
	private Date dtAlteracao = new Date();

	public long getCdItem() {
		return cdItem;
	}

	public void setCdItem(long cdItem) {
		this.cdItem = cdItem;
	}

	public T02CTG getT02ctg() {
		return t02ctg;
	}

	public void setT02ctg(T02CTG t02ctg) {
		this.t02ctg = t02ctg;
	}

	public String getDsItem() {
		return dsItem;
	}

	public void setDsItem(String dsItem) {
		this.dsItem = dsItem;
	}

	public boolean isBlFazerRevisao() {
		return blFazerRevisao;
	}

	public void setBlFazerRevisao(boolean blFazerRevisao) {
		this.blFazerRevisao = blFazerRevisao;
	}

	public Date getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(Date dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	
}
