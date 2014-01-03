package org.sourcelesslight.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.sourcelesslight.model.enums.AccountState;
import org.sourcelesslight.model.enums.AuthType;
import org.springframework.cache.annotation.Cacheable;

@Indexed
@Entity(name="USERS")
@Table(name="USERS")
@Cacheable(value =  "Entity_User")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User {
	
	
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="ID_USERS_GENERATOR", name="ID_USERS_GENERATOR")
	@GeneratedValue(generator="ID_USERS_GENERATOR", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="ID_USERS")
	private int userId;
	
	// Field is for Hibernate Search
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	//@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="FNAME")
	private String firstname;
	
	//@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="LNAME")
	private String lastname;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="EMAIL")
	private String email;
	
	@Temporal(TemporalType.DATE)
	@Column(name="REGDATE")
	private Date regDate;
	
	@Column(name="AUTHLEVEL")
	@Enumerated(EnumType.ORDINAL)
	private AuthType authLevel;
	
	@Column(name="ACCSTATE")
	@Enumerated(EnumType.ORDINAL)
	private AccountState accState;
	
	// Lazy initialize this until we manually initialize them
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PREFERENCES")
	private Preferences preferences;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CONFIRMATIONCODES")
	private ConfirmationCode confirmationCode;
	
	// Setters & Getters
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public AuthType getAuthLevel() {
		return authLevel;
	}
	
	public void setAuthLevel(AuthType authLevel) {
		this.authLevel = authLevel;
	}
	
	public Preferences getPreferences() {
		return preferences;
	}
	
	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
	
	public AccountState getAccState() {
		return accState;
	}
	
	public void setAccState(AccountState accState) {
		this.accState = accState;
	}
	public ConfirmationCode getConfirmationCode() {
		return confirmationCode;
	}
	public void setConfirmationCode(ConfirmationCode confirmationCode) {
		this.confirmationCode = confirmationCode;
	}
	
	
	
}
