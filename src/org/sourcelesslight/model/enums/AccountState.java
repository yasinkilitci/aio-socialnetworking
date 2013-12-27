package org.sourcelesslight.model.enums;

public enum AccountState {
	PENDING_CONFIRMATION(0),
	CONFIRMED(1),
	SUSPENDED(2),
	BANNED(3);
	
	private int value;
	
	private AccountState(int value)
	{
		this.value = value;
	}
	
	public int toInt()
	{
		return value;
	}
	
}
