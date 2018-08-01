package ren.xiangmu.creatorapi.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import ren.xiangmu.creatorapi.services.impl.TestServiceImpl;
import ren.xiangmu.creatorapi.services.interceptors.AppInboundInterceptor;
import ren.xiangmu.creatorapi.services.interceptors.AppOutboundInterceptor;
import ren.xiangmu.creatorapi.services.interceptors.GatewayOutInterceptor;

@Configuration
@EnableWebMvc
public class CXFConfig {

	private static final Logger LOG = LoggerFactory.getLogger(CXFConfig.class);

	public static final String URL_FOLDER_REST = "/rest";

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public ServletRegistrationBean dispatcherServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/services/*");
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		SpringBus springBus = new SpringBus();
		springBus.getInInterceptors().add(new AppInboundInterceptor());
		springBus.getOutInterceptors().add(new AppOutboundInterceptor());
		springBus.getOutInterceptors().add(new GatewayOutInterceptor());
		return springBus;
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), new TestServiceImpl());
		endpoint.getFeatures().add(new LoggingFeature());
		endpoint.publish("/test");
		return endpoint;
	}

	@Bean("jsonProvider") // 构造一个json转化bean，用于将student转化为json，因为后面需要用这个bean配置json转化，所以给他取个名
	public JacksonJsonProvider getJacksonJsonProvider() {
		return new JacksonJsonProvider();

	}

	@Bean
	public Server jaxRsServer() {
		// List<Object> restServiceBeans = new
		// ArrayList<>(this.applicationContext.getBeansOfType(RestService.class).values());
		Collection<Object> restServices = findRestServices();
		if (restServices.size() > 0) {
			for (Object o : restServices) {
				LOG.info("111:" + o.getClass().toString());
			}
		}
		if (restServices.isEmpty()) {
			LOG.info("No REST Services have been found. Rest Endpoint will not be enabled in CXF.");
			return null;
		}
		Collection<Object> providers = this.applicationContext.getBeansWithAnnotation(Provider.class).values();

		if (providers.size() > 0) {
			for (Object o : providers) {
				LOG.info("222:" + o.getClass().toString());
			}
		}

		JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
		factory.setBus(springBus());
		factory.setAddress(URL_FOLDER_REST);
		factory.setServiceBeans(new ArrayList<>(restServices));
		factory.setProviders(new ArrayList<>(providers));
		Map<Object, Object> extMaps = new HashMap<Object, Object>();
		extMaps.put("json", "application/json");
		extMaps.put("xml", "application/xml");
		factory.setExtensionMappings(extMaps);

		return factory.create();
	}

	private Collection<Object> findRestServices() {
		return this.applicationContext.getBeansWithAnnotation(Path.class).values();
	}
}