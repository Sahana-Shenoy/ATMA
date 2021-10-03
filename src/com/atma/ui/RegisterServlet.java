package com.atma.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atma.database.LoginDAO;

public class RegisterServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String join1 = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + "<style>\r\n"
				+ ".button {\r\n" + "  border-radius: 4px;\r\n" + "  background-color:    #0000FF;\r\n"
				+ "  border: none;\r\n" + "  color: #FFFFFF;\r\n" + "  text-align: center;\r\n"
				+ "  font-size: 20px;\r\n" + "  padding: 20px;\r\n" + "  width: 200px;\r\n"
				+ "  transition: all 0.5s;\r\n" + "  cursor: pointer;\r\n" + "  margin: 5px;\r\n" + "}\r\n" + "\r\n"
				+ ".button span {\r\n" + "  cursor: pointer;\r\n" + "  display: inline-block;\r\n"
				+ "  position: relative;\r\n" + "  transition: 0.5s;\r\n" + "}\r\n" + "\r\n"
				+ ".button span:after {\r\n" + "  content: '\\00bb';\r\n" + "  position: absolute;\r\n"
				+ "  opacity: 0;\r\n" + "  top: 0;\r\n" + "  right: -20px;\r\n" + "  transition: 0.5s;\r\n" + "}\r\n"
				+ "\r\n" + ".button:hover span {\r\n" + "  padding-right: 25px;\r\n" + "}\r\n" + "\r\n"
				+ ".button:hover span:after {\r\n" + "  opacity: 1;\r\n" + "  right: 0;\r\n" + "}\r\n" + "\r\n"
				+ "h2\r\n" + "{\r\n" + "    font-size: 40px;\r\n" + "    margin: 5px;\r\n" + "    color:red;\r\n"
				+ "    padding-bottom:50px;\r\n" + "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
				+ "<h2>Invalid!!!";
		String join2 = "</h2>\r\n" + "\r\n"
				+ "<a href=\"signup.html\"><button class=\"button\"><span>Enter Again </span></button></a>\r\n"
				+ "</body>\r\n" + "</html>";

		try {
			PrintWriter out = res.getWriter();
			if (req.getParameter("fname") == "" || req.getParameter("lname") == "" || req.getParameter("Phone") == ""
					|| req.getParameter("email") == "" || req.getParameter("pass") == ""
					|| req.getParameter("city") == "") {

				out.println(join1);
				if (req.getParameter("fname") == "") {
					out.println("FirstName,");
				}
				if (req.getParameter("lname") == "") {
					out.println("LastName,");
				}
				if (req.getParameter("Phone") == "") {
					out.println("Phone,");
				}
				if (req.getParameter("email") == "") {
					out.println("Email,");
				}
				if (req.getParameter("pass") == "") {
					out.println("Password,");
				}
				if (req.getParameter("city") == "") {
					out.println("City,");
				}
				out.println(".");
				out.println(join2);

			} else {

				String fname = req.getParameter("fname");
				String lname = req.getParameter("lname");
				long phone = Long.parseLong(req.getParameter("Phone"));

				String email = req.getParameter("email");
				String password = req.getParameter("pass");
				String city = req.getParameter("city");

				LoginDAO u = new LoginDAO();

				out.println(join1);
				boolean[] b = u.check(email, password);

				if (b[0] == true || b[1] == true) {

					if (b[0] == true) {
						out.println("Email(Taken),");
					}
					if (b[1] == true) {
						out.println("Password (Taken),");
					}
					if (req.getParameter("city") == "") {
						out.println("City,");
					}
					out.println(".");
					out.println(join2);
				} else {
//                    String temp1 = Long.toString(phone);
//                    String temp2 = email.substring(0, 4);
//                    String temp3 = temp1.substring(5, 9);

					u.adduser(email + "." + phone, password, fname, lname, email, phone, city, "User");

					// u.adduser("aaa001", "abcde1", "Meena", "K","meena@gmail.com",12345,
					// "Bangalore", "User");
					res.sendRedirect("index.html");
				}
			}
			/*
			 * Cookie cookie=new Cookie("k",sum+""); res.addCookie(cookie);
			 * res.sendRedirect("sq");
			 */
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error occured");

		}
	}

}