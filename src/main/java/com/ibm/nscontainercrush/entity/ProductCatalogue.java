package com.ibm.nscontainercrush.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="XXIBM_PRODUCT_CATALOGUE")
public class ProductCatalogue {
	
	@EmbeddedId
    private ProductCataloguePK id;
	
	@Column(name="SEGMENT_NAME")
	private String segmentName;
		
	@Column(name="FAMILY_NAME")
	private String familyName;
	
	@Column(name="CLASS_NAME")
	private String className;
	
	@Column(name="COMMODITY_NAME")
	private String commodityName;

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public ProductCataloguePK getId() {
		return id;
	}

	public void setId(ProductCataloguePK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ProductCatalogue other = (ProductCatalogue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
