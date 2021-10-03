package com.atma.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atma.database.Cancel_ReviewDAO;

public class Cancel extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
// bookingid,totalcost,bookdate,status,memebers,tourid,regid
		String join1 = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + "<style>\r\n"
				+ ".button {\r\n" + " border-radius: 4px;\r\n" + " background-color: #0000FF;\r\n"
				+ " border: none;\r\n" + " color: #FFFFFF;\r\n" + " text-align: center;\r\n" + " font-size: 20px;\r\n"
				+ " padding: 20px;\r\n" + " width: 200px;\r\n" + " transition: all 0.5s;\r\n" + " cursor: pointer;\r\n"
				+ " margin: 5px;\r\n" + "}\r\n" + "\r\n" + ".button span {\r\n" + " cursor: pointer;\r\n"
				+ " display: inline-block;\r\n" + " position: relative;\r\n" + " transition: 0.5s;\r\n" + "}\r\n"
				+ "\r\n" + ".button span:after {\r\n" + " content: '\\00bb';\r\n" + " position: absolute;\r\n"
				+ " opacity: 0;\r\n" + " top: 0;\r\n" + " right: -20px;\r\n" + " transition: 0.5s;\r\n" + "}\r\n"
				+ "\r\n" + ".button:hover span {\r\n" + " padding-right: 25px;\r\n" + "}\r\n" + "\r\n"
				+ ".button:hover span:after {\r\n" + " opacity: 1;\r\n" + " right: 0;\r\n" + "}\r\n" + "\r\n" + "h2\r\n"
				+ "{\r\n" + " font-size: 40px;\r\n" + " margin: 5px;\r\n" + " color:red;\r\n"
				+ " padding-bottom:50px;\r\n" + "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
				+ "<h2>Invalid!!!";
		String join2 = " </h2>\r\n" + "\r\n"
				+ "<a href=\"cancel.jsp\"><button class=\"button\"><span>Enter Again </span></button></a>\r\n"
				+ "</body>\r\n" + "</html>";

		PrintWriter out = res.getWriter();
		try {
// System.out.println(req.getParameter("firstname"));
			if (req.getParameter("reason") == "") {
				out.println(join1 + "Reason" + join2);

			} else {

				HttpSession session = req.getSession();
				String regid = (String) session.getAttribute("regid");
				String tourid = (String) session.getAttribute("tourid2");
				session.removeAttribute("tourid2");
				String reason = req.getParameter("reason");
				Cancel_ReviewDAO c = new Cancel_ReviewDAO();
// String tourid,String regid,String reason
// get tourid from bookingid
				c.updateStatus(tourid);
				c.cancelTour(tourid.substring(tourid.indexOf(".") + 1), regid, reason);
				res.sendRedirect("UserDashIndex.jsp");
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error occured");
		}

	}

}