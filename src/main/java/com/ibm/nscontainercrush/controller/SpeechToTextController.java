package com.ibm.nscontainercrush.controller;

import java.util.List;

import javax.sound.sampled.LineUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.nscontainercrush.dto.ProductCatalogueDto;
import com.ibm.nscontainercrush.service.SpeechToTextService;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;

@RestController
@RequestMapping(value = "/speechToText")
public class SpeechToTextController {
	
	@Autowired
	private SpeechToTextService sttService;
	
	@PostMapping("/convertSpeechToText")
	public SpeechRecognitionResults convertSpeechToText(){
		try {
			return sttService.convertSpeechToText();
		} catch (InterruptedException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
