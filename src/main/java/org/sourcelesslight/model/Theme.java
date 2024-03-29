package org.sourcelesslight.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Table(name="THEMES")
@Cacheable(value =  "Entity_Themes")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Theme {

	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="ID_THEMES_GENERATOR", name="ID_THEMES_GENERATOR")
	@GeneratedValue(generator="ID_THEMES_GENERATOR", strategy=GenerationType.SEQUENCE)
	@Id
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
