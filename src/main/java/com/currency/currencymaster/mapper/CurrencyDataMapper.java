package com.currency.currencymaster.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.currency.currencymaster.dto.CurrencyDataDTO;
import com.currency.currencymaster.entity.CurrencyData;

@Mapper(componentModel="spring")
public interface CurrencyDataMapper {
	CurrencyDataMapper INSTANCE = Mappers.getMapper(CurrencyDataMapper.class);
	
	CurrencyDataDTO toCurrencyMasterDTO(CurrencyData currencyData);
	
	List<CurrencyDataDTO> toCurrencyMasterDTOs(List<CurrencyData> currencyDataEntries);
	
	CurrencyData toCurrencyData(CurrencyDataDTO currencyDataDTO);
	
	List<CurrencyData> toCurrencyDatas(List<CurrencyDataDTO> currencyDataDTOs);
	
	
}