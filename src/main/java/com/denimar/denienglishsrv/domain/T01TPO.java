package com.denimar.denienglishsrv.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t01tpo", uniqueConstraints = @UniqueConstraint(columnNames = "ds_tipo"))
public class T01TPO implements java.io.Serializable {

	@Id
	@Column(name = "cd_tipo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cdTipo;
	
	@Column(name = "ds_tipo", unique = true, nullable = false, length = 40)
	private String dsTipo;
	
	@Column(name = "ds_pagina_mostrar", length = 45)
	private String dsPaginaMostrar;

	public T01TPO() {
	}

	public T01TPO(int cdTipo, String dsTipo) {
		this.cdTipo = cdTipo;
		this.dsTipo = dsTipo;
	}

	public T01TPO(int cdTipo, String dsTipo, String dsPaginaMostrar) {
		this.cdTipo = cdTipo;
		this.dsTipo = dsTipo;
		this.dsPaginaMostrar = dsPaginaMostrar;
	}

	public int getCdTipo() {
		return cdTipo;
	}

	public void setCdTipo(int cdTipo) {
		this.cdTipo = cdTipo;
	}

	public String getDsTipo() {
		return dsTipo;
	}

	public void setDsTipo(String dsTipo) {
		this.dsTipo = dsTipo;
	}

	public String getDsPaginaMostrar() {
		return dsPaginaMostrar;
	}

	public void setDsPaginaMostrar(String dsPaginaMostrar) {
		this.dsPaginaMostrar = dsPaginaMostrar;
	}

}
