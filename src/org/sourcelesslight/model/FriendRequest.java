package org.sourcelesslight.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.sourcelesslight.model.enums.RequestType;
import org.sourcelesslight.model.enums.RespondType;


@Entity(name="FRIENDREQUESTS")
@Table(name="FRIENDREQUESTS")
public class FriendRequest {

	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="ID_FRIENDREQUESTS_GENERATOR", name="ID_FRIENDREQUESTS_GENERATOR")
	@GeneratedValue(generator="ID_FRIENDREQUESTS_GENERATOR", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="ID_FRIENDREQUESTS")
	private long id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_USERS_FROM")
	private User userFrom;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_USERS_TO")
	private User userTo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_SENT")
	private Date dateSent;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_RESPOND")
	private Date dateRespond;
	
	@Column(name="RESPOND")
	@Enumerated(EnumType.ORDINAL)
	private RespondType respond;
	
	@Column(name="REQUEST_TYPE")
	@Enumerated(EnumType.ORDINAL)
	private RequestType requestType;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public Date getDateRespond() {
		return dateRespond;
	}

	public void setDateRespond(Date dateRespond) {
		this.dateRespond = dateRespond;
	}

	public RespondType getRespond() {
		return respond;
	}

	public void setRespond(RespondType respond) {
		this.respond = respond;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	

	
	
	
}
