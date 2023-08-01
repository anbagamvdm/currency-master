package com.currency.currencymaster.repository;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.currency.currencymaster.config.WriteableRepository;
import com.currency.currencymaster.entity.MasterData;

@Repository
public interface MasterDataRepo extends WriteableRepository<MasterData, Long>, JpaSpecificationExecutor<MasterData>  {
	Optional<MasterData> findByCurrencyCode(String code);
}
