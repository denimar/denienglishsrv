package com.denimar.denienglishsrv.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	private int cd_dicionario;
	
	@Column(name = "ds_expressao", nullable = false)
	private String ds_expressao;
	
	@Column(name = "ds_tags", length = 100)
	private String ds_tags;
	
	@Column(name = "bl_aprendido", nullable = false)
	private boolean bl_aprendido;

	@Column(name = "ds_expressoes")
	private String ds_expressoes;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dt_inclusao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao", nullable = false)
	private Date dt_alteracao;

	public int getCd_dicionario() {
		return cd_dicionario;
	}

	public void setCd_dicionario(int cd_dicionario) {
		this.cd_dicionario = cd_dicionario;
	}

	public String getDs_expressao() {
		return ds_expressao;
	}

	public void setDs_expressao(String ds_expressao) {
		this.ds_expressao = ds_expressao;
	}

	public String getDs_tags() {
		return ds_tags;
	}

	public void setDs_tags(String ds_tags) {
		this.ds_tags = ds_tags;
	}

	public boolean isBl_aprendido() {
		return bl_aprendido;
	}

	public void setBl_aprendido(boolean bl_aprendido) {
		this.bl_aprendido = bl_aprendido;
	}

	public String getDs_expressoes() {
		return ds_expressoes;
	}

	public void setDs_expressoes(String ds_expressoes) {
		this.ds_expressoes = ds_expressoes;
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
