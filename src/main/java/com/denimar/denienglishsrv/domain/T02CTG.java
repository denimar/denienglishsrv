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
@Table(name = "t02ctg", uniqueConstraints = @UniqueConstraint(columnNames = {"cd_tipo", "cd_categoria_pai", "ds_categoria" }))
public class T02CTG implements java.io.Serializable {
	
	@Id
	@Column(name = "cd_categoria")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cdCategoria;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_categoria_pai")
	private T02CTG t02ctg;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_tipo", nullable = false)
	private T01TPO t01tpo;
	
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

	public T02CTG(int cdCategoria, T01TPO t01tpo, String dsCategoria, Date dhInclusao, Date dhAlteracao) {
		this.cdCategoria = cdCategoria;
		this.t01tpo = t01tpo;
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

	public T01TPO getT01tpo() {
		return t01tpo;
	}

	public void setT01tpo(T01TPO t01tpo) {
		this.t01tpo = t01tpo;
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
