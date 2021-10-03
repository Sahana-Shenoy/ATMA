package com.atma.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/add")
public class MyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int i = Integer.parseInt(req.getParameter("num1"));
		int j = Integer.parseInt(req.getParameter("num2"));
		int sum = i + j;
		/*
		 * Cookie cookie=new Cookie("k",sum+""); res.addCookie(cookie);
		 * res.sendRedirect("sq");
		 */
		PrintWriter out = res.getWriter();
		out.print("<html><body bgcolor='orange'>");
		out.println("result is :" + sum);
		out.print("</html>"+"</body>");
		res.sendRedirect("/ATMA/temp/Atma/index.html");
		
	}

}
