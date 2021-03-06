package ren.xiangmu.creatorapi.services.interceptors;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.message.Message;

@InInterceptors
public class AppInboundInterceptor extends LoggingInInterceptor {

	@Override
	public void handleMessage(Message message) throws Fault {
		processPayLoad(message);
		super.handleMessage(message);
	}

	private void processPayLoad(Message message) {
		System.out.println("*** PROCESSING PAYLOAD AT IN-INTERCEPTOR **");
	}
}
