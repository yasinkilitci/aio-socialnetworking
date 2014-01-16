package org.sourcelesslight.actions;


import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class SubmenuProviderAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String member = "ilker";
	
	public String execute()
	{
		member = "ilker";
		
		return SUCCESS;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}
	
}
