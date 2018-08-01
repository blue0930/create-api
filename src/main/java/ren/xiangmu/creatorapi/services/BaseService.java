package ren.xiangmu.creatorapi.services;

import java.util.Iterator;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

import ren.xiangmu.creatorapi.beans.ResponseMessage;
import ren.xiangmu.creatorapi.enums.ResultEnum;
import ren.xiangmu.creatorapi.utils.BasicUtils;

public class BaseService<T> {
	
	public String bean2Xml(Object o) {
		XStream xstream = new XStream();
		// xstream.alias("response", LabResponse.class);
		// xstream.alias("labItem", LabItem.class);
		String xmlStr = xstream.toXML(o);
		String head = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlStr = head + xmlStr;
		return xmlStr;
	}

	public String bean2Xml(Object o, Map<String, Class> excludeMap) {
		XStream xstream = new XStream();
		if (!excludeMap.isEmpty()) {
			Iterator<Map.Entry<String, Class>> iterator = excludeMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Class> entry = iterator.next();
				xstream.alias(entry.getKey(), entry.getValue());
			}
		}
		// xstream.alias("response", LabResponse.class);
		// xstream.alias("labItem", LabItem.class);
		String xmlStr = xstream.toXML(o);
		String head = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlStr = head + xmlStr;
		return xmlStr;
	}
	
	public ResponseMessage success(T value) {
		return BasicUtils.success(value);
	}
	
	/**
	 * 操作失败返回的消息
	 * @param code
	 * @param msg
	 * @return
	 */
	public ResponseMessage error(int code,String msg) {
	    return BasicUtils.error(code, msg);
	}

	/**
	 * 操作失败返回消息，对error的重载
	 * @param resultEnum
	 * @return
	 */
	public ResponseMessage error(ResultEnum resultEnum){
	    return BasicUtils.error(resultEnum);
	}
}
