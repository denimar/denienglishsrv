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
	private int cd_definicao_dicionario;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_dicionario", nullable = false)
	private T50DCI t50dci;
	
	@Column(name = "tx_definicao")
	private String tx_definicao;

	public int getCd_definicao_dicionario() {
		return cd_definicao_dicionario;
	}

	public void setCd_definicao_dicionario(int cd_definicao_dicionario) {
		this.cd_definicao_dicionario = cd_definicao_dicionario;
	}

	public T50DCI getT50dci() {
		return t50dci;
	}

	public void setT50dci(T50DCI t50dci) {
		this.t50dci = t50dci;
	}

	public String getTx_definicao() {
		return tx_definicao;
	}

	public void setTx_definicao(String tx_definicao) {
		this.tx_definicao = tx_definicao;
	}

}
