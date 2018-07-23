package ren.xiangmu.creatorapi.services;

import java.util.Iterator;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

public class BaseService {
	
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
}
