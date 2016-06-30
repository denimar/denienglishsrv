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
	private String ds_titulo;
	
	@Column(name = "nr_seq", nullable = false)
	private short nrSeq;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dt_inclusao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao", nullable = false)
	private Date dt_alteracao;
	
	@Column(name = "bl_fazer_revisao", nullable = false)
	private boolean bl_fazer_revisao;

	public final int getCdTexto() {
		return cdTexto;
	}

	public final void setCdTexto(int cdTexto) {
		this.cdTexto = cdTexto;
	}

	public final T05ITM getT05itm() {
		return t05itm;
	}

	public final void setT05itm(T05ITM t05itm) {
		this.t05itm = t05itm;
	}

	public final String getDs_titulo() {
		return ds_titulo;
	}

	public final void setDs_titulo(String ds_titulo) {
		this.ds_titulo = ds_titulo;
	}

	public final short getNrSeq() {
		return nrSeq;
	}

	public final void setNrSeq(short nrSeq) {
		this.nrSeq = nrSeq;
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

	public final boolean isBl_fazer_revisao() {
		return bl_fazer_revisao;
	}

	public final void setBl_fazer_revisao(boolean bl_fazer_revisao) {
		this.bl_fazer_revisao = bl_fazer_revisao;
	}

}
