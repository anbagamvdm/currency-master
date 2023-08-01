package com.currency.currencymaster.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.currency.currencymaster.dto.CurrencyMasterDTO;
import com.currency.currencymaster.entity.MasterData;

@Mapper(componentModel="spring")
public interface MasterDataMapper {
	MasterDataMapper INSTANCE = Mappers.getMapper(MasterDataMapper.class);
	
	CurrencyMasterDTO toCurrencyMasterDTO(MasterData userGroup);
	
	List<CurrencyMasterDTO> toCurrencyMasterDTOs(List<MasterData> userGroupEntries);
	
	MasterData toMasterData(CurrencyMasterDTO userGroupDTO);
	
	List<MasterData> toMasterDatas(List<CurrencyMasterDTO> userGroupDtos);
	
	
}