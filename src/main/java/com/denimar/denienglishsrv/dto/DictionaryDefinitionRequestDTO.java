package com.denimar.denienglishsrv.dto;

public class DictionaryDefinitionRequestDTO {
	
	private int cd_dicionario;
	private String tx_definicao;
	
	public int getCd_dicionario() {
		return cd_dicionario;
	}
	public void setCd_dicionario(int cd_dicionario) {
		this.cd_dicionario = cd_dicionario;
	}
	public String getTx_definicao() {
		return tx_definicao;
	}
	public void setTx_definicao(String tx_definicao) {
		this.tx_definicao = tx_definicao;
	}

}
