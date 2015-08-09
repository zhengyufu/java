package com.fzy.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fzy.framework.mvc.ActionBase;
import com.fzy.framework.pojo.PojoBase;
import com.fzy.web.pojo.UserInfo;

public class UserAction implements ActionBase {

	@Override
	public String execute(HttpServletRequest request,PojoBase form,Map<String,String> forword) {

		UserInfo userInfo = (UserInfo)form;
		
		String url=forword.get("success");

		
		return url;
	}

	
}
