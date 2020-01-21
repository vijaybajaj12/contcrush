package com.ibm.nscontainercrush.constant;

import java.util.HashMap;
import java.util.Map;

public enum DocumentKeywordsEnum {
	
	BRAND("c91622e153f0567e18d63b0aad470739"), 
	GENDER("ab9c31ecc95ff79b6224a05a7ff9da14"), 
	COLOR("dce9818314ccf00bf9cb15d53b62b26a"),
    SIZE("8d3a65e0d6afd99c83e85ef90a803594") ,
    COMMODITY("9de6807a8f564bc017638d1d60808a0a");

    private final String documentId;

    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<String, DocumentKeywordsEnum> lookup = new HashMap<String, DocumentKeywordsEnum>();

    static {
        for (DocumentKeywordsEnum d : DocumentKeywordsEnum.values()) {
            lookup.put(d.getDocumentId(), d);
        }
    }

    private DocumentKeywordsEnum(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public static DocumentKeywordsEnum get(String documentId) {
//    	if (lookup.get(documentId).equals(DocumentKeywordsEnum.COLOR)) {
//    		
//    	}
        return lookup.get(documentId);
    }
    
}

