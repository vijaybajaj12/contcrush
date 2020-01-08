package com.ibm.nscontainercrush.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.nscontainercrush.config.SpeechToTextConfiguration;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionAlternative;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.speech_to_text.v1.model.SpeechWordConfidence;
import com.ibm.watson.speech_to_text.v1.websocket.BaseRecognizeCallback;

@Service
public class SpeechToTextService {
	
	@Autowired
	private SpeechToTextConfiguration sttConfig;
	
	private SpeechRecognitionResults srResults;
	
	public List<String> convertSpeechToText() {
		
		try {
			invokeWatsonSpeechToTextProcess();
			return extractWordsFromWatsonResult(srResults);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
		
	public void invokeWatsonSpeechToTextProcess() throws InterruptedException, LineUnavailableException {
		Authenticator authenticator = new IamAuthenticator(sttConfig.getAuthenticatorKey());
		SpeechToText service = new SpeechToText(authenticator);

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
				.keywords(Arrays.asList("shirt", "trouser", "Coat", "Skirt","Blouse"))
				.keywordsThreshold((float) 0.6)
				.maxAlternatives(3)
				.interimResults(false)
				.timestamps(true)
				.wordConfidence(true)
				// .inactivityTimeout(5) // use this to stop listening when the speaker pauses,
				// i.e. for 5s
				.contentType(HttpMediaType.AUDIO_RAW + ";rate=" + sampleRate).build();

		service.recognizeUsingWebSocket(options, new BaseRecognizeCallback() {
			@Override
			public void onTranscription(SpeechRecognitionResults speechResults) {
				// TODO -need to capture speechResults
				
				storeResults (speechResults);
				//srResults=speechResults;
				System.out.println(speechResults);
			}
			
			@Override
			public void onTranscriptionComplete() {
				System.out.println("onTranscriptionComplete");
			}
			
		});

		System.out.println("Listening to your voice for the next 5s...");
		Thread.sleep(5 * 1000);

		// closing the WebSockets underlying InputStream will close the WebSocket
		// itself.
		line.stop();
		line.close();
		
		Thread.sleep(5 * 1000); // to ensure that we get the SpeechRecognitionResults
		System.out.println("Fin.");
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

