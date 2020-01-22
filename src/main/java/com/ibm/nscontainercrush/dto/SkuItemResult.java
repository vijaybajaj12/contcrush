package com.ibm.nscontainercrush.dto;

import java.util.ArrayList;
import java.util.List;

public class SkuItemResult extends BaseResponse{
	
	private List<SkuItem> skuItemList = new ArrayList<>(); //send empty result for UI to display message

	public List<SkuItem> getSkuItemList() {
		return skuItemList;
	}

	public void setSkuItemList(List<SkuItem> skuItemList) {
		this.skuItemList = skuItemList;
	}

}
