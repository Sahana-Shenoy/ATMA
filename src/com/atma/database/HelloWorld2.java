package com.atma.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class HelloWorld2 {

	public static void main(String[] args) {
		Demo d = new Demo();
		d.getdata();
		System.out.println();

	}
}

class User1 {
	int phone;
	String regid, fname, lname, password, email, city, type;

	/*
	 * public User1(int phone, String regid, String fname, String lname, String
	 * password, String email, String city, String type) { super(); this.phone =
	 * phone; this.regid = regid; this.fname = fname; this.lname = lname;
	 * this.password = password; this.email = email; this.city = city; this.type =
	 * type; }
	 */
	class Demo1 {
		public void getdata() {
			try {

				String url = "jdbc:mysql://localhost:3306/atma";// change
				String uname = "root";
				String pwd = "atma";// change
				String query = "insert into user values(?,?,?,?,?,?,?,?)";

				Connection con = DriverManager.getConnection(url, uname, pwd);
				PreparedStatement st = con.prepareStatement(query);
				st = con.prepareStatement(query);
				// st.setString(1, photo_num);
				User1 s = new User1();
				/* phone, regid, fname, lname, password, email, city, type */
				s.regid = "sac18106";
				s.password = "sac18105";
				s.fname = "sachin";
				s.lname = "sachin";
				s.phone = 1234567890;
				s.email = "sachindid@gmail.com";
				s.city = "bangalore";
				s.type = "ADMIN";

				st.setString(1, regid);
				st.setString(2, password);
				st.setString(3, fname);
				st.setString(4, lname);
				st.setString(5, email);
				st.setInt(6, phone);
				st.setString(7, city);
				st.setString(8, type);

				/*
				 * ResultSet rs = st.executeQuery(); rs.next();
				 */

				// Class.forName("com.mysql.cj.jdbc.Driver");
				/*
				 * Connection con = DriverManager.getConnection(url, uname, pwd); Statement st =
				 * con.createStatement();
				 */

				int c = st.executeUpdate(query);
				System.out.println(c + " rows affected");
				/* rs.close(); */
				st.close();
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				System.out.println("Done");
			}
		}
	}

}