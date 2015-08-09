package com.fzy.framework.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fzy.framework.pojo.PojoBase;

public interface ActionBase {
	String execute(HttpServletRequest request,PojoBase form,Map<String,String> forword);
}
