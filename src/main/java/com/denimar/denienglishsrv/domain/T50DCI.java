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
Dicionario
-------------------------------------------*/

@Entity
@Table(name = "t50dci", uniqueConstraints = @UniqueConstraint(columnNames = {"ds_expressao" }))
public class T50DCI implements java.io.Serializable {

	@Id
	@Column(name = "cd_dicionario")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cdDicionario;
	
	@Column(name = "ds_expressao", nullable = false)
	private String dsExpressao;
	
	@Column(name = "ds_tags", length = 100)
	private String dsTags;
	
	@Column(name = "bl_aprendido", nullable = false)
	private boolean blAprendido;

	@Column(name = "ds_expressoes")
	private String dsExpressoes;
	
	@Column(name = "nr_level_of_learning")
	private int nrLevelOfLearning;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dtInclusao = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao", nullable = false)
	private Date dtAlteracao = new Date();

	public int getCdDicionario() {
		return cdDicionario;
	}

	public void setCdDicionario(int cdDicionario) {
		this.cdDicionario = cdDicionario;
	}

	public String getDsExpressao() {
		return dsExpressao;
	}

	public void setDsExpressao(String dsExpressao) {
		this.dsExpressao = dsExpressao;
	}

	public String getDsTags() {
		return dsTags;
	}

	public void setDsTags(String dsTags) {
		this.dsTags = dsTags;
	}

	public boolean isBlAprendido() {
		return blAprendido;
	}

	public void setBlAprendido(boolean blAprendido) {
		this.blAprendido = blAprendido;
	}

	public String getDsExpressoes() {
		return dsExpressoes;
	}

	public void setDsExpressoes(String dsExpressoes) {
		this.dsExpressoes = dsExpressoes;
	}

	public int getNrLevelOfLearning() {
		return nrLevelOfLearning;
	}

	public void setNrLevelOfLearning(int nrLevelOfLearning) {
		this.nrLevelOfLearning = nrLevelOfLearning;
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
