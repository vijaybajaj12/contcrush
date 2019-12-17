package com.ibm.nscontainercrush.dto;

import java.util.List;

public class Family {
	
	public Family(String familyId, String familyName) {
		super();
		this.familyId = familyId;
		this.familyName = familyName;
	}
	private String familyId;
	private String familyName;
	private List<ClassLevel> classList;
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public List<ClassLevel> getClassList() {
		return classList;
	}
	public void setClassList(List<ClassLevel> classList) {
		this.classList = classList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classList == null) ? 0 : classList.hashCode());
		result = prime * result + ((familyId == null) ? 0 : familyId.hashCode());
		result = prime * result + ((familyName == null) ? 0 : familyName.hashCode());
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
		Family other = (Family) obj;
		if (classList == null) {
			if (other.classList != null)
				return false;
		} else if (!classList.equals(other.classList))
			return false;
		if (familyId == null) {
			if (other.familyId != null)
				return false;
		} else if (!familyId.equals(other.familyId))
			return false;
		if (familyName == null) {
			if (other.familyName != null)
				return false;
		} else if (!familyName.equals(other.familyName))
			return false;
		return true;
	}

}
