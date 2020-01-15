package com.ibm.nscontainercrush.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("vtt")
public class VisualToTextConfiguration {
	
	private String authenticatorKey;
	private float baseConfidence;
	private String versionDate;
	private String serviceUrl;
	private String classifierId;
	
	public String getAuthenticatorKey() {
		return authenticatorKey;
	}
	public void setAuthenticatorKey(String authenticatorKey) {
		this.authenticatorKey = authenticatorKey;
	}
	public float getBaseConfidence() {
		return baseConfidence;
	}
	public void setBaseConfidence(float baseConfidence) {
		this.baseConfidence = baseConfidence;
	}
	public String getVersionDate() {
		return versionDate;
	}
	public void setVersionDate(String versionDate) {
		this.versionDate = versionDate;
	}
	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	public String getClassifierId() {
		return classifierId;
	}
	public void setClassifierId(String classifierId) {
		this.classifierId = classifierId;
	}
}
