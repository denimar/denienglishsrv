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

/*------------------------------------------
Items - Revisoes Espacadas
-------------------------------------------*/

@Entity
@Table(name = "t05rev")
public class T05REV implements java.io.Serializable {

	@Id
	@Column(name = "cd_revisao_item")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cdRevisaoItem;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_item", nullable = false)
	private T05ITM t05itm;
	
	@Column(name = "cd_texto")
	private Integer cdTexto;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_revisao", nullable = false)
	private Date dhRevisao;

	public int getCdRevisaoItem() {
		return cdRevisaoItem;
	}

	public void setCdRevisaoItem(int cdRevisaoItem) {
		this.cdRevisaoItem = cdRevisaoItem;
	}

	public T05ITM getT05itm() {
		return t05itm;
	}

	public void setT05itm(T05ITM t05itm) {
		this.t05itm = t05itm;
	}

	public Integer getCdTexto() {
		return cdTexto;
	}

	public void setCdTexto(Integer cdTexto) {
		this.cdTexto = cdTexto;
	}

	public Date getDhRevisao() {
		return dhRevisao;
	}

	public void setDhRevisao(Date dhRevisao) {
		this.dhRevisao = dhRevisao;
	}


}