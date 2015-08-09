package com.fzy.framework.listener;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jdom2.JDOMException;

import com.fzy.framework.config.XmlBean;
import com.fzy.framework.util.StrutsConfig;

public class ActionListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext context = arg0.getServletContext();
		
		String xmlPath = context.getInitParameter("struts-config");
		
		String realPath = context.getRealPath("\\");
		try {
			Map<String,XmlBean> map = StrutsConfig.getConfig(realPath+xmlPath);
			
			arg0.getServletContext().setAttribute("struts", map);
			
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
