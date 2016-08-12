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
Textos
-------------------------------------------*/

@Entity
@Table(name = "t07txt")
public class T07TXT implements java.io.Serializable {

	@Id
	@Column(name = "cd_texto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cdTexto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_item", nullable = false)
	private T05ITM t05itm;
	
	@Column(name = "ds_titulo", nullable = false, length = 70)
	private String dsTitulo;
	
	@Column(name = "nr_seq", nullable = false)
	private short nrSeq;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dtInclusao = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao", nullable = false)
	private Date dtAlteracao = new Date();
	
	@Column(name = "bl_fazer_revisao", nullable = false)
	private boolean blFazerRevisao;

	public int getCdTexto() {
		return cdTexto;
	}

	public void setCdTexto(int cdTexto) {
		this.cdTexto = cdTexto;
	}

	public T05ITM getT05itm() {
		return t05itm;
	}

	public void setT05itm(T05ITM t05itm) {
		this.t05itm = t05itm;
	}

	public String getDsTitulo() {
		return dsTitulo;
	}

	public void setDsTitulo(String dsTitulo) {
		this.dsTitulo = dsTitulo;
	}

	public short getNrSeq() {
		return nrSeq;
	}

	public void setNrSeq(short nrSeq) {
		this.nrSeq = nrSeq;
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

	public boolean isBlFazerRevisao() {
		return blFazerRevisao;
	}

	public void setBlFazerRevisao(boolean blFazerRevisao) {
		this.blFazerRevisao = blFazerRevisao;
	}

}
