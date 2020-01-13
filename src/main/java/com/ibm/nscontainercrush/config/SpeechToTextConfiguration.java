package com.ibm.nscontainercrush.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("stt")
public class SpeechToTextConfiguration {
	
	private String authenticatorKey;
	private float baseConfidence;
	private int sampleRate;
	private String model;
	private String definedKeywords;
	
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
	public int getSampleRate() {
		return sampleRate;
	}
	public void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDefinedKeywords() {
		return definedKeywords;
	}
	public void setDefinedKeywords(String definedKeywords) {
		this.definedKeywords = definedKeywords;
	}

}
