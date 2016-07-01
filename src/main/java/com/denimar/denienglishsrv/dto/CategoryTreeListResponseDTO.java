package com.denimar.denienglishsrv.dto;

public class CategoryTreeListResponseDTO {
	
	private int id;
	private String text;
	private CategoryTreeListResponseDTO[] children;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public CategoryTreeListResponseDTO[] getChildren() {
		return children;
	}
	public void setChildren(CategoryTreeListResponseDTO[] children) {
		this.children = children;
	}

}
