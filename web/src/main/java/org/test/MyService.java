package org.test;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class MyService {
	
	@EJB
	private ConfigRepo repo;

	@WebMethod
	public String getWelcomeMsg(String name) {
		return repo.getConfigVal("prefix1") + name;
	}
}
