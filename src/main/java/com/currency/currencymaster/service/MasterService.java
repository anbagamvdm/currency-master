package com.currency.currencymaster.service;


import java.util.List;

import com.currency.currencymaster.dto.CurrencyDataDTO;
import com.currency.currencymaster.dto.CurrencyMasterDTO;


public interface MasterService {

	public List<CurrencyMasterDTO> getMasterList();

	public void createCurrencyRates(CurrencyDataDTO currencyDataDTO);

	public void updateCurrencyRates(CurrencyDataDTO currencyDataDTO);

	public void removeCurrencyRates(CurrencyDataDTO currencyDataDTO);

	public List<CurrencyDataDTO> getCurrencyList();

}
