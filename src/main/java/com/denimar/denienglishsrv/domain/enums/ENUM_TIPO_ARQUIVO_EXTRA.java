package com.denimar.denienglishsrv.domain.enums;

public enum ENUM_TIPO_ARQUIVO_EXTRA {
	
	AUDIO(1),
	
	VIDEO(2);
	
	private int tipoArquivoExtra;
	
	ENUM_TIPO_ARQUIVO_EXTRA(int tipoArquivoExtra) {
		this.tipoArquivoExtra = tipoArquivoExtra;
	}
	
    public int getTipoArquivoExtra() {
        return this.tipoArquivoExtra;
    }	
	
}
