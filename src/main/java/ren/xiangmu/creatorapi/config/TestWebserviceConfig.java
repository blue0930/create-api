package ren.xiangmu.creatorapi.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ren.xiangmu.creatorapi.services.ITestService;
import ren.xiangmu.creatorapi.services.impl.TestServiceImpl;

@Configuration
public class TestWebserviceConfig {

	@Bean
	public ServletRegistrationBean dispatcherServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/service/*");// 发布服务名称
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public ITestService testService() {
		return new TestServiceImpl();
	}

	@Bean
	public Endpoint endpoint() {
		Endpoint endpoint = new EndpointImpl(springBus(), testService());// 绑定要发布的服务
		endpoint.publish("/test"); // 显示要发布的名称
		return endpoint;
	}
}
