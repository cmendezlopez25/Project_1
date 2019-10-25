package com.revature.test.servlet;

import javax.servlet.http.HttpServletResponse;

public abstract class FakeHttpResponse implements HttpServletResponse {
	@Override
	public void sendRedirect(String location) {
		
	}
}
