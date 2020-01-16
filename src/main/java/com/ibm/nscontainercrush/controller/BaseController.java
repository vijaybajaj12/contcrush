package com.ibm.nscontainercrush.controller;

import java.sql.SQLException;

import javax.sound.sampled.LineUnavailableException;

import com.ibm.cloud.sdk.core.service.exception.NotFoundException;
import com.ibm.cloud.sdk.core.service.exception.RequestTooLargeException;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.nscontainercrush.constant.ErrorConstants;
import com.ibm.nscontainercrush.dto.BaseResponse;

public class BaseController {
	
	protected void setExceptionResponse(BaseResponse res,Exception e) {

		res.setSuccess(false);

		if (e instanceof SQLException) {
			res.setErrorCode(ErrorConstants.SQL_EXCEPTION);
			res.setErrorDesc(e.getMessage());
		} else if (e instanceof NotFoundException) {
			res.setErrorCode(ErrorConstants.NOT_FOUND_EXCEPTION);
			res.setErrorDesc(e.getStackTrace()[0].toString());
		} else if (e instanceof RequestTooLargeException) {
			res.setErrorCode(ErrorConstants.REQUEST_TOO_LARGE_EXCEPTION);
			res.setErrorDesc(e.getStackTrace()[0].toString());
		} else if (e instanceof ServiceResponseException) {
			res.setErrorCode(ErrorConstants.SERVICE_RESPONSE_EXCEPTION);
			res.setErrorDesc(e.getStackTrace()[0].toString());
		} else if (e instanceof InterruptedException) {
			res.setErrorCode(ErrorConstants.INTERRUPTED_EXCEPTION);
			res.setErrorDesc(e.getStackTrace()[0].toString());
		} else if (e instanceof LineUnavailableException) {
			res.setErrorCode(ErrorConstants.LINE_UNAVAILABLE_EXCEPTION);
			res.setErrorDesc(e.getStackTrace()[0].toString());
		}else {
			res.setErrorCode(ErrorConstants.EXCEPTION);
			res.setErrorDesc(e.getMessage());
		}

	}

}
