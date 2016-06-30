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

import com.denimar.denienglishsrv.domain.enums.ENUM_TIPO_ARQUIVO_EXTRA;

/*------------------------------------------
Textos - Arquivos Extras
-------------------------------------------*/

@Entity
@Table(name = "t07aet")
public class T07AET implements java.io.Serializable {

	@Id
	@Column(name = "cd_arq_extra_texto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cd_arq_extra_texto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_texto")
	private T07TXT t07txt;
	
	@Column(name = "ds_arq_extra", nullable = false, length = 80)
	private String ds_arq_extra;
	
	@Column(name = "tp_arq_extra", nullable = false)
	private ENUM_TIPO_ARQUIVO_EXTRA tp_arq_extra;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dt_inclusao;
	
	@Column(name = "ds_id_google_drive", length = 45)
	private String ds_id_google_drive;

	public final int getCd_arq_extra_texto() {
		return cd_arq_extra_texto;
	}

	public final void setCd_arq_extra_texto(int cd_arq_extra_texto) {
		this.cd_arq_extra_texto = cd_arq_extra_texto;
	}

	public final T07TXT getT07txt() {
		return t07txt;
	}

	public final void setT07txt(T07TXT t07txt) {
		this.t07txt = t07txt;
	}

	public final String getDs_arq_extra() {
		return ds_arq_extra;
	}

	public final void setDs_arq_extra(String ds_arq_extra) {
		this.ds_arq_extra = ds_arq_extra;
	}

	public final ENUM_TIPO_ARQUIVO_EXTRA getTp_arq_extra() {
		return tp_arq_extra;
	}

	public final void setTp_arq_extra(ENUM_TIPO_ARQUIVO_EXTRA tp_arq_extra) {
		this.tp_arq_extra = tp_arq_extra;
	}

	public final Date getDt_inclusao() {
		return dt_inclusao;
	}

	public final void setDt_inclusao(Date dt_inclusao) {
		this.dt_inclusao = dt_inclusao;
	}

	public final String getDs_id_google_drive() {
		return ds_id_google_drive;
	}

	public final void setDs_id_google_drive(String ds_id_google_drive) {
		this.ds_id_google_drive = ds_id_google_drive;
	}


}