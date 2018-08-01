package ren.xiangmu.creatorapi.services.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ren.xiangmu.creatorapi.beans.ResponseMessage;
import ren.xiangmu.creatorapi.models.User;
import ren.xiangmu.creatorapi.services.BaseService;
import ren.xiangmu.creatorapi.services.ITestService;
import ren.xiangmu.creatorapi.test.dao.UserMapper;

@Service
@Component
@Path("/")
//@WebService(targetNamespace = "http://api.xiangmu.ren/", endpointInterface = "ren.xiangmu.creatorapi.services.ITestService", serviceName = "test")
public class TestServiceImpl extends BaseService implements ITestService {
	private static final Logger LOG = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	@GET
	@Path("/test")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String test() {
		return "Hello world";
	}

	@GET
	@Path("/testUser/{name}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseMessage<List<User>> testUser(@PathParam("name") String name) {
		LOG.info("name:" + name);
		List<User> list = userMapper.findUsersByName(name);
		LOG.info("list.size : " + list.size());
		for (User u : list) {
			LOG.info("U:" + u.getUserName());
		}
		return success(list);
	}
}
