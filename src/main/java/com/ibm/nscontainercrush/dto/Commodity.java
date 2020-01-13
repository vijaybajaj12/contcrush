package com.ibm.nscontainercrush.dto;

public class Commodity {
	
	private String commodityId;
	private String commodityName;
	private String imageUrl;
	
	public Commodity(String commodityId, String commodityName) {
		super();
		this.commodityId = commodityId;
		this.commodityName = commodityName;
	}
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
