package com.fzy.framework.config;

import java.util.Map;

public class XmlBean {

	public XmlBean()
	{
		
	}
	
	private String actionName;
	private String actionClass;
	private String actionForm;
	private Map<String,String> forward;
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionClass() {
		return actionClass;
	}
	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}
	public String getActionForm() {
		return actionForm;
	}
	public void setActionForm(String actionForm) {
		this.actionForm = actionForm;
	}

	public Map<String, String> getForward() {
		return forward;
	}
	public void setForward(Map<String, String> forward) {
		this.forward = forward;
	}
	
}
