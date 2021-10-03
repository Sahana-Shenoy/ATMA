package com.atma.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloWorld {

	public static void main(String[] args) {
		Demo d = new Demo();
		d.getdata();
		System.out.println();

	}
}

class Demo {
	public void getdata() {
		try {

			String url = "jdbc:mysql://localhost:3306/atma";// change
			String uname = "root";
			String pwd = "atma";// change

			String query = "select * from user";
			Connection con = DriverManager.getConnection(url, uname, pwd);
			PreparedStatement st = con.prepareStatement(query);
			st = con.prepareStatement(query);
			// st.setString(1, photo_num);
			ResultSet rs = st.executeQuery();
			rs.next();
			
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));

			User s = new User();
			s.regid = "sac18106";
			s.password = "sac18105";
			s.fname = "sachin";
			s.lname = "sachin";
			s.phone = 1234567890;
			s.email = "sachindid@gmail.com";
			s.city = "bangalore";
			s.type = "ADMIN";

			// Class.forName("com.mysql.cj.jdbc.Driver");
			// this pointer is on row and to next row we get desired data
			// String name = rs.getInt(1) + " : " + rs.getString(1);
			// here the number is column number from 1 not 0
			System.out.println(rs);
			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Done");
		}
	}
}

class User {
	int phone;
	String fname, regid, lname, password, email, city, type;

}
