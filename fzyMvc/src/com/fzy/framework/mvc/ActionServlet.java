package com.fzy.framework.mvc;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fzy.framework.config.XmlBean;
import com.fzy.framework.pojo.PojoBase;
import com.fzy.framework.util.FormModel;
import com.fzy.web.action.UserAction;

public class ActionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		String path = getPath(request);
		Map<String,XmlBean> map = (Map<String,XmlBean>)this.getServletContext().getAttribute("struts");

		XmlBean bean = map.get(path);
		String formClass = bean.getActionForm();
		request.setAttribute("form", formClass);
		
		PojoBase form = FormModel.parse(request);
		
		String actionClass = bean.getActionClass();
		
		ActionBase action= null;
		String url ="";
		try
		{
			Class clazz = Class.forName(actionClass);
			action = (ActionBase)clazz.newInstance();
			url = action.execute(request,form,bean.getForward());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doGet(request,response);
	}
	
	public String getPath(HttpServletRequest request)
	{
		String path = request.getServletPath();
		
		return path.split("\\.")[0];
	}
	
}
