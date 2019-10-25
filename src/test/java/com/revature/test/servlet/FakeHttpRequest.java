package com.revature.test.servlet;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class FakeHttpRequest implements HttpServletRequest {
	private final Map<String, Object> attributes = new LinkedHashMap<>();
	
	 @Override public Object getAttribute(String name) {
	   return attributes.get(name);
	 }

	 @Override public void setAttribute(String name, Object value) {
	   attributes.put(name, value);
	 }
}
