package com.fzy.framework.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.fzy.framework.config.XmlBean;;

public class StrutsConfig {

	public StrutsConfig()
	{
		
	}
	
	public static Map<String,XmlBean> getConfig(String xmlPath) throws JDOMException, IOException
	{
		Map<String,XmlBean> map = new HashMap<String,XmlBean>();
		
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(new File(xmlPath));
		
		Element root = document.getRootElement();
		Element actionRoot = root.getChild("action-mapping");
		
		List<Element> actionList = actionRoot.getChildren();
		
		for(Element e:actionList)
		{
			XmlBean bean = new XmlBean();
			
			bean.setActionName(e.getAttributeValue("name"));
			bean.setActionClass(e.getAttributeValue("class"));
			bean.setActionForm(e.getAttributeValue("form"));
			List<Element> forwards = e.getChildren();
			
			Map<String,String> forwardMap = new HashMap<String,String>();
			for(Element f:forwards)
			{
				forwardMap.put(f.getAttributeValue("name"),f.getAttributeValue("value"));
			}
			bean.setForward(forwardMap);
			
			map.put(e.getAttributeValue("path"), bean);
		}
		
		return map;
	}
}
