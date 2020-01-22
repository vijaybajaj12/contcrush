package com.ibm.nscontainercrush.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.util.ListUtils;

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
					if (!StringUtils.isEmpty(word)) {
						if (definedKeywordList.contains(word.toLowerCase())) {
							filteredWordList.add(ContainerCrushConstant.EMPTY_SPACE + word);
						}
					}
				}	
			}
		}

		return filteredWordList;
	}

	/**
	 * This method will be used to convert a list of strings into text string
	 * @param strList
	 * @return
	 */
	public static String convertListToText(List<String> strList) {
		String textStr = null;
		if (!ListUtils.isEmpty(strList)) {
			StringBuilder strBuilder = new StringBuilder();
			for (String str : strList) {
				if (!str.isEmpty()) {
					strBuilder.append(str).append(ContainerCrushConstant.EMPTY_SPACE);
				}
			}
			textStr = strBuilder.toString();
		}
		
		return textStr;
	}
	
	/**
	 * This method will be used to multiply discount value by 100
	 * @param discount
	 * @return
	 */
	public static String convertIntoDiscountPercent(String discount) {
		double disc = Double.valueOf(discount) * 100;
		int i = (int) disc;
		return String.valueOf(i);		
	}

}
