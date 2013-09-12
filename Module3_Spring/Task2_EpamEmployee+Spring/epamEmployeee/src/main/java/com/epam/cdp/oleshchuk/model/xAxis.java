package com.epam.cdp.oleshchuk.model;

import java.util.List;

public class xAxis {
	private List<String> categories;

	public xAxis(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
}
