package com.denimar.denienglishsrv.vo;

import java.util.ArrayList;
import java.util.List;

public class RestDefaultReturn<T> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean success = false;
	private List<T> records;	
	private String message = "";
	private int total = -1;
	
	public RestDefaultReturn(boolean success, List<T> records, String message, int total) {
		this.success = success;
		this.records = records;
		this.message = message;
		this.total = total;
	}
	
	public RestDefaultReturn(boolean success, List<T> records, String message) {
		this.success = success;
		this.records = records;
		this.message = message;
		this.total = records.size();
	}
	
	public RestDefaultReturn(boolean success, List<T> records) {
		this.success = success;
		this.records = records;
		this.message = "";
		this.total = records.size();
	}
	
	public RestDefaultReturn(boolean success, T item) {
		this.success = success;
		this.total = 1;
		
		if (item instanceof String) { //message
			this.records = new ArrayList<T>();
			this.message = (String) item;
		} else {
			List<T> lista = new ArrayList<T>();			
			if (item == null) {
				this.total = 0;
			} else {
				lista.add(item);
			}
			this.records = lista;			
			this.message = "";
		}
	}

	public RestDefaultReturn(boolean success, String message) {
		this.success = success;
		this.records = new ArrayList<T>();		
		this.message = message;
		this.total = 0;
	}
	
	public RestDefaultReturn(boolean success) {
		this.success = success;
		this.records = new ArrayList<T>();		
		this.message = "";
		this.total = 0;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
