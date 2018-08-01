package ren.xiangmu.creatorapi.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import ren.xiangmu.creatorapi.beans.ResponseMessage;
import ren.xiangmu.creatorapi.models.User;

//@WebService(name = "ITestService", targetNamespace = "http://api.xiangmu.ren/")
@WebService(targetNamespace = "http://api.xiangmu.ren/", endpointInterface = "ren.xiangmu.creatorapi.services.ITestService", serviceName = "test")
public interface ITestService{
	@WebMethod
	@GET
	@Path("/test")
	String test();
	
	@WebMethod
	@GET
	@Path("/testUser/{name}")
	ResponseMessage<List<User>> testUser(@WebParam(name = "name") @PathParam("name") String name);
}
