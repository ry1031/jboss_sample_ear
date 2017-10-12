package org.test;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class MyService {
	
	@EJB
	private WelcomeService service;

	@WebMethod
	public String getWelcomeMsg(String name) {
		return service.getWelcomeMsg(name);
	}

}
