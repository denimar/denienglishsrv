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
	private int cd_pronuncia;
	
	@Column(name = "ds_expressao", nullable = false)
	private String ds_expressao;
	
	@Column(name = "bl_aprendido", nullable = false)
	private boolean bl_aprendido = false;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dt_inclusao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao", nullable = false)
	private Date dt_alteracao;
	
	
	public final int getCd_pronuncia() {
		return cd_pronuncia;
	}

	public final void setCd_pronuncia(int cd_pronuncia) {
		this.cd_pronuncia = cd_pronuncia;
	}

	public final String getDs_expressao() {
		return ds_expressao;
	}

	public final void setDs_expressao(String ds_expressao) {
		this.ds_expressao = ds_expressao;
	}

	public final boolean isBl_aprendido() {
		return bl_aprendido;
	}

	public final void setBl_aprendido(boolean bl_aprendido) {
		this.bl_aprendido = bl_aprendido;
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

}
