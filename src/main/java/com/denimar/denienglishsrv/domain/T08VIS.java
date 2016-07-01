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
	private int cdItemSubtitle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_video", nullable = false)
	private T08VDO t08vdo;
	
	@Column(name = "nr_item_old")
	private Integer nrItemOld;
	
	@Column(name = "nr_start", nullable = false, precision = 17, scale = 17)
	private double nrStart;
	
	@Column(name = "nr_end", nullable = false, precision = 17, scale = 17)
	private double nrEnd;
	
	@Column(name = "ds_texto", nullable = false)
	private String dsTexto;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dtInclusao = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao", nullable = false)
	private Date dtAlteracao = new Date();

	public int getCdItemSubtitle() {
		return cdItemSubtitle;
	}

	public void setCdItemSubtitle(int cdItemSubtitle) {
		this.cdItemSubtitle = cdItemSubtitle;
	}

	public T08VDO getT08vdo() {
		return t08vdo;
	}

	public void setT08vdo(T08VDO t08vdo) {
		this.t08vdo = t08vdo;
	}

	public Integer getNrItemOld() {
		return nrItemOld;
	}

	public void setNrItemOld(Integer nrItemOld) {
		this.nrItemOld = nrItemOld;
	}

	public double getNrStart() {
		return nrStart;
	}

	public void setNrStart(double nrStart) {
		this.nrStart = nrStart;
	}

	public double getNrEnd() {
		return nrEnd;
	}

	public void setNrEnd(double nrEnd) {
		this.nrEnd = nrEnd;
	}

	public String getDsTexto() {
		return dsTexto;
	}

	public void setDsTexto(String dsTexto) {
		this.dsTexto = dsTexto;
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