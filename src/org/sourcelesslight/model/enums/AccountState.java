package org.sourcelesslight.model.enums;

public enum AccountState {
	CONFIRMATION_NOT_SEND(0),
	PENDING_CONFIRMATION(1),
	CONFIRMED(2),
	SUSPENDED(3),
	BANNED(4);
	
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
