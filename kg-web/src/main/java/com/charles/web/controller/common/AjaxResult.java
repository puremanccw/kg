package com.charles.web.controller.common;

import java.util.HashMap;
import java.util.Map;


public class AjaxResult {
	
	private Info info;
	
	@SuppressWarnings("rawtypes")
	private Map data;
	
	public AjaxResult(boolean isOK) {
		this.info = new Info(isOK);
	}
	
	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@SuppressWarnings("rawtypes")
	public Map getData() {
		if(data == null) {
			data = new HashMap();
		}
		return data;
	}
	
	@SuppressWarnings("rawtypes")
	public void setData(Map data) {
		this.data = data;
	}
	
	public static AjaxResult succResult() {
		return new AjaxResult(true);
	}
	
	public static AjaxResult succResult(String message) {
		AjaxResult result = new AjaxResult(true);
		result.getInfo().setMessage(message);
		return result;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static AjaxResult succResult(String key, Object value) {
		AjaxResult result = new AjaxResult(true);
		Map data = new HashMap<String, Object>();
		data.put(key, value);
		result.setData(data);
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public static AjaxResult succResult(Map dataMap) {
		AjaxResult result = new AjaxResult(true);
		result.setData(dataMap);
		return result;
	}
	
	public static AjaxResult errorResult(String message) {
		AjaxResult result = new AjaxResult(false);
		result.getInfo().setMessage(message);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
