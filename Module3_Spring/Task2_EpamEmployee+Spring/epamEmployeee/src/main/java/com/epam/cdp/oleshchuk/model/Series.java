package com.epam.cdp.oleshchuk.model;

import java.util.List;
import java.util.Map;


public class Series {
	private String name;
	private String _jrid;
	private List<Map<String, Object>> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String get_jrid() {
		return _jrid;
	}

	public void set_jrid(String _jrid) {
		this._jrid = _jrid;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
}
