package com.ibm.nscontainercrush.controller;

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
import com.ibm.nscontainercrush.dto.SkuItemResult;
import com.ibm.nscontainercrush.service.VisualToTextService;

@RestController
@RequestMapping(value = "/visualToText")
public class VisualToTextController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(VisualToTextController.class);

	@Autowired
	private VisualToTextService vttService;
	
	@PostMapping("/convertVisualToText")
	public SkuItemResult convertVisualToText(@RequestParam("file") MultipartFile file){
		SkuItemResult result = new SkuItemResult(); 
		boolean success=false;
		if (!file.isEmpty()) {
			byte[] bytes;
			try {
				bytes = file.getBytes();
				List<SkuItem> skuItemList = vttService.retrieveItemsUsingVisualToTextConversion(bytes);
				result.setSkuItemList(skuItemList);
				success = true;
			} catch (Exception e) {
				logger.error("Exception occurred while processing visual to text conversion" + e);
				result.setErrorDesc(e.getMessage());
				setExceptionResponse(result, e);
			} finally {
				result.setSuccess(success);
			}
		}
	 return result;
	}

}
