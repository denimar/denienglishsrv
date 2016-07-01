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
	private int cdArqExtraTexto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_texto")
	private T07TXT t07txt;
	
	@Column(name = "ds_arq_extra", nullable = false, length = 80)
	private String dsArqExtra;
	
	@Column(name = "tp_arq_extra", nullable = false)
	private ENUM_TIPO_ARQUIVO_EXTRA tpArqExtra;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inclusao", nullable = false)
	private Date dtInclusao;
	
	@Column(name = "ds_id_google_drive", length = 45)
	private String dsIdGoogleDrive;

	public int getCdArqExtraTexto() {
		return cdArqExtraTexto;
	}

	public void setCdArqExtraTexto(int cdArqExtraTexto) {
		this.cdArqExtraTexto = cdArqExtraTexto;
	}

	public T07TXT getT07txt() {
		return t07txt;
	}

	public void setT07txt(T07TXT t07txt) {
		this.t07txt = t07txt;
	}

	public String getDsArqExtra() {
		return dsArqExtra;
	}

	public void setDsArqExtra(String dsArqExtra) {
		this.dsArqExtra = dsArqExtra;
	}

	public ENUM_TIPO_ARQUIVO_EXTRA getTpArqExtra() {
		return tpArqExtra;
	}

	public void setTpArqExtra(ENUM_TIPO_ARQUIVO_EXTRA tpArqExtra) {
		this.tpArqExtra = tpArqExtra;
	}

	public Date getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(Date dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public String getDsIdGoogleDrive() {
		return dsIdGoogleDrive;
	}

	public void setDsIdGoogleDrive(String dsIdGoogleDrive) {
		this.dsIdGoogleDrive = dsIdGoogleDrive;
	}


}