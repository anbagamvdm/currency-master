package com.currency.currencymaster.entity;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "modifiedBy","modifiedOn","createdBy","createdOn", "deletedOn","deletedBy","status" })
public class RecordModifier implements Serializable {	

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;
	
	@JsonIgnore
	@CreatedDate
    @Column(name = "created_date")
	private	Date createdOn = new Date();
	
	@JsonIgnore
	@LastModifiedBy
    @Column(name = "updated_by")
	private String modifiedBy ;
	
	@JsonIgnore
	@LastModifiedDate
	@Column(name = "updated_date")
	private	Date modifiedOn;
	

}
