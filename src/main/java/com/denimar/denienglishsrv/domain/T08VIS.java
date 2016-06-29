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
Videos - Itens que formarao os subtitles
-------------------------------------------*/

@Entity
@Table(name = "t08vis", uniqueConstraints = @UniqueConstraint(columnNames = {"cd_video", "nr_item_old" }))
public class T08VIS implements java.io.Serializable {

	@Id
	@Column(name = "cd_item_subtitle")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cd_item_subtitle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_video", nullable = false)
	private T08VDO t08vdo;
	
	@Column(name = "nr_item_old")
	private Integer nr_item_old;
	
	@Column(name = "nr_start", nullable = false, precision = 17, scale = 17)
	private double nr_start;
	
	@Column(name = "nr_end", nullable = false, precision = 17, scale = 17)
	private double nr_end;
	
	@Column(name = "ds_texto", nullable = false)
	private String ds_texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dt_inclusao = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao", nullable = false)
	private Date dt_alteracao = new Date();

	public final int getCd_item_subtitle() {
		return cd_item_subtitle;
	}

	public final void setCd_item_subtitle(int cd_item_subtitle) {
		this.cd_item_subtitle = cd_item_subtitle;
	}

	public final T08VDO getT08vdo() {
		return t08vdo;
	}

	public final void setT08vdo(T08VDO t08vdo) {
		this.t08vdo = t08vdo;
	}

	public final Integer getNr_item_old() {
		return nr_item_old;
	}

	public final void setNr_item_old(Integer nr_item_old) {
		this.nr_item_old = nr_item_old;
	}

	public final double getNr_start() {
		return nr_start;
	}

	public final void setNr_start(double nr_start) {
		this.nr_start = nr_start;
	}

	public final double getNr_end() {
		return nr_end;
	}

	public final void setNr_end(double nr_end) {
		this.nr_end = nr_end;
	}

	public final String getDs_texto() {
		return ds_texto;
	}

	public final void setDs_texto(String ds_texto) {
		this.ds_texto = ds_texto;
	}

	public final Date getDt_inclusao() {
		return dt_inclusao;
	}

	public final void setDt_inclusao(Date dt_inclusao) {
		this.dt_inclusao = dt_inclusao;
	}

	public final Date getDt_alteracao() {
		return dt_alteracao;
	}

	public final void setDt_alteracao(Date dt_alteracao) {
		this.dt_alteracao = dt_alteracao;
	}
	
}