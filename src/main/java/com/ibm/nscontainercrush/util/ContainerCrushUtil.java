package com.ibm.nscontainercrush.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

import com.ibm.nscontainercrush.constant.ContainerCrushConstant;

public class ContainerCrushUtil {
		
	public static List<String> getFilteredWords(List<String> wordsExtracted, String definedKeywords) {
		List<String> filteredWordList = null;
		List<String> definedKeywordList = null;
		if (wordsExtracted != null && !wordsExtracted.isEmpty()) {
			filteredWordList = new ArrayList<>();
			if (!StringUtils.isEmpty(definedKeywords)) {
				String delimiter = ContainerCrushConstant.PIPE;
				definedKeywordList = new ArrayList<>();

				StringTokenizer st = new StringTokenizer(definedKeywords, delimiter);
				while (st.hasMoreElements()) {
					definedKeywordList.add((String) st.nextElement());
				}
				for (String word : wordsExtracted) {
					if (definedKeywordList.contains(word)) {
						filteredWordList.add(word);
					}
				}	
			}
		}

		return filteredWordList;
	}


}
