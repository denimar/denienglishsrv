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
Textos - Conteúdo -> O Conteúdo dos Textos propriamente dito está aqui
-------------------------------------------*/

@Entity
@Table(name = "t07ctd")
public class T07CTD {

	@Id
	@Column(name = "cd_texto_conteudo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cd_texto_conteudo;	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_texto", nullable = false)
	private T07TXT t07txt;
	
	@Column(name = "tx_conteudo")
	private String tx_conteudo;

	public int getCd_texto_conteudo() {
		return cd_texto_conteudo;
	}

	public void setCd_texto_conteudo(int cd_texto_conteudo) {
		this.cd_texto_conteudo = cd_texto_conteudo;
	}

	public T07TXT getT07txt() {
		return t07txt;
	}

	public void setT07txt(T07TXT t07txt) {
		this.t07txt = t07txt;
	}

	public String getTx_conteudo() {
		return tx_conteudo;
	}

	public void setTx_conteudo(String tx_conteudo) {
		this.tx_conteudo = tx_conteudo;
	}

	
}
