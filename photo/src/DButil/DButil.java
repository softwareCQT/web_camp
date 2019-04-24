package DButil;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DButil {
	private static String url = "jdbc:mysql://000.000.000.000:3306/chenqiting?serverTimezone=UTC";
	private static String username = "root";
	private static String password = "18244958701";
	private static String jdbcname = "com.mysql.cj.jdbc.Driver";

	public static Connection getCon() {
		try {
			Class.forName(jdbcname);

		} catch (ClassNotFoundException e) {
			System.out.println("");
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void close(PreparedStatement pstmt, Connection con,ResultSet rs) {
	 if (pstmt != null) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	if (con != null) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if (rs != null) {
		try {
			rs.close();
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
  }
}


