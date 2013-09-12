package org.sourcelesslight.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="THEMES")
public class Theme {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_THEMES")
	private int themeId;
	
	@Column(name="NAME",nullable=false)
	private String themeName;

	public int getThemeId() {
		return themeId;
	}

	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	
	
}
