package com.ibm.nscontainercrush.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.nscontainercrush.service.VisualToTextService;

@RestController
@RequestMapping(value = "/visualToText")
public class VisualToTextController {

	@Autowired
	private VisualToTextService vttService;
	
	@PostMapping("/convertVisualToText")
	public List<String> convertVisualToText(@RequestBody byte[] bytes){ 
		return vttService.convertVisualToText(bytes);
	}
}
