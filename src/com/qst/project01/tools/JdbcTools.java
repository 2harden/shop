package com.qst.project01.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTools {

	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/shop";
			String user = "root";
			String password = "0320";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void Close(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int exec_update(String sql, Object... para) {
		int i = 0;
		try {
			Connection conn = JdbcTools.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);

			System.out.println(para.length);

			for (int j = 0; j < para.length; j++) {
				ps.setObject(j + 1, para[j]);
			}
			i = ps.executeUpdate();
			
			System.out.println("共" + i + "行受到影响");
			JdbcTools.Close(null, ps, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
