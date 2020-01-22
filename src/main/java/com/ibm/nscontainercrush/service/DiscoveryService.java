package com.ibm.nscontainercrush.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.util.ListUtils;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.nscontainercrush.config.DiscoveryConfiguration;
import com.ibm.nscontainercrush.constant.DocumentKeywordsEnum;
import com.ibm.nscontainercrush.dto.KeywordSearchDto;
//import com.ibm.watson.discovery.v2.model.QueryOptions;
//import com.ibm.watson.discovery.v2.model.QueryResponse;
import com.ibm.watson.discovery.v1.Discovery;
import com.ibm.watson.discovery.v1.model.QueryOptions;
import com.ibm.watson.discovery.v1.model.QueryResponse;
import com.ibm.watson.discovery.v1.model.QueryResult;

@Service
public class DiscoveryService {
	
	@Autowired
	private DiscoveryConfiguration disConfig;
	
	private static final Logger logger = LoggerFactory.getLogger(DiscoveryService.class);
	
	public KeywordSearchDto prepareKeywordList(String text) {
		
		KeywordSearchDto keywordSearchDto = null;
		QueryResponse queryResponse;
		if (!StringUtils.isEmpty(text)) {
			queryResponse = invokeWatsonDiscoveryService(text);
			keywordSearchDto = parseWatsonDiscoveryResponse(queryResponse);
		}
		
		return keywordSearchDto;
	}

	public QueryResponse invokeWatsonDiscoveryService(String queryText) {

		IamAuthenticator authenticator = new IamAuthenticator(disConfig.getAuthenticatorKey());
		Discovery discovery = new Discovery(disConfig.getVersionDate(), authenticator);
		discovery.setServiceUrl(disConfig.getServiceUrl());
		String environmentId = disConfig.getEnvironmentId();
		String collectionId = disConfig.getCollectionId();
		
		logger.info("Querying the collection...");
		QueryOptions.Builder queryBuilder = new QueryOptions.Builder(environmentId, collectionId);
		queryBuilder.highlight(true).deduplicate(true)
				.query(queryText);

		QueryResponse queryResponse = discovery.query(queryBuilder.build()).execute().getResult();

		logger.info("Query Results:" + queryResponse);

		return queryResponse;
	}
	
	private KeywordSearchDto parseWatsonDiscoveryResponse(QueryResponse queryResponse) {
		KeywordSearchDto keywordSearchDto = null;
		if (queryResponse != null) {
			keywordSearchDto = new KeywordSearchDto();
			if (!ListUtils.isEmpty(queryResponse.getResults())) {
				for (QueryResult qryResult : queryResponse.getResults()) {
					populateKeywordSearchDto(qryResult, keywordSearchDto);
				}
			}
		}
		return keywordSearchDto;
	}
	
	private void populateKeywordSearchDto(QueryResult qryResult, KeywordSearchDto keywordSearchDto) {
		if (qryResult != null && qryResult.getId() != null && DocumentKeywordsEnum.get(qryResult.getId()) != null) {
			if (DocumentKeywordsEnum.get(qryResult.getId()).equals(DocumentKeywordsEnum.BRAND)) {
				keywordSearchDto.setBrands(getKeywords(qryResult));
			} else if (DocumentKeywordsEnum.get(qryResult.getId()).equals(DocumentKeywordsEnum.GENDER)) {
				keywordSearchDto.setGenders(getKeywords(qryResult));
			} else if (DocumentKeywordsEnum.get(qryResult.getId()).equals(DocumentKeywordsEnum.COLOR)) {
				keywordSearchDto.setColors(getKeywords(qryResult));
			} else if (DocumentKeywordsEnum.get(qryResult.getId()).equals(DocumentKeywordsEnum.SIZE)) {
				keywordSearchDto.setSizes(getKeywords(qryResult));
			} else if (DocumentKeywordsEnum.get(qryResult.getId()).equals(DocumentKeywordsEnum.DESCRIPTION)) {
				keywordSearchDto.setDescriptions(getKeywords(qryResult));
			}
		}	
	}	
		
	private List<String> getKeywords(QueryResult qryResult) {

		List<String> keywords = null;
		Map<String, Object> propertyMap = qryResult.getProperties();
		for (Map.Entry<String, Object> entry : propertyMap.entrySet()) {
			if (entry.getKey().equals("highlight")) {
				Map<String, List<String>> enrichedTextMap = (Map<String, List<String>>) entry.getValue();
				for (Map.Entry<String, List<String>> enrichTextEntry : enrichedTextMap.entrySet()) {
					if (enrichTextEntry.getKey().equals("enriched_text.keywords.text")) {
						keywords = new ArrayList<String>();
						keywords.addAll(formatText(enrichTextEntry.getValue()));
						break;
					}
				}
			}
//			if (entry.getKey().equals("enriched_text")) {
//				Map<String, List<String>> enrichedTextMap = (Map<String, List<String>>) entry.getValue();
//				if (!enrichedTextMap.isEmpty()) {
//					for (Map.Entry<String, List<String>> enrichTextEntry : enrichedTextMap.entrySet()) {
//						if (enrichTextEntry.getKey().equals("keywords")) {
//							List<String> keywordsMap = (List<String>) enrichTextEntry.getValue();
//							if (!keywordsMap.isEmpty()) {
//								keywords = new ArrayList<String>();
//								for (Map.Entry<String, List<String>> textEntry : enrichedTextMap.entrySet()) {
//									if (textEntry.getKey().equals("text")) {
//										keywords.addAll(textEntry.getValue());
//										break;
//									}
//								}
//							}
//						} 
//					}
//				}
//			}
		}
		return keywords;
	}
	
	private List<String> formatText(List<String> unformattedTextList) {
		List<String> formattedStrList = null;
		if (!ListUtils.isEmpty(unformattedTextList)) {
			formattedStrList = new ArrayList<>();
			for (String unformattedStr : unformattedTextList) {
				if (!StringUtils.isEmpty(unformattedStr)) {
					String formattedStr = StringUtils.replace(unformattedStr, "<em>", "").replaceAll("</em>", "");
					formattedStrList.add(formattedStr);
				}
			}
		}
		return formattedStrList;
	}
	
	public static void main(String[] args) {
		new DiscoveryService().prepareKeywordList("want blue and red tshirts of small size of Reflex brand");
	}
}
