package com.ibm.nscontainercrush.dto;

import java.util.List;

public class KeywordSearchDto {
	
	private List<String> brands;
	private List<String> genders;
	private List<String> colors;
	private List<String> sizes;
	private List<String> commodities;
	
	public List<String> getBrands() {
		return brands;
	}
	public void setBrands(List<String> brands) {
		this.brands = brands;
	}
	public List<String> getGenders() {
		return genders;
	}
	public void setGenders(List<String> genders) {
		this.genders = genders;
	}
	public List<String> getColors() {
		return colors;
	}
	public void setColors(List<String> colors) {
		this.colors = colors;
	}
	public List<String> getSizes() {
		return sizes;
	}
	public void setSizes(List<String> sizes) {
		this.sizes = sizes;
	}
	public List<String> getCommodities() {
		return commodities;
	}
	public void setCommodities(List<String> commodities) {
		this.commodities = commodities;
	}
	
}
