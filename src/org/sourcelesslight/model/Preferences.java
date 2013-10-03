package org.sourcelesslight.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PREFERENCES")
public class Preferences {

	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="ID_PREFERENCES_GENERATOR", name="ID_PREFERENCES_GENERATOR")
	@GeneratedValue(generator="ID_PREFERENCES_GENERATOR", strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="ID_PREFERENCES")
	private int id;

	@ManyToOne(cascade=CascadeType.ALL)
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
