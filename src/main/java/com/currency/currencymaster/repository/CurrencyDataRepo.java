package com.currency.currencymaster.repository;




import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.currency.currencymaster.config.WriteableRepository;
import com.currency.currencymaster.entity.CurrencyData;
import com.currency.currencymaster.entity.MasterData;
import com.currency.currencymaster.enumeration.Status;

@Repository
public interface CurrencyDataRepo extends WriteableRepository<CurrencyData, Long>, JpaSpecificationExecutor<CurrencyData>  {
	 Optional<CurrencyData> findByCurrencyCodeAndStatus(String code,Status value);
	 
	 List<CurrencyData> findByStatus(Status value);
}
