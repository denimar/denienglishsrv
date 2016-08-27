package com.denimar.denienglishsrv.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/*------------------------------------------
Pronuncia
-------------------------------------------*/

@Entity
@Table(name = "t51prn", uniqueConstraints = @UniqueConstraint(columnNames = {"ds_expressao" }))
public class T51PRN implements java.io.Serializable {

	@Id
	@Column(name = "cd_pronuncia")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cdPronuncia;
	
	@Column(name = "ds_expressao", nullable = false)
	private String dsExpressao;
	
	@Column(name = "bl_aprendido", nullable = false)
	private boolean blAprendido = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dtInclusao = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao", nullable = false)
	private Date dtAlteracao = new Date();

	public int getCdPronuncia() {
		return cdPronuncia;
	}

	public void setCdPronuncia(int cdPronuncia) {
		this.cdPronuncia = cdPronuncia;
	}

	public String getDsExpressao() {
		return dsExpressao;
	}

	public void setDsExpressao(String dsExpressao) {
		this.dsExpressao = dsExpressao;
	}

	public boolean isBlAprendido() {
		return blAprendido;
	}

	public void setBlAprendido(boolean blAprendido) {
		this.blAprendido = blAprendido;
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
