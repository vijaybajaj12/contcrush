package com.ibm.nscontainercrush.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.ibm.nscontainercrush.constant.ContainerCrushConstant;

public class ContainerCrushUtil {
	
	@Autowired
	private static Environment env;
	
	public static List<String> getFilteredWords(List<String> wordsExtracted) {
		List<String> filteredWordList = null;
		List<String> definedKeywordList = null;
		String definedKeywords = env.getProperty("definedKeywords");
		if (wordsExtracted != null && !wordsExtracted.isEmpty()) {
			filteredWordList = new ArrayList<>();
			if (!StringUtils.isNoneEmpty(definedKeywords)) {
				String delimiter = ContainerCrushConstant.PIPE;
				definedKeywordList = new ArrayList<>();

				StringTokenizer st = new StringTokenizer(definedKeywords, delimiter);
				while (st.hasMoreElements()) {
					definedKeywordList.add((String) st.nextElement());
				}
			}

			for (String word : wordsExtracted) {
				if (definedKeywordList.contains(word)) {
					filteredWordList.add(word);
				}
			}
		}

		return filteredWordList;
	}


}
