package com.currency.currencymaster.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currency.currencymaster.dto.CurrencyDataDTO;
import com.currency.currencymaster.response.Response;
import com.currency.currencymaster.response.ResponseGenerator;
import com.currency.currencymaster.response.TransactionContext;
import com.currency.currencymaster.service.MasterService;
import com.currency.currencymaster.service.MessagePropertyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor(onConstructor_ = { @Autowired })
@RequestMapping("/api/currency/master")
@Api(value = "Currencty Master's", produces = "application/json", consumes = "application/json")
public class MasterController {

	private static final Logger logger = Logger.getLogger(MasterController.class);

	private @NonNull ResponseGenerator responseGenerator;
	
	@Autowired	
	MessagePropertyService messageSource;
	
	@Autowired
	MasterService masterService;
	
	@ApiOperation(value = "Allows to fetch list of currency code.", response = Response.class)
	@GetMapping(value = "/data", produces = "application/json")
	public ResponseEntity<?> getCurrencyMaster(@RequestHeader HttpHeaders httpHeader) throws Exception {
		
		TransactionContext context = responseGenerator.generateTransationContext(httpHeader);
		
		try {
			return responseGenerator.successGetResponse(context, messageSource.getMessage("currency.get.list"), masterService.getMasterList(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return responseGenerator.errorResponse(context, e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "Allow to create currency rate based on country code.", response = Response.class)
	@PostMapping(value = "/create", produces = "application/json")
	public ResponseEntity<?> currencyEntry(@RequestHeader HttpHeaders httpHeader, 
			@Valid @RequestBody CurrencyDataDTO currencyDataDTO,BindingResult bindingResult) throws Exception {		
		TransactionContext context = responseGenerator.generateTransationContext(httpHeader);
		
	    if (bindingResult.hasErrors()) {
	        List<String> errors = new ArrayList<>();
	        for (FieldError error : bindingResult.getFieldErrors()) {
	            errors.add(error.getDefaultMessage());
	        }
	        return responseGenerator.errorResponses(context,"",HttpStatus.BAD_REQUEST , errors);
	    }
	    
		try {
			masterService.createCurrencyRates(currencyDataDTO);
			return responseGenerator.successResponse(context, messageSource.getMessage("currency.rate.create"),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return responseGenerator.errorResponse(context, e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "Allow to update currency rate based on country code.", response = Response.class)
	@PutMapping(value = "/update", produces = "application/json")
	public ResponseEntity<?> updateCurrencyEntries(@RequestHeader HttpHeaders httpHeader, 
			@Valid @RequestBody CurrencyDataDTO currencyDataDTO,BindingResult bindingResult) throws Exception {		
		TransactionContext context = responseGenerator.generateTransationContext(httpHeader);
		
	    if (bindingResult.hasErrors()) {
	        List<String> errors = new ArrayList<>();
	        for (FieldError error : bindingResult.getFieldErrors()) {
	            errors.add(error.getDefaultMessage());
	        }
	        return responseGenerator.errorResponses(context,"",HttpStatus.BAD_REQUEST , errors);
	    }
		
		try {
			masterService.updateCurrencyRates(currencyDataDTO);
			return responseGenerator.successResponse(context, messageSource.getMessage("currency.rate.update"),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return responseGenerator.errorResponse(context, e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "Allow to update currency rate based on country code.", response = Response.class)
	@DeleteMapping(value = "/remove", produces = "application/json")
	public ResponseEntity<?> removeCurrencyEntries(@RequestHeader HttpHeaders httpHeader, 
			@Valid @RequestBody CurrencyDataDTO currencyDataDTO,BindingResult bindingResult) throws Exception {		
		TransactionContext context = responseGenerator.generateTransationContext(httpHeader);
		
	    if (bindingResult.hasErrors()) {
	        List<String> errors = new ArrayList<>();
	        for (FieldError error : bindingResult.getFieldErrors()) {
	            errors.add(error.getDefaultMessage());
	        }
	        return responseGenerator.errorResponses(context,"",HttpStatus.BAD_REQUEST , errors);
	    }
		
		try {
			masterService.removeCurrencyRates(currencyDataDTO);
			return responseGenerator.successResponse(context, messageSource.getMessage("currency.rate.remove"),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return responseGenerator.errorResponse(context, e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "Allows to fetch list of currency rates.", response = Response.class)
	@GetMapping(value = "/currency/data", produces = "application/json")
	public ResponseEntity<?> getCurrencyrates(@RequestHeader HttpHeaders httpHeader) throws Exception {
		
		TransactionContext context = responseGenerator.generateTransationContext(httpHeader);
		
		try {
			return responseGenerator.successGetResponse(context, messageSource.getMessage("currency.rate.get.list"), masterService.getCurrencyList(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return responseGenerator.errorResponse(context, e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
}
