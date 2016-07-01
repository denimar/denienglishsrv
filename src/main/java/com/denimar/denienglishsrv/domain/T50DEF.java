package com.denimar.denienglishsrv.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*------------------------------------------
Dicionario - Definições
-------------------------------------------*/

@Entity
@Table(name = "t50def")
public class T50DEF {
	
	@Id
	@Column(name = "cd_dicionario_definicao")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cdDefinicaoDicionario;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_dicionario", nullable = false)
	private T50DCI t50dci;
	
	@Column(name = "tx_definicao")
	private String txDefinicao;

	public int getCdDefinicaoDicionario() {
		return cdDefinicaoDicionario;
	}

	public void setCdDefinicaoDicionario(int cdDefinicaoDicionario) {
		this.cdDefinicaoDicionario = cdDefinicaoDicionario;
	}

	public T50DCI getT50dci() {
		return t50dci;
	}

	public void setT50dci(T50DCI t50dci) {
		this.t50dci = t50dci;
	}

	public String getTxDefinicao() {
		return txDefinicao;
	}

	public void setTxDefinicao(String txDefinicao) {
		this.txDefinicao = txDefinicao;
	}


}
