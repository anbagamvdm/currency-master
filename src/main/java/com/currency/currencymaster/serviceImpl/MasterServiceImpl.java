package com.currency.currencymaster.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.currency.currencymaster.dto.CurrencyDataDTO;
import com.currency.currencymaster.dto.CurrencyMasterDTO;
import com.currency.currencymaster.entity.CurrencyData;
import com.currency.currencymaster.entity.MasterData;
import com.currency.currencymaster.enumeration.Status;
import com.currency.currencymaster.exception.BadRequestException;
import com.currency.currencymaster.mapper.CurrencyDataMapper;
import com.currency.currencymaster.mapper.MasterDataMapper;
import com.currency.currencymaster.repository.CurrencyDataRepo;
import com.currency.currencymaster.repository.MasterDataRepo;
import com.currency.currencymaster.service.MasterService;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@Transactional
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class MasterServiceImpl implements MasterService {

	private static final Logger logger = Logger.getLogger(MasterServiceImpl.class);

	private @NonNull MasterDataRepo masterDataRepo;

	private @NonNull CurrencyDataRepo currencyDataRepo;

	@Override
	public List<CurrencyMasterDTO> getMasterList() {
		try {
			MasterDataMapper masterDataMapper = MasterDataMapper.INSTANCE;
			List<MasterData> masterData = masterDataRepo.findAll();
				List<CurrencyMasterDTO> currentMasterList = masterDataMapper.toCurrencyMasterDTOs(masterData);
				return currentMasterList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadRequestException("No able to fetch the data");
		}
	};

	@Override
	public void createCurrencyRates(CurrencyDataDTO currencyDataDTO) {		
	Optional<CurrencyData> currencyData = currencyDataRepo.findByCurrencyCodeAndStatus(currencyDataDTO.getCurrencyCode(), Status.ACTIVE);
			if (!currencyData.isPresent()) {
				//Optional<MasterData> masterData = masterDataRepo.findByCurrencyCode(currencyDataDTO.getCurrencyCode());
				//System.out.println(masterData.get());
				//if (masterData.isPresent()) {
					CurrencyData currencyValue = new CurrencyData();
					currencyValue.setStatus(Status.ACTIVE);
					//currencyValue.setMasterData(masterData.get());
					currencyValue.setCurrencyCode(currencyDataDTO.getCurrencyCode());
					currencyValue.setCurrencyRate(currencyDataDTO.getCurrencyRate());
					currencyValue.setCurrencyName(currencyDataDTO.getCurrencyName());
					currencyDataRepo.saveAndFlush(currencyValue);
				//} else {
				//	throw new BadRequestException("Invalid currencyCode");
				//}
			} else {
				throw new BadRequestException("Entry is already available.");
			}
	}

	@Override
	public void updateCurrencyRates(CurrencyDataDTO currencyDataDTO) {
		try {
			Optional<CurrencyData> currencyData = currencyDataRepo
					.findByCurrencyCodeAndStatus(currencyDataDTO.getCurrencyCode(), Status.ACTIVE);
			if (currencyData.isPresent()) {
				CurrencyData currencyValues = currencyData.get();
				currencyValues.setCurrencyRate(currencyDataDTO.getCurrencyRate());
				currencyDataRepo.saveAndFlush(currencyValues);
			} else {
				throw new BadRequestException("Invalid entries provoided");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadRequestException("No able to update the data");
		}
		
	}

	@Override
	public void removeCurrencyRates(CurrencyDataDTO currencyDataDTO) {
		try {
			Optional<CurrencyData> currencyData = currencyDataRepo
					.findByCurrencyCodeAndStatus(currencyDataDTO.getCurrencyCode(), Status.ACTIVE);
			if (currencyData.isPresent()) {
				CurrencyData currencyValues = currencyData.get();
				currencyValues.setStatus(Status.INACTIVE);
				currencyDataRepo.saveAndFlush(currencyValues);
			} else {
				throw new BadRequestException("Invalid entries provoided");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadRequestException("No able to delete the data");
		}
		
	}

	@Override
	public List<CurrencyDataDTO> getCurrencyList() {
		try {
			CurrencyDataMapper currencyDataMapper = CurrencyDataMapper.INSTANCE;
			List<CurrencyData> masterData = currencyDataRepo.findByStatus(Status.ACTIVE);
				List<CurrencyDataDTO> currentMasterList = currencyDataMapper.toCurrencyMasterDTOs(masterData);
				return currentMasterList;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadRequestException("No able to fetch the data");
		}
	}

}
