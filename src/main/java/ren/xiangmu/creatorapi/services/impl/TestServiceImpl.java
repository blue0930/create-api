package ren.xiangmu.creatorapi.services.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import ren.xiangmu.creatorapi.models.User;
import ren.xiangmu.creatorapi.services.BaseService;
import ren.xiangmu.creatorapi.services.ITestService;
import ren.xiangmu.creatorapi.test.dao.UserMapper;

@WebService(targetNamespace = "http://api.xiangmu.ren/", endpointInterface = "ren.xiangmu.creatorapi.services.ITestService", serviceName = "test")
public class TestServiceImpl extends BaseService implements ITestService {
	
	@Autowired
	private UserMapper userMapper;

	public TestServiceImpl() {

	}

	public String test() {
		return "Hello world";
	}

	public String testUser(String name) {
		List<User> list = userMapper.findUsersByName(name);
		return bean2Xml(list);
	}
}
