package com.currency.currencymaster.dto;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CurrencyMasterDTO {
	
	@Pattern(regexp = "^[A-Z]*$", message = "Invalid Currency Code.")
	private String currencyCode;
	
	@Pattern(regexp = "^[a-zA-Z0-9\\s\\.]*$", message = "Invalid Currency Name.")
	private String currencyName;	
}