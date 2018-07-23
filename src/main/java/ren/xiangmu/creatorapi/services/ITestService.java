package ren.xiangmu.creatorapi.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "ITestService", targetNamespace = "http://api.xiangmu.ren/")
public interface ITestService{
	@WebMethod
	String test();
	
	@WebMethod
	String testUser(@WebParam(name = "name") String name);
}
