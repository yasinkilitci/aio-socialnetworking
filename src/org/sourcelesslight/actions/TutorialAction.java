package org.sourcelesslight.actions;

public class TutorialAction {

	public String execute()
	{
		System.err.println("hata");
		int a = 4;
		
		if(a==4)
			return "failure";
		else
			return "success";
	}
	
}
