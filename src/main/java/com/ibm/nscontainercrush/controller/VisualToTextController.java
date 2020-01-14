package com.ibm.nscontainercrush.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.nscontainercrush.dto.SkuItem;
import com.ibm.nscontainercrush.service.VisualToTextService;

@RestController
@RequestMapping(value = "/visualToText")
public class VisualToTextController {
	
	private static final Logger logger = LoggerFactory.getLogger(VisualToTextController.class);

	@Autowired
	private VisualToTextService vttService;
	
	@PostMapping("/convertVisualToText")
	//public List<String> convertVisualToText(@RequestBody byte[] bytes){ 
	public List<SkuItem> convertVisualToText(@RequestParam("file") MultipartFile file){
		if (!file.isEmpty()) {
			byte[] bytes;
			try {
				bytes = file.getBytes();
				return vttService.retrieveItemsUsingVisualToTextConversion(bytes);
			} catch (Exception e) {
				logger.error("Exception occurred while processing visual to text conversion");
			}
		}
	 return null;
	}

}
