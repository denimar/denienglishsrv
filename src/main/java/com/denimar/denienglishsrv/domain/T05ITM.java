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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_categoria", nullable = false)
	private T02CTG t02ctg;
	
	@Column(name = "ds_item", nullable = false)
	private String ds_item;
	
	@Column(name = "bl_fazer_revisao", nullable = false)
	private boolean blFazerRevisao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dt_inclusao = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao", nullable = false)
	private Date dt_alteracao = new Date();

	public long getCdItem() {
		return cdItem;
	}

	public void setCdItem(long cdItem) {
		this.cdItem = cdItem;
	}

	public String getDs_item() {
		return ds_item;
	}

	public void setDs_item(String ds_item) {
		this.ds_item = ds_item;
	}

	public boolean isBl_fazer_revisao() {
		return blFazerRevisao;
	}

	public void setBl_fazer_revisao(boolean bl_fazer_revisao) {
		this.blFazerRevisao = bl_fazer_revisao;
	}

	public T02CTG getT02ctg() {
		return t02ctg;
	}

	public void setT02ctg(T02CTG t02ctg) {
		this.t02ctg = t02ctg;
	}

	public Date getDt_inclusao() {
		return dt_inclusao;
	}

	public void setDt_inclusao(Date dt_inclusao) {
		this.dt_inclusao = dt_inclusao;
	}

	public Date getDt_alteracao() {
		return dt_alteracao;
	}

	public void setDt_alteracao(Date dt_alteracao) {
		this.dt_alteracao = dt_alteracao;
	}
	
}
