package com.ibm.nscontainercrush.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("dis")
public class DiscoveryConfiguration {
	
	private String authenticatorKey;
	private String versionDate;
	private String serviceUrl;
	private String environmentId;
	private float collectionId;
	public String getAuthenticatorKey() {
		return authenticatorKey;
	}
	public void setAuthenticatorKey(String authenticatorKey) {
		this.authenticatorKey = authenticatorKey;
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
	public String getEnvironmentId() {
		return environmentId;
	}
	public void setEnvironmentId(String environmentId) {
		this.environmentId = environmentId;
	}
	public float getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(float collectionId) {
		this.collectionId = collectionId;
	}
	
	

}
