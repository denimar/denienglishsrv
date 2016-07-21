package com.denimar.denienglishsrv.dto;

public class ExpressionResponseDTO {

	private int cdDicionario;
	private int cdPronuncia;
	private String dsExpressao;
	private int nrLevelOfLearning;
	
	public int getCdDicionario() {
		return cdDicionario;
	}
	public void setCdDicionario(int cdDicionario) {
		this.cdDicionario = cdDicionario;
	}
	public int getCdPronuncia() {
		return cdPronuncia;
	}
	public void setCdPronuncia(int cdPronuncia) {
		this.cdPronuncia = cdPronuncia;
	}
	public String getDsExpressao() {
		return dsExpressao;
	}
	public void setDsExpressao(String dsExpressao) {
		this.dsExpressao = dsExpressao;
	}
	public int getNrLevelOfLearning() {
		return nrLevelOfLearning;
	}
	public void setNrLevelOfLearning(int nrLevelOfLearning) {
		this.nrLevelOfLearning = nrLevelOfLearning;
	}
	
}
