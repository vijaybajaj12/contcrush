package com.ibm.nscontainercrush.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.nscontainercrush.config.SpeechToTextConfiguration;
import com.ibm.nscontainercrush.constant.ContainerCrushConstant;
import com.ibm.nscontainercrush.dto.SkuItem;
import com.ibm.nscontainercrush.util.ContainerCrushUtil;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionAlternative;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.speech_to_text.v1.model.SpeechWordConfidence;
import com.ibm.watson.speech_to_text.v1.websocket.BaseRecognizeCallback;

@Service
public class SpeechToTextService {
	
	private static final Logger logger = LoggerFactory.getLogger(SpeechToTextService.class);
	
	@Autowired
	private SpeechToTextConfiguration sttConfig;
	
	@Autowired
	private Environment env;
	
	private SpeechRecognitionResults srResults;
	
	@Autowired
	private ProductCatalogueService productCatalogueService;
	
	public List<SkuItem> retrieveItemsUsingSpeechToTextConversion () throws Exception {
		List<String> extractedWords = convertSpeechToText();
		List<String> finalWordList = ContainerCrushUtil.getFilteredWords(extractedWords, env.getProperty("definedKeywords"));
		
		return productCatalogueService.findItemsByTextArray(finalWordList);
	}
	
	public List<String> convertSpeechToText() throws LineUnavailableException, InterruptedException {
		invokeWatsonSpeechToTextProcess();
		List<String> extractedWords = extractWordsFromWatsonResult(srResults);
		return extractedWords;
	}
		
	/**
	 * This method is used to invoke watson speech to text service and capture the results
	 * @throws InterruptedException
	 * @throws LineUnavailableException
	 */
	private void invokeWatsonSpeechToTextProcess() throws LineUnavailableException, InterruptedException {
		Authenticator authenticator = new IamAuthenticator(sttConfig.getAuthenticatorKey());
		SpeechToText service = new SpeechToText(authenticator);
		String[] keywordsArray = {"shirt", "trouser", "Coat", "Skirt","Blouse"};

		// Signed PCM AudioFormat with 16kHz, 16 bit sample size, mono
		int sampleRate = sttConfig.getSampleRate();
		AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

		if (!AudioSystem.isLineSupported(info)) {
			System.exit(0);
		}

		TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
		line.open(format);
		line.start();

		AudioInputStream audio = new AudioInputStream(line);

		RecognizeOptions options = new RecognizeOptions.Builder()
				.model(sttConfig.getModel())
				.audio(audio)
				.keywords(Arrays.asList(keywordsArray))
				.keywordsThreshold((float) 0.6)
				.maxAlternatives(3)
				.interimResults(false)
				.timestamps(true)
				.wordConfidence(true)
				.contentType(HttpMediaType.AUDIO_RAW + ";rate=" + sampleRate).build();

		service.recognizeUsingWebSocket(options, new BaseRecognizeCallback() {
			@Override
			public void onTranscription(SpeechRecognitionResults speechResults) {			
				storeResults (speechResults);
				if (logger.isInfoEnabled()) {
					logger.info("Results retrieved from watson speech to text service : " + speechResults);
				}
			}
			
			@Override
			public void onTranscriptionComplete() {
			}
			
		});

		if (logger.isInfoEnabled()) {
			logger.info("Listening to your voice for the next 5s...");
		}
		// Adding a sleep time to capture the voice from user
		Thread.sleep(5 * 1000); 

		// closing the WebSockets underlying InputStream will close the WebSocket itself.
		line.stop();
		line.close();
		
		// Adding a sleep time to ensure that we get the SpeechRecognitionResults before proceeding to next method
		Thread.sleep(5 * 1000); 

		if (logger.isInfoEnabled()) {
			logger.info("Finished");
		}
	}
	
	private void storeResults(SpeechRecognitionResults speechResults) {
		srResults = speechResults;
	}
	
	/**
	 * This method is used to extract the words from speech to watson recognition API output
	 * @param srResult
	 * @return
	 */
	private List<String> extractWordsFromWatsonResult(SpeechRecognitionResults spReResult) {

		List<String> extractedWordList = null;

		if (spReResult != null && !spReResult.getResults().isEmpty()) {
			for (SpeechRecognitionResult speechResult : spReResult.getResults()) {
				if (speechResult.isXFinal()) {
					int k = 0;
					extractedWordList = new ArrayList<>();
					List<SpeechRecognitionAlternative> alternatives = speechResult.getAlternatives();
					if (alternatives != null && !alternatives.isEmpty()) {
						for (SpeechRecognitionAlternative speechRecognitionAlter : alternatives) {
							if (speechRecognitionAlter.getWordConfidence() != null
									&& !speechRecognitionAlter.getWordConfidence().isEmpty()) {
								for (SpeechWordConfidence speechWordConfidence : speechRecognitionAlter
										.getWordConfidence()) {
									if (speechWordConfidence.getConfidence() > sttConfig.getBaseConfidence()) {
										extractedWordList.add(speechWordConfidence.getWord());
										k++;
									}
								}
							}
						}
					}
				}
			}
		}

		srResults = null;
		return extractedWordList;
	}
	
}

