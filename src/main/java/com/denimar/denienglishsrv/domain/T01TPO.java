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
	private int cd_tipo;
	
	@Column(name = "ds_tipo", unique = true, nullable = false, length = 40)
	private String ds_tipo;
	
	@Column(name = "ds_pagina_mostrar", length = 45)
	private String ds_pagina_mostrar;

	public T01TPO() {
	}

	public T01TPO(int cd_tipo, String ds_tipo) {
		this.cd_tipo = cd_tipo;
		this.ds_tipo = ds_tipo;
	}

	public T01TPO(int cd_tipo, String dsTipo, String dsPaginaMostrar) {
		this.cd_tipo = cd_tipo;
		this.ds_tipo = dsTipo;
		this.ds_pagina_mostrar = dsPaginaMostrar;
	}

	public int getCd_tipo() {
		return cd_tipo;
	}

	public void setCd_tipo(int cd_tipo) {
		this.cd_tipo = cd_tipo;
	}

	public String getDs_tipo() {
		return ds_tipo;
	}

	public void setDs_tipo(String ds_tipo) {
		this.ds_tipo = ds_tipo;
	}

	public String getDs_pagina_mostrar() {
		return ds_pagina_mostrar;
	}

	public void setDs_pagina_mostrar(String ds_pagina_mostrar) {
		this.ds_pagina_mostrar = ds_pagina_mostrar;
	}

}
