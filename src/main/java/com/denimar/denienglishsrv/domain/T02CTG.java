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

@Entity
@Table(name = "t02ctg", uniqueConstraints = @UniqueConstraint(columnNames = {"cd_categoria_pai", "ds_categoria" }))
public class T02CTG implements java.io.Serializable {
	
	@Id
	@Column(name = "cd_categoria")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cdCategoria;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_categoria_pai")
	private T02CTG t02ctg;
	
	@Column(name = "ds_categoria", nullable = false, length = 40)
	private String dsCategoria;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_inclusao", nullable = false)	
	private Date dhInclusao = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_alteracao", nullable = false)
	private Date dhAlteracao = new Date();

	public T02CTG() {
	}

	public T02CTG(int cdCategoria, String dsCategoria, Date dhInclusao, Date dhAlteracao) {
		this.cdCategoria = cdCategoria;
		this.dsCategoria = dsCategoria;
		this.dhInclusao = dhInclusao;
		this.dhAlteracao = dhAlteracao;
	}

	public int getCdCategoria() {
		return cdCategoria;
	}

	public void setCdCategoria(int cdCategoria) {
		this.cdCategoria = cdCategoria;
	}

	public T02CTG getT02ctg() {
		return t02ctg;
	}

	public void setT02ctg(T02CTG t02ctg) {
		this.t02ctg = t02ctg;
	}

	public String getDsCategoria() {
		return dsCategoria;
	}

	public void setDsCategoria(String dsCategoria) {
		this.dsCategoria = dsCategoria;
	}

	public Date getDhInclusao() {
		return dhInclusao;
	}

	public void setDhInclusao(Date dhInclusao) {
		this.dhInclusao = dhInclusao;
	}

	public Date getDhAlteracao() {
		return dhAlteracao;
	}

	public void setDhAlteracao(Date dhAlteracao) {
		this.dhAlteracao = dhAlteracao;
	}


}
