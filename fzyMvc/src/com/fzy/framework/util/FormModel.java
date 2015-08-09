package com.fzy.framework.util;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import com.fzy.framework.pojo.PojoBase;

public class FormModel {

	public static PojoBase parse(HttpServletRequest request)
	{
		PojoBase o=null;
		
		try
		{
			Class cls = Class.forName(request.getAttribute("form").toString());
			o = (PojoBase) cls.newInstance();
			
			Field[] fields = cls.getDeclaredFields();
			for(Field f:fields)
			{
				f.setAccessible(true);
				
				f.set(o, request.getParameter(f.getName()));
				
				f.setAccessible(false);
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return o;
	}
}
