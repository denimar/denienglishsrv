package com.denimar.denienglishsrv.dto;

public class CreateVideoRequestDTO {
	
	private int cd_categoria;
	private String ds_item;
	private String ds_url;
	private String bt_imagem_item;
	
	public int getCd_categoria() {
		return cd_categoria;
	}
	public void setCd_categoria(int cd_categoria) {
		this.cd_categoria = cd_categoria;
	}
	public String getDs_item() {
		return ds_item;
	}
	public void setDs_item(String ds_item) {
		this.ds_item = ds_item;
	}
	public String getDs_url() {
		return ds_url;
	}
	public void setDs_url(String ds_url) {
		this.ds_url = ds_url;
	}
	public String getBt_imagem_item() {
		return bt_imagem_item;
	}
	public void setBt_imagem_item(String bt_imagem_item) {
		this.bt_imagem_item = bt_imagem_item;
	}

}
