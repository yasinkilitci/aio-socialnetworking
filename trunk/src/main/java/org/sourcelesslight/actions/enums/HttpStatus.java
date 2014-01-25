package org.sourcelesslight.actions.enums;

public enum HttpStatus {
	SUCCESSFUL(200),
	CONFLICT(409),
	NOT_FOUND(404),
	FORBIDDEN(403),
	INTERNAL_SERVER_ERROR(502),
	SERVICE_UNAVAILABLE(503),
	CONNECTION_TIMED_OUT(510)
	;
	
	private int value;
	private HttpStatus(int value)
	{
		this.value = value;
	}
	
	public int toInt()
	{
		return value;
	}
}