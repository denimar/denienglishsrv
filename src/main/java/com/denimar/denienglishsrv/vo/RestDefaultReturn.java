package com.denimar.denienglishsrv.vo;

import java.util.ArrayList;
import java.util.List;

public class RestDefaultReturn<T> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean success = false;
	private List<T> data;	
	private String message = "";
	private int total = -1;
	
	public RestDefaultReturn(boolean success, List<T> data, String message, int total) {
		this.success = success;
		this.data = data;
		this.message = message;
		this.total = total;
	}
	
	public RestDefaultReturn(boolean success, List<T> data, String message) {
		this.success = success;
		this.data = data;
		this.message = message;
		this.total = data.size();
	}
	
	public RestDefaultReturn(boolean success, List<T> data) {
		this.success = success;
		this.data = data;
		this.message = "";
		this.total = data.size();
	}
	
	public RestDefaultReturn(boolean success, T item) {
		this.success = success;
		this.total = 1;
		
		if (item instanceof String) { //message
			this.data = new ArrayList<T>();
			this.message = (String) item;
		} else {
			List<T> lista = new ArrayList<T>();			
			if (item == null) {
				this.total = 0;
			} else {
				lista.add(item);
			}
			this.data = lista;			
			this.message = "";
		}
	}

	public RestDefaultReturn(boolean success, String message) {
		this.success = success;
		this.data = new ArrayList<T>();		
		this.message = message;
		this.total = 0;
	}
	
	public RestDefaultReturn(boolean success) {
		this.success = success;
		this.data = new ArrayList<T>();		
		this.message = "";
		this.total = 0;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
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
