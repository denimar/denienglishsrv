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
	private int cdTextoConteudo;	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_texto", nullable = false)
	private T07TXT t07txt;
	
	@Column(name = "tx_conteudo")
	private String txConteudo;

	public int getCdTextoConteudo() {
		return cdTextoConteudo;
	}

	public void setCdTextoConteudo(int cdTextoConteudo) {
		this.cdTextoConteudo = cdTextoConteudo;
	}

	public T07TXT getT07txt() {
		return t07txt;
	}

	public void setT07txt(T07TXT t07txt) {
		this.t07txt = t07txt;
	}

	public String getTxConteudo() {
		return txConteudo;
	}

	public void setTxConteudo(String txConteudo) {
		this.txConteudo = txConteudo;
	}

	
}
