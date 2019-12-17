package com.ibm.nscontainercrush.dto;

import java.util.List;

public class ProductCatalogueDto {
	
	public ProductCatalogueDto(String segmentId, String segmentName) {
		this.segmentId = segmentId;
		this.segmentName = segmentName;
	}
	
	private String segmentId;
	private String segmentName;

	public String getSegmentId() {
		return segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}
	public String getSegmentName() {
		return segmentName;
	}
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((segmentId == null) ? 0 : segmentId.hashCode());
		result = prime * result + ((segmentName == null) ? 0 : segmentName.hashCode());
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
		ProductCatalogueDto other = (ProductCatalogueDto) obj;
		if (segmentId == null) {
			if (other.segmentId != null)
				return false;
		} else if (!segmentId.equals(other.segmentId))
			return false;
		if (segmentName == null) {
			if (other.segmentName != null)
				return false;
		} else if (!segmentName.equals(other.segmentName))
			return false;
		return true;
	}
	
}
