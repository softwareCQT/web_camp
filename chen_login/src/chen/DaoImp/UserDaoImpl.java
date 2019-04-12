package chen.DaoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chen.Dao.UserDao;
import chen.Util.DbUtil;
import chen.model.User;

public class UserDaoImpl implements UserDao {
	private static DbUtil dbUtil = new DbUtil(); //全局可用连接
	@Override
	public Boolean login(User user) {
		Boolean flag = false;
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from login where userID=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				if (user.getPWD().equals(rs.getString("upwd"))) 
				{
					flag = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		if (flag == true) 
			return true;
		else 
			return false;			
}
		
	@Override//修改姓名和密码
	public boolean updateUser(User user) throws Exception {
		PreparedStatement pstmt = null;
        Connection con = dbUtil.getCon();
		int result = 0;
		try {
			String sql = "update login set upwd=?,name=?  where userID=?";
			pstmt = con .prepareStatement(sql);
			pstmt.setString(1,user.getPWD());
			pstmt.setString(2,user.getName());
			pstmt.setString(3, user.getID());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
      //验证
	  if(result != 0)
		  return true;
	  else
		  return false;
}

	@Override //注册
	public boolean addUser(User user) throws Exception {
		PreparedStatement pstmt = null;
		Connection con = dbUtil.getCon();
		int result = 0;
		try {
			String sql = "insert into login values(?,?,?,?)";
			pstmt = con .prepareStatement(sql);
			pstmt.setString(1, user.getID());
			pstmt.setString(2, user.getName());
			pstmt.setInt(3, 1);            //注册时权限只能是普通人
			pstmt.setString(4, user.getPWD());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
      //验证
	  if(result != 0)
		  return true;
	  else
		  return false;
	}
	@Override
	public boolean userIDIsExist(String ID) throws Exception {
		PreparedStatement pstmt = null;
		Connection con = dbUtil.getCon();
		ResultSet rs = null;
		Boolean flag = false;
		try {
			String sql = "select userID from login where userID=?";
			pstmt = con.prepareStatement(sql);		
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			if(rs != null)
			{
				if(!rs.next())
				   flag = false;
				else 
			      flag = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	  return flag;	
  }
	@Override
	public int userPriority(String ID)
	{
		PreparedStatement pstmt = null;
		Connection con = dbUtil.getCon();
		ResultSet rs = null;
		int result = 0;
		try {
			String sql = "select * from login where userID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
			   result = rs.getInt("priority");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	return result;
}
	@Override 
    public List<User> QueryAll() {
    	Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		User user = null;
		List<User> users = new ArrayList<>();
		ResultSet rs = null ;
		try {
			  String sql = "select * from login";
			  pstmt = con.prepareStatement(sql);
			  rs = pstmt.executeQuery();
			  while(rs.next()) {
				  String ID = rs.getString("userID");
				  String name = rs.getString("name");
				  int priority = rs.getInt("priority");
				  users.add(new User(ID, name, priority));
			  }
			  return users ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null ; 
		}catch (Exception e) {
			e.printStackTrace();
			return null ; 
		}
		finally {
			dbUtil.close(pstmt, con);
				}
		}
	@Override 
    public User Query(String ID)
    {
    	Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		User user = null;
		ResultSet rs = null ;
		try {
			  String sql = "select * from login where  userID = ?";
			  pstmt = con.prepareStatement(sql);
			  pstmt.setString(1, ID);
			  rs = pstmt.executeQuery();
			  if(rs.next()) {
				  String ID1 = rs.getString("userID");
				  String name = rs.getString("name");
				  int priority = rs.getInt("priority");
				  String pwd = rs.getString("upwd");
				  user = new User(ID1,pwd,priority,name);
			  }
			  return user ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null ; 
		}catch (Exception e) {
			e.printStackTrace();
			return null ; 
		}
		finally {
			dbUtil.close(pstmt, con);
				}
		}
	@Override 
	public Boolean Beupdate(int priority,String ID) //修改权限
	{
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		User user = null;
		int result = 0 ;
		try {
			  String sql = "update login set priority=? where userID=?";
			  pstmt = con.prepareStatement(sql);
			  pstmt.setInt(1, priority);
			  pstmt.setString(2, ID);
			  result = pstmt.executeUpdate();
			  if(result == 1) {
				  return true;
			  }
			  return false ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null ; 
		}catch (Exception e) {
			e.printStackTrace();
			return null ; 
		}
		finally {
			dbUtil.close(pstmt, con);
				}
	}
 }
