package com.atma.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Register check using adduser()
//Login check using check()
public class LoginDAO {

	public void adduser(String regid, String password, String fname, String lname, String email, long phone,
			String city, String type) {
		try {
			String url = "jdbc:mysql://localhost:3306/atma";
			String uname = "root";
			String pwd = "atma";
			String query = "insert into user values(?,?,?,?,?,?,?,?)";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			PreparedStatement st = con.prepareStatement(query);

			st.setString(1, regid);
			st.setString(2, password);
			st.setString(3, fname);
			st.setString(4, lname);
			st.setString(5, email);
			st.setLong(6, phone);
			st.setString(7, city);
			st.setString(8, type);

			int c = st.executeUpdate();
			System.out.println(c + " rows affected");
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean[] check(String email, String pass) {
		boolean[] b = new boolean[2];
		b[0] = false;
		b[1] = false;
		try {
			String url = "jdbc:mysql://localhost:3306/atma";
			String uname = "root";
			String pwd = "atma";

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			String query = "select email from user where email=?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, email);

			ResultSet r = st.executeQuery();
			System.out.println("2");
			if (r.next()) {
				System.out.println("enter1");
				if ((email.compareTo(r.getString("email")) == 0)) {
					b[0] = true;
					System.out.println("2");
				}
			}
			System.out.println("2");
			String query2 = "select password from user where password=?";
			st = con.prepareStatement(query2);
			st.setString(1, pass);
			r = st.executeQuery();
			System.out.println("2");
			if (r.next()) {
				System.out.println("enter2");
				if ((pass.compareTo(r.getString("password")) == 0)) {
					b[1] = true;
					System.out.println("2");
				}
			}

			st.close();
			con.close();
			r.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Email: " + b[0] + ", Password:" + b[1]);
		}
		return b;
	}

	public long getPhone(String email) {
		long phone = 0;
		try {
			String url = "jdbc:mysql://localhost:3306/atma";
			String uname = "root";
			String pwd = "atma";

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			String query = "select phone from user where email=?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, email);

			ResultSet r = st.executeQuery();

			if (r.next()) {
				System.out.println("got phone");
				phone = r.getLong("phone");
			}
			st.close();
			con.close();
			r.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Phone: " + phone);
		}
		return phone;
	}

	/*
	 * public static void main(String[] args) { LoginDAO u = new LoginDAO();
	 * boolean[] i=u.check("sachinvermaboss@gmail.com","12345");
	 * System.out.println(i[0]+" "+i[1]); }
	 */

}