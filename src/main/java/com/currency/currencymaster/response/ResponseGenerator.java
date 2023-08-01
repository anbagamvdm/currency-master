package com.currency.currencymaster.response;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseGenerator {

	private static Logger logger = LoggerFactory.getLogger(ResponseGenerator.class);

	public ResponseEntity<Response> successResponse(TransactionContext context, Object object, HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLabel", context.getApplicationLabel());
		headers.add("Content-Type", "application/json");
		Response response = new Response();
		response.setData(object);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		logger.debug("response class is " + Data.class);
		logger.debug("response status is " + httpStatus.toString());
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers, httpStatus);
		return responseEntity;
	} 
	
	public ResponseEntity<Resource> successGetFileResponse(TransactionContext context, String fileName, ByteArrayInputStream inStream, HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLabel", context.getApplicationLabel());
		headers.add(HttpHeaders.CONTENT_DISPOSITION,"inline;filename="+fileName);
		logger.info("response status is " + httpStatus.toString());
		
		return ResponseEntity
				.status(httpStatus)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.headers(headers)
				.body(new InputStreamResource(inStream));
	}
	
	public ResponseEntity<Response> successGetResponse(TransactionContext context, String message, Object object, HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLabel", context.getApplicationLabel());
		headers.add("Content-Type", "application/json");
		Response response = new Response();
		response.setData(object);
		response.setMessage(message);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		logger.debug("response class is " + Data.class);
		logger.debug("response status is " + httpStatus.toString());
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers, httpStatus);
		return responseEntity;
	} 

	public ResponseEntity<Response> errorResponse(TransactionContext context, String errorMessage,HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLabel", context.getApplicationLabel());
		headers.add("Content-Type", "application/json");
		Error error = new Error();
		error.setCode(httpStatus.toString() + "0001");
		error.setReason(errorMessage);
		Response response = new Response();
		response.setError(error);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers, httpStatus);
		return responseEntity;
	}
	
	public ResponseEntity<Response> errorResponses(TransactionContext context, String errorMessage, HttpStatus httpStatus, List<String> errors) {
		Response errorResponse = new Response();
		    errorResponse.setErrorMessages(errors);
		    errorResponse.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		    return new ResponseEntity<>(errorResponse, httpStatus);
	}

	public TransactionContext generateTransationContext(HttpHeaders httpHeaders) {

		TransactionContext context = new TransactionContext();
		
		if(null == httpHeaders) {
			context.setCorrelationId("demo");
			context.setApplicationLabel("demo");
			return context;
		}
		
		if (httpHeaders.get("correlationId") != null) {
			context.setCorrelationId(httpHeaders.get("correlationId").toString());
		} else {
			context.setCorrelationId("demo");
		}
		if (httpHeaders.get("ApplicationLabel") != null) {
			context.setApplicationLabel(httpHeaders.get("ApplicationLabel").toString());
		} else {
			context.setApplicationLabel("demo");
		}
		return context;
	}

}
