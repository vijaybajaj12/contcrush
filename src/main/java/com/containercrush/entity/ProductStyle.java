package com.containercrush.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="XXIBM_PRODUCT_STYLE")
public class ProductStyle {
	
	@Id
	@Column(name="ITEM_NUMBER")
	private Integer itemNumber;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="LONG_DESCRIPTION")
	private String longDescription;
	
	@Column(name="CATALOGUE_CATEGORY")
	private Integer catalogueCategory;
	
	@Column(name="BRAND")
	private String brand;

	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Integer getCatalogueCategory() {
		return catalogueCategory;
	}

	public void setCatalogueCategory(Integer catalogueCategory) {
		this.catalogueCategory = catalogueCategory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((catalogueCategory == null) ? 0 : catalogueCategory.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((itemNumber == null) ? 0 : itemNumber.hashCode());
		result = prime * result + ((longDescription == null) ? 0 : longDescription.hashCode());
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
		ProductStyle other = (ProductStyle) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (catalogueCategory == null) {
			if (other.catalogueCategory != null)
				return false;
		} else if (!catalogueCategory.equals(other.catalogueCategory))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (itemNumber == null) {
			if (other.itemNumber != null)
				return false;
		} else if (!itemNumber.equals(other.itemNumber))
			return false;
		if (longDescription == null) {
			if (other.longDescription != null)
				return false;
		} else if (!longDescription.equals(other.longDescription))
			return false;
		return true;
	}
	
	
}
