package com.st;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


public class BalanceSPTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rtp4203", "root", "root");
		String sql = "{call GET_BALANCES(?,?,?,?,?)}";
		//String sql = "{call GET_FEATURES(?,?,?,?,?)}";
		CallableStatement cs = connection.prepareCall(sql);

		cs.registerOutParameter(4, Types.VARCHAR);
		cs.registerOutParameter(5, Types.VARCHAR);

		cs.setString(1, "web");
		cs.setString(2, "online");
		cs.setString(3, "5211140058239");
						
		cs.execute();
		ResultSet rs = cs.executeQuery();
		while( rs.next()){
			System.out.println(rs.getString(1) + "--" + rs.getString(2) + "--" + rs.getString(3)
			+ "--" + rs.getString(4)+ "--" + rs.getString(5)/*+ "--" + rs.getString(6)*/);
		}
		System.out.println(cs.getString(4));
		System.out.println(cs.getString(5));


	}

}
