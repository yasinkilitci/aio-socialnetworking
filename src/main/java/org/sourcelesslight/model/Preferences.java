package org.sourcelesslight.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Table(name="PREFERENCES")
@Cacheable(value =  "Entity_Preferences")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Preferences {

	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="ID_PREFERENCES_GENERATOR", name="ID_PREFERENCES_GENERATOR")
	@GeneratedValue(generator="ID_PREFERENCES_GENERATOR", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="ID_PREFERENCES")
	private int id;
	
	//CascadeType.PERSIST prevent theme table updating while updating Preferences
	@ManyToOne(cascade=CascadeType.PERSIST,fetch=FetchType.LAZY)
	@JoinColumn(name="ID_THEMES")
	private Theme theme;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
}
