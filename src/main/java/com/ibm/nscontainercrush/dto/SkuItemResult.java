package com.ibm.nscontainercrush.dto;

import java.util.List;

public class SkuItemResult extends BaseResponse{
	
	private List<SkuItem> skuItemList;

	public List<SkuItem> getSkuItemList() {
		return skuItemList;
	}

	public void setSkuItemList(List<SkuItem> skuItemList) {
		this.skuItemList = skuItemList;
	}

}
