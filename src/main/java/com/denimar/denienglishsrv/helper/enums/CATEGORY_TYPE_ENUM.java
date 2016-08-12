package com.denimar.denienglishsrv.helper.enums;

public enum CATEGORY_TYPE_ENUM {
	
	TEXT(275),
	
	VIDEO(276);
	
	private int categoryType;
	
	CATEGORY_TYPE_ENUM(int categoryType) {
		this.categoryType = categoryType;
	}
	
    public int getCategoryType() {
        return this.categoryType;
    }		

}
