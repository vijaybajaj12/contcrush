package com.ibm.nscontainercrush.dto;

import java.util.Date;

public class SkuItem {
	
	private String styleItemNumber;
	private String styleDescription;
	private String styleLongDescription;
	private String styleCatalogueCategory;
	private String styleBrand;
	
	private String skuItemNumber;
	private String skuUnitOfMeasure;
	private String skuAttribute1;
	private String skuAttribute2;
	private String skuAttributeValue1;
	private String skuAttributeValue2;

	private String priceId;
	private String listPrice;
	private String discount;
	private String inStock;
	private Date priceEffectiveDate;
	private String imageRetrievalUrl;
	
	public String getStyleItemNumber() {
		return styleItemNumber;
	}
	public void setStyleItemNumber(String styleItemNumber) {
		this.styleItemNumber = styleItemNumber;
	}
	public String getStyleDescription() {
		return styleDescription;
	}
	public void setStyleDescription(String styleDescription) {
		this.styleDescription = styleDescription;
	}
	public String getStyleLongDescription() {
		return styleLongDescription;
	}
	public void setStyleLongDescription(String styleLongDescription) {
		this.styleLongDescription = styleLongDescription;
	}
	public String getStyleCatalogueCategory() {
		return styleCatalogueCategory;
	}
	public void setStyleCatalogueCategory(String styleCatalogueCategory) {
		this.styleCatalogueCategory = styleCatalogueCategory;
	}
	public String getStyleBrand() {
		return styleBrand;
	}
	public void setStyleBrand(String styleBrand) {
		this.styleBrand = styleBrand;
	}
	public String getSkuItemNumber() {
		return skuItemNumber;
	}
	public void setSkuItemNumber(String skuItemNumber) {
		this.skuItemNumber = skuItemNumber;
	}
	public String getSkuUnitOfMeasure() {
		return skuUnitOfMeasure;
	}
	public void setSkuUnitOfMeasure(String skuUnitOfMeasure) {
		this.skuUnitOfMeasure = skuUnitOfMeasure;
	}
	public String getSkuAttribute1() {
		return skuAttribute1;
	}
	public void setSkuAttribute1(String skuAttribute1) {
		this.skuAttribute1 = skuAttribute1;
	}
	public String getSkuAttribute2() {
		return skuAttribute2;
	}
	public void setSkuAttribute2(String skuAttribute2) {
		this.skuAttribute2 = skuAttribute2;
	}
	public String getSkuAttributeValue1() {
		return skuAttributeValue1;
	}
	public void setSkuAttributeValue1(String skuAttributeValue1) {
		this.skuAttributeValue1 = skuAttributeValue1;
	}
	public String getSkuAttributeValue2() {
		return skuAttributeValue2;
	}
	public void setSkuAttributeValue2(String skuAttributeValue2) {
		this.skuAttributeValue2 = skuAttributeValue2;
	}
	public String getPriceId() {
		return priceId;
	}
	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}
	public String getListPrice() {
		return listPrice;
	}
	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getInStock() {
		return inStock;
	}
	public void setInStock(String inStock) {
		this.inStock = inStock;
	}
	public Date getPriceEffectiveDate() {
		return priceEffectiveDate;
	}
	public void setPriceEffectiveDate(Date priceEffectiveDate) {
		this.priceEffectiveDate = priceEffectiveDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((imageRetrievalUrl == null) ? 0 : imageRetrievalUrl.hashCode());
		result = prime * result + ((inStock == null) ? 0 : inStock.hashCode());
		result = prime * result + ((listPrice == null) ? 0 : listPrice.hashCode());
		result = prime * result + ((priceEffectiveDate == null) ? 0 : priceEffectiveDate.hashCode());
		result = prime * result + ((priceId == null) ? 0 : priceId.hashCode());
		result = prime * result + ((skuAttribute1 == null) ? 0 : skuAttribute1.hashCode());
		result = prime * result + ((skuAttribute2 == null) ? 0 : skuAttribute2.hashCode());
		result = prime * result + ((skuAttributeValue1 == null) ? 0 : skuAttributeValue1.hashCode());
		result = prime * result + ((skuAttributeValue2 == null) ? 0 : skuAttributeValue2.hashCode());
		result = prime * result + ((skuItemNumber == null) ? 0 : skuItemNumber.hashCode());
		result = prime * result + ((skuUnitOfMeasure == null) ? 0 : skuUnitOfMeasure.hashCode());
		result = prime * result + ((styleBrand == null) ? 0 : styleBrand.hashCode());
		result = prime * result + ((styleCatalogueCategory == null) ? 0 : styleCatalogueCategory.hashCode());
		result = prime * result + ((styleDescription == null) ? 0 : styleDescription.hashCode());
		result = prime * result + ((styleItemNumber == null) ? 0 : styleItemNumber.hashCode());
		result = prime * result + ((styleLongDescription == null) ? 0 : styleLongDescription.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkuItem other = (SkuItem) obj;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (imageRetrievalUrl == null) {
			if (other.imageRetrievalUrl != null)
				return false;
		} else if (!imageRetrievalUrl.equals(other.imageRetrievalUrl))
			return false;
		if (inStock == null) {
			if (other.inStock != null)
				return false;
		} else if (!inStock.equals(other.inStock))
			return false;
		if (listPrice == null) {
			if (other.listPrice != null)
				return false;
		} else if (!listPrice.equals(other.listPrice))
			return false;
		if (priceEffectiveDate == null) {
			if (other.priceEffectiveDate != null)
				return false;
		} else if (!priceEffectiveDate.equals(other.priceEffectiveDate))
			return false;
		if (priceId == null) {
			if (other.priceId != null)
				return false;
		} else if (!priceId.equals(other.priceId))
			return false;
		if (skuAttribute1 == null) {
			if (other.skuAttribute1 != null)
				return false;
		} else if (!skuAttribute1.equals(other.skuAttribute1))
			return false;
		if (skuAttribute2 == null) {
			if (other.skuAttribute2 != null)
				return false;
		} else if (!skuAttribute2.equals(other.skuAttribute2))
			return false;
		if (skuAttributeValue1 == null) {
			if (other.skuAttributeValue1 != null)
				return false;
		} else if (!skuAttributeValue1.equals(other.skuAttributeValue1))
			return false;
		if (skuAttributeValue2 == null) {
			if (other.skuAttributeValue2 != null)
				return false;
		} else if (!skuAttributeValue2.equals(other.skuAttributeValue2))
			return false;
		if (skuItemNumber == null) {
			if (other.skuItemNumber != null)
				return false;
		} else if (!skuItemNumber.equals(other.skuItemNumber))
			return false;
		if (skuUnitOfMeasure == null) {
			if (other.skuUnitOfMeasure != null)
				return false;
		} else if (!skuUnitOfMeasure.equals(other.skuUnitOfMeasure))
			return false;
		if (styleBrand == null) {
			if (other.styleBrand != null)
				return false;
		} else if (!styleBrand.equals(other.styleBrand))
			return false;
		if (styleCatalogueCategory == null) {
			if (other.styleCatalogueCategory != null)
				return false;
		} else if (!styleCatalogueCategory.equals(other.styleCatalogueCategory))
			return false;
		if (styleDescription == null) {
			if (other.styleDescription != null)
				return false;
		} else if (!styleDescription.equals(other.styleDescription))
			return false;
		if (styleItemNumber == null) {
			if (other.styleItemNumber != null)
				return false;
		} else if (!styleItemNumber.equals(other.styleItemNumber))
			return false;
		if (styleLongDescription == null) {
			if (other.styleLongDescription != null)
				return false;
		} else if (!styleLongDescription.equals(other.styleLongDescription))
			return false;
		return true;
	}
	public String getImageRetrievalUrl() {
		return imageRetrievalUrl;
	}
	public void setImageRetrievalUrl(String imageRetrievalUrl) {
		this.imageRetrievalUrl = imageRetrievalUrl;
	}
}
