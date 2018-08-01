package ren.xiangmu.creatorapi.services.interceptors;

import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	private SAAJInInterceptor saa = new SAAJInInterceptor();
	
	public AuthInterceptor() {
		super(Phase.PRE_PROTOCOL);
		getAfter().add(SAAJInInterceptor.class.getName());
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		SOAPMessage mess = message.getContent(SOAPMessage.class);
		if (mess == null) {
			saa.handleMessage(message);
			mess = message.getContent(SOAPMessage.class);
		}
		SOAPHeader head = null;
		try {
			head = mess.getSOAPHeader();
		} catch (Exception e) {
			logger.error("getSOAPHeader error: {}", e.getMessage(), e);
		}
		if (head == null) {
			throw new Fault(new IllegalArgumentException("找不到Header，无法验证用户信息"));
		}

		NodeList users = head.getElementsByTagName("username");
		NodeList passwords = head.getElementsByTagName("password");
		if (users.getLength() < 1) {
			throw new Fault(new IllegalArgumentException("找不到用户信息"));
		}
		if (passwords.getLength() < 1) {
			throw new Fault(new IllegalArgumentException("找不到密码信息"));
		}

		String userName = users.item(0).getTextContent().trim();
		String password = passwords.item(0).getTextContent().trim();

	}

}
