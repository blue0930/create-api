package ren.xiangmu.creatorapi;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class WebServiceTest {

	public static void main(String[] args) throws Exception {
		 
//		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
//		Client client = clientFactory.createClient("http://localhost:8080/service/test?wsdl");
//		Object[] objects = client.invoke("test");
//		System.out.println(objects[0].toString());
		
		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
		Client client = clientFactory.createClient("http://localhost:8080/service/test?wsdl");
		Object[] objects = client.invoke("testUser", "wang");
		System.out.println(objects[0].toString());
	}

}
