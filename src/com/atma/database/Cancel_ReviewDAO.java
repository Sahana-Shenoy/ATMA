package com.atma.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Cancel_ReviewDAO {

	public void cancelTour(String tourid, String regid, String reason) {
		try {
			String url = "jdbc:mysql://localhost:3306/atma";
			String uname = "root";
			String pwd = "atma";

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			String query = "insert into cancellation values (?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(query);
//cancelid,reason,refstatus,regid3,tourid3;
			st.setString(1, reason.substring(0, 10) + "." + tourid);
			st.setString(2, reason);
			st.setInt(3, 0);// admin can change
			st.setString(4, regid);
			st.setString(5, tourid);

			int r = st.executeUpdate();
			System.out.println(r + " rows affected");

			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error");
		}
	}

	public void updateStatus(String bookingid) {
		try {
			String url = "jdbc:mysql://localhost:3306/atma";
			String uname = "root";
			String pwd = "atma";

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			String query = "update booking set status=2 where bookingid=?";
			PreparedStatement st = con.prepareStatement(query);
//cancelid,reason,refstatus,regid3,tourid3;
			st.setString(1, bookingid);

			int r = st.executeUpdate();
			System.out.println(r + " rows affected");

			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error");
		}
	}

	public static void main(String[] args) {
//cancelid,reason,refstatus,regid3,tourid3;
		Cancel_ReviewDAO d = new Cancel_ReviewDAO();
		d.updateStatus("Bus.Delhi.Rajghat");
	}

}