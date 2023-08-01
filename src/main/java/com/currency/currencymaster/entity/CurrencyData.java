package com.currency.currencymaster.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.currency.currencymaster.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "curreny_data")
public class CurrencyData extends RecordModifier implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	//@NotNull(message="Currency code cannot be empty")
	//@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name = "currency_code")
	//private MasterData masterData;

	@Column(name = "currency_code")
	private String currencyCode;
	
	@Column(name = "currency_rate")
	private String currencyRate;
	
	@Column(name = "currency_name")
	private String currencyName;
	
	@Enumerated(EnumType.STRING)
	private Status status;
}
