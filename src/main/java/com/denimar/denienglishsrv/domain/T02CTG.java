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
	private int cd_categoria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_categoria_pai")
	private T02CTG t02ctg;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_tipo", nullable = false)
	private T01TPO t01tpo;
	
	@Column(name = "ds_categoria", nullable = false, length = 40)
	private String ds_categoria;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_inclusao", nullable = false)	
	private Date dh_inclusao = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_alteracao", nullable = false)
	private Date dh_alteracao = new Date();

	public T02CTG() {
	}

	public T02CTG(int cd_categoria, T01TPO t01tpo, String ds_categoria, Date dh_inclusao, Date dh_alteracao) {
		this.cd_categoria = cd_categoria;
		this.t01tpo = t01tpo;
		this.ds_categoria = ds_categoria;
		this.dh_inclusao = dh_inclusao;
		this.dh_alteracao = dh_alteracao;
	}

	public int getCd_categoria() {
		return cd_categoria;
	}

	public void setCd_categoria(int cd_categoria) {
		this.cd_categoria = cd_categoria;
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

	public String getDs_categoria() {
		return ds_categoria;
	}

	public void setDs_categoria(String ds_categoria) {
		this.ds_categoria = ds_categoria;
	}

	public Date getDh_inclusao() {
		return dh_inclusao;
	}

	public void setDh_inclusao(Date dt_inclusao) {
		this.dh_inclusao = dt_inclusao;
	}

	public Date getDh_alteracao() {
		return dh_alteracao;
	}

	public void setDh_alteracao(Date dt_alteracao) {
		this.dh_alteracao = dt_alteracao;
	}

}
