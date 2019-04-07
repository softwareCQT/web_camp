package chen.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DbUtil {
		private static String url = "jdbc:mysql://000.000.000.000:3306/chenqiting?serverTimezone=UTC";
		private static String username = "root";
		private static String password = "18244958701";
		private static String jdbcname = "com.mysql.cj.jdbc.Driver";

		public Connection getCon() {
			try {
				Class.forName(jdbcname);

			} catch (ClassNotFoundException e) {
				System.out.println("加载数据库连接失败");
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

		public void close(PreparedStatement pstmt, Connection conn) {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}


