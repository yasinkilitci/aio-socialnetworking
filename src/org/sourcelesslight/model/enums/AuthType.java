package org.sourcelesslight.model.enums;

public enum AuthType {
	ADMIN(0),
	USER(1),
	MODERATOR(2);
	
	private int value;
	private AuthType(int value)
	{
		this.value = value;
	}
	
	public int toInt()
	{
		return value;
	}
	 
}
