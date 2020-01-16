package com.ibm.nscontainercrush.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.nscontainercrush.dto.SkuItem;
import com.ibm.nscontainercrush.dto.SkuItemResult;
import com.ibm.nscontainercrush.service.SpeechToTextService;

@RestController
@RequestMapping(value = "/speechToText")
public class SpeechToTextController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(SpeechToTextController.class);
	
	@Autowired
	private SpeechToTextService sttService;
	
	@GetMapping("/convertSpeechToText")
	public SkuItemResult convertSpeechToText(){
		SkuItemResult result = new SkuItemResult(); 
		boolean success=false;
			try {
				List<SkuItem> skuItemList = sttService.retrieveItemsUsingSpeechToTextConversion();
				result.setSkuItemList(skuItemList);
				success = true;
			} catch (Exception e) {
				logger.error("Exception occurred while processing speech to text conversion" + e);
				result.setErrorDesc(e.getMessage());
				setExceptionResponse(result, e);
			} finally {
				result.setSuccess(success);
			}
	 return result;
	}

}
