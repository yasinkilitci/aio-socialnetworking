package org.sourcelesslight.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Table(name="CONFIRMATION_CODES")
@Cacheable(value =  "Entity_ConfirmationCodes")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ConfirmationCode {

	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="ID_CONFIRMATIONCODES_GENERATOR", name="ID_CONFIRMATIONCODES_GENERATOR")
	@GeneratedValue(generator="ID_CONFIRMATIONCODES_GENERATOR", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="ID_CONFIRMATIONCODES")
	private int id;
	
	@Column(name="CONFIRMATIONCODE")
	private String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTACTIONTIME")
	private Date lastActionTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getLastActionTime() {
		return lastActionTime;
	}

	public void setLastActionTime(Date lastActionTime) {
		this.lastActionTime = lastActionTime;
	}

	
	
	
	
}
