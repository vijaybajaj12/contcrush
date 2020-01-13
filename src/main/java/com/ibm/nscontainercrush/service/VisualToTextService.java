package com.ibm.nscontainercrush.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.cloud.sdk.core.service.exception.NotFoundException;
import com.ibm.cloud.sdk.core.service.exception.RequestTooLargeException;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.nscontainercrush.dto.SkuItem;
import com.ibm.nscontainercrush.util.ContainerCrushUtil;
import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.visual_recognition.v3.model.ClassifyOptions;

@Service
public class VisualToTextService {
	
	@Autowired
	private ProductCatalogueService productCatalogueService;
	
	public List<SkuItem> retrieveItemsUsingVisualToTextConversion (byte[] bytes) {
		List<String> extractedWords = convertVisualToText(bytes);
		List<String> finalWordList = ContainerCrushUtil.getFilteredWords(extractedWords);
		
		return productCatalogueService.findItemsByTextArray(finalWordList);
	}
	
	public List<String> convertVisualToText(byte[] bytes) {
		IamAuthenticator authenticator = new IamAuthenticator("fcCpq2BEdfp3RCE6EnuOHxF0ySW5VL8lOmLPaaLSGp0f");
		VisualRecognition visualRecognition = new VisualRecognition("2018-03-19", authenticator);
		visualRecognition.setServiceUrl(
				"https://api.us-south.visual-recognition.watson.cloud.ibm.com/instances/fad65a1b-45c0-48d2-bb5e-0b93b65a1ccf");
		ClassifiedImages result = null;
		List<String> strList = null;
		try {
			// TODO

			/*
			 * ClassifyOptions classifyOptions = new ClassifyOptions.Builder() .url(
			 * "https://watson-developer-cloud.github.io/doc-tutorial-downloads/visual-recognition/fruitbowl.jpg")
			 * //.url("https://ibm.biz/BdzLPG") .build();
			 * 
			 * // Invoke a Visual Recognition method result =
			 * visualRecognition.classify(classifyOptions).execute().getResult();
			 * System.out.println(result);
			 */
			// File file = new
			// File("src/main/resources/visual_recognition/v4/fruitbowl.jpg");
			// init array with file length
			// byte[] bytesArray = new byte[(int) file.length()];
			// byte[] array =
			// Files.readAllBytes(Paths.get("src/main/resources/visual_recognition/v4/fruitbowl.jpg"));
			// FileInputStream fis = new FileInputStream(file);
			// fis.read(bytesArray); //read file into bytes[]
			// fis.close();

			// return bytesArray;
			// imagesStream = new
			// FileInputStream("src/main/resources/visual_recognition/v4/fruitbowl.jpg");
			InputStream imagesStream = new ByteArrayInputStream(bytes);
			// InputStream imagesStream = new
			// FileInputStream("src/main/resources/visual_recognition/v4/fruitbowl.jpg");
			ClassifyOptions classifyOptions = new ClassifyOptions.Builder().imagesFile(imagesStream)
					.imagesFilename("fruitbowl.jpg").imagesFileContentType("image/jpg")
					// .imagesFileContentType(imagesFileContentType)
					.classifierIds(Arrays.asList("food")).build();

			result = visualRecognition.classify(classifyOptions).execute().getResult();
			// TODO convert results into list of string

		} catch (NotFoundException e) {
			// Handle Not Found (404) exception
		} catch (RequestTooLargeException e) {
			// Handle Request Too Large (413) exception
		} catch (ServiceResponseException e) {
			// Base class for all exceptions caused by error responses from the service
			System.out.println("Error: Service returned status code " + e.getStatusCode() + ": " + e.getMessage());
		}

		System.out.println("\n******** Classify with the General model ********\n" + result
				+ "\n******** END Classify with the General model ********\n");
		return strList;
	}
	
	public static void main(String args[]) {

	//	new VisualToTextService().convertVisualToText();

	}
}
