package com.atma.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BookingDAO {

	public void book(String bookingid, double totalcost, String bookdate, int status, int member, String transmode,
			String tourid, String regid) {
		// bookingid,totalcost,bookdate,status,member,tourid,regid
		long phone = 0;
		try {
			String url = "jdbc:mysql://localhost:3306/atma";
			String uname = "root";
			String pwd = "atma";

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			String query = "insert into booking values (?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(query);

			// bookingid,totalcost,bookdate,status,member,tourid,regid
			st.setString(1, bookingid);
			st.setDouble(2, totalcost);
			st.setString(3, bookdate);
			st.setInt(4, status);
			st.setInt(5, member);
			st.setString(6, transmode);
			st.setString(7, tourid);
			st.setString(8, regid);

			int c = st.executeUpdate();
			System.out.println(c + " rows affected");
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public double getInfoTour(String s) {
		try {
			String url = "jdbc:mysql://localhost:3306/atma";
			String uname = "root";
			String pwd = "atma";

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			String query = "select cost from tours where tourid=?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, s);

			ResultSet r = st.executeQuery();

			if (r.next()) {
				double d = r.getDouble("cost");
				return d;
			}
			// System.out.println(c + " rows affected");
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public boolean reduceSeat(int s, String tourid) {
		try {
			String url = "jdbc:mysql://localhost:3306/atma";
			String uname = "root";
			String pwd = "atma";

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			String query = "select availseats from tours where tourid=?";
			PreparedStatement st = con.prepareStatement(query);

			st.setString(1, tourid);

			ResultSet r = st.executeQuery();
			if (r.next()) {
				int availseats = r.getInt("availseats");
				if (availseats == 0 || (availseats - s) < 0)
					return false;
			}

			// reduce it
			String query2 = "update tours set availseats=availseats-? where tourid=?";
			st = con.prepareStatement(query2);
			st.setInt(1, s);
			st.setString(2, tourid);
			int r2 = st.executeUpdate();
			System.out.println(r2 + " rows affected");

			st.close();
			con.close();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public UserDash[] getData(String regid) {
		try {
			String url = "jdbc:mysql://localhost:3306/atma";
			String uname = "root";
			String pwd = "atma";

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			String query = "select bookingid,bookdate,status from booking where regid=?";
			PreparedStatement st = con.prepareStatement(query);

			st.setString(1, regid);
			ResultSet r = st.executeQuery();

			UserDash[] u = new UserDash[100];
			int count2 = 0;
			while (r.next()) {
				u[count2] = new UserDash(r.getString("bookingid"), r.getString("bookdate"), r.getInt("status"), 0);
				count2++;
			}
			// System.out.println("c"+u[0].getCount());
			u[0].setCount(count2);
			System.out.println(u[0].getCount() + "entries found");
			st.close();
			r.close();
			con.close();
			return u;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error");
			UserDash[] u = new UserDash[100];
			u[0] = new UserDash("-", "-", 0, 0);
			// u[0].setCount(0);
			return u;
		}
	}

	/*
	 * public static void main(String[] args) { BookingDAO b = new BookingDAO();
	 * UserDash[] u = b.getData("abcdef@gmail.com.1234567890");
	 * System.out.println(u[1].toString()); for (int i = 0; i < u[0].getCount();
	 * i++) { System.out.println(u[i].toString()); } }
	 */

	public class UserDash {
		String Bookingid;
		String bookdate;
		int status;
		int count;

		public UserDash(String bookingid, String bookdate, int status, int count) {
			super();
			Bookingid = bookingid;
			this.bookdate = bookdate;
			this.status = status;
			this.count = count;
		}

		public String getBookingid() {
			return Bookingid;
		}

		public void setBookingid(String bookingid) {
			Bookingid = bookingid;
		}

		public String getBookdate() {
			return bookdate;
		}

		public void setBookdate(String bookdate) {
			this.bookdate = bookdate;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		@Override
		public String toString() {
			return "UserDash [Bookingid=" + Bookingid + ", bookdate=" + bookdate + ", status=" + status + ", count="
					+ count + "]";
		}

	}

}