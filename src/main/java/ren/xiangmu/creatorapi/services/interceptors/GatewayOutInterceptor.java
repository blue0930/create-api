package ren.xiangmu.creatorapi.services.interceptors;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("gatewayOutInterceptor")
public class GatewayOutInterceptor extends AbstractPhaseInterceptor<Message> {

	private Logger logger = LoggerFactory.getLogger(GatewayOutInterceptor.class);

	public GatewayOutInterceptor() {
		super(Phase.PRE_STREAM); // 触发点在流关闭之前
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		try {
			OutputStream os = message.getContent(OutputStream.class);
			CachedStream cs = new CachedStream();
			message.setContent(OutputStream.class, cs);
			message.getInterceptorChain().doIntercept(message);
			CachedOutputStream csnew = (CachedOutputStream) message.getContent(OutputStream.class);
			InputStream in = csnew.getInputStream();

			String result = IOUtils.toString(in, "UTF-8");
			
			//增加消息体
			logger.info("返回给客户端值：" + result);
			/**
			 * 这里可以对result做处理，如可以对result进行加密，把密文返回给客户端 处理完后同理，写回流中
			 */
			IOUtils.copy(new ByteArrayInputStream(result.getBytes("UTF-8")), os);

			cs.close();
			os.flush();
			message.setContent(OutputStream.class, os);
		} catch (Exception e) {
			logger.error("GatewayOutInterceptor异常", e);
		}

	}

	private class CachedStream extends CachedOutputStream {
		public CachedStream() {
			super();
		}

		protected void doFlush() throws IOException {
			currentStream.flush();
		}

		protected void doClose() throws IOException {
		}

		protected void onWrite() throws IOException {
		}

	}

}
