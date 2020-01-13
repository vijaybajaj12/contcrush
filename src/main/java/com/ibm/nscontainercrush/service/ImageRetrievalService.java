package com.ibm.nscontainercrush.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.nscontainercrush.config.CloudObjectStorageConfiguration;
import com.ibm.nscontainercrush.constant.ContainerCrushConstant;

@Service
public class ImageRetrievalService {

	private static final Logger logger = LoggerFactory.getLogger(ImageRetrievalService.class);
	
	@Autowired
	private CloudObjectStorageConfiguration cosConfig;
	
	public String contructImageRetrievalUrl(String objectKey) {

		String endpoint = ContainerCrushConstant.HTTPS + cosConfig.getHost();
		String region = "";
		String requestUrl = null;
		try {
			// assemble the standardized request
			ZonedDateTime time = ZonedDateTime.now(ZoneOffset.UTC);
			String datestamp = time.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			String timestamp = datestamp + "T" + time.format(DateTimeFormatter.ofPattern("HHmmss")) + "Z";

			String standardizedQuerystring = "X-Amz-Algorithm=AWS4-HMAC-SHA256" + "&X-Amz-Credential="
					+ URLEncoder.encode(cosConfig.getAccessKey() + "/" + datestamp + "/" + region + "/s3/aws4_request",
							StandardCharsets.UTF_8.toString())
					+ "&X-Amz-Date=" + timestamp + "&X-Amz-Expires=" + String.valueOf(cosConfig.getExpiration())
					+ "&X-Amz-SignedHeaders=host";

			String standardizedResource = "/" + cosConfig.getBucket() + "/" + objectKey;

			String payloadHash = ContainerCrushConstant.UNSIGNED_PAYLOAD;
			String standardizedHeaders = "host:" + cosConfig.getHost();
			String signedHeaders = "host";

			String standardizedRequest = ContainerCrushConstant.HTTP_GET + "\n" + standardizedResource + "\n"
					+ standardizedQuerystring + "\n" + standardizedHeaders + "\n" + "\n" + signedHeaders + "\n"
					+ payloadHash;

			// assemble string-to-sign
			String hashingAlgorithm = ContainerCrushConstant.AWS4_HMAC_SHA256;
			String credentialScope = datestamp + "/" + region + "/" + "s3" + "/" + "aws4_request";
			String sts = hashingAlgorithm + "\n" + timestamp + "\n" + credentialScope + "\n"
					+ hashHex(standardizedRequest);

			// generate the signature
			byte[] signatureKey = createSignatureKey(cosConfig.getSecretKey(), datestamp, region, "s3");
			String signature = hmacHex(signatureKey, sts);

			// construction of request url
			requestUrl = endpoint + "/" + cosConfig.getBucket() + "/" + objectKey + "?" + standardizedQuerystring
					+ "&X-Amz-Signature=" + signature;

		} catch (Exception ex) {
			System.out.printf("Error: %s\n", ex.getMessage());
		}

		return requestUrl;
	}

	private static String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();

		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}

	public static byte[] hash(byte[] key, String msg) throws Exception {
		byte[] returnVal = null;
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			returnVal = mac.doFinal(msg.getBytes("UTF8"));
		} catch (Exception ex) {
			throw ex;
		}
		return returnVal;
	}

	public static String hmacHex(byte[] key, String msg) {
		String returnVal = null;
		try {
			returnVal = toHexString(hash(key, msg));
		} catch (Exception ex) {

		} finally {
			return returnVal;
		}
	}

    public static String hashHex(String msg) {
        String returnVal = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(msg.getBytes(StandardCharsets.UTF_8));
            returnVal = toHexString(encodedhash);
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            return returnVal;
        }
    }

    // region is a wildcard value that takes the place of the AWS region value
    // as COS doesn"t use the same conventions for regions, this parameter can accept any string
    public static byte[] createSignatureKey(String key, String datestamp, String region, String service) {
        byte[] returnVal = null;
        try {
            byte[] keyDate = hash(("AWS4" + key).getBytes("UTF8"), datestamp);
            byte[] keyString = hash(keyDate, region);
            byte[] keyService = hash(keyString, service);
            byte[] keySigning = hash(keyService, "aws4_request");
            returnVal = keySigning;
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            return returnVal;
        }
    }

    public static String createHexSignatureKey(String key, String datestamp, String region, String service) {
        String returnVal = null;
        try {
            returnVal = toHexString(createSignatureKey(key, datestamp, region, service));
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            return returnVal;
        }
    }
}