package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DButil.AppMD5Util;
import DButil.DButil;
import model.User;
import model.photo;

public class userDaoImpl implements userDao {
	private static DButil db = new DButil();   //通用数据库接口
	private static Connection con = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	
	@Override
	public boolean update(User Y) {
		boolean flag = false;//标志是否成功
		try {
			  String sql = "UPDATE user SET name=?,sex=?,password=? where ID=?";//进行连接和分配
	          con = db.getCon();
	          pstmt = con.prepareStatement(sql);
	          pstmt.setString(1, Y.getName());
	          pstmt.setString(2, Y.getSex()); 
	          //密码要经过MD5加密
	          pstmt.setString(3, AppMD5Util.getMD5(Y.getPassword()));
	          pstmt.setString(4, Y.getId());
	          
	          if (pstmt.executeUpdate() > 0) //判断是否修改成功
	        	  	flag = true;
	   }catch (SQLException e){ 
		   e.printStackTrace();
	   } catch (Exception e) {
			e.printStackTrace();
		}finally {
			 db.close(pstmt, con, rs);
	   }
	   return flag;
		
	}

	@Override
	public boolean login(String ID, String password) {
		Boolean flag = false;
		try {
			String sql = "select * from user where ID=? ";
			String spassword = AppMD5Util.getMD5(password);
			con = db.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ID);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs != null&&rs.next())   
			   flag = spassword.equals(rs.getString("password"));
			   	   
		 } catch (SQLException e) {
			e.printStackTrace();
		 }  catch (Exception e) {
			e.printStackTrace();
		 }finally {
			 db.close(pstmt, con, rs);
		}
	    return flag;
	}

	@Override
	public boolean add(User Y) {
		boolean flag = false;
		try {
			String sql = "insert into user values(?,?,?,?)";
			con = db.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Y.getId());
			pstmt.setString(2, AppMD5Util.getMD5(Y.getPassword()));
			pstmt.setString(3, Y.getSex());
			pstmt.setString(4, Y.getName());
			
			if (pstmt.executeUpdate() > 0) //判断是否修改成功
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			 db.close(pstmt, con, rs);
		}
      
	return false;
}
 

	@Override
	public boolean IsExist(String ID) {
		int count = -1;
		try {
			String sql = "select count(1) from user where ID=?";
			con = db.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ID);
			
			rs = pstmt.executeQuery();   
			if(rs.next()) {
				count = rs.getInt(1) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			 db.close(pstmt, con, rs);
		}
	   if (count > 0)
		   return true;
	   return false;
	}
	@Override
	public User query(String ID)
	{   User user = null;
		try {
			String sql = "select * from user where ID=? ";
			con = db.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ID);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs != null&&rs.next())
			{
				  String ID1 = rs.getString("ID");
				  String sex = rs.getString("sex");
				  String name = rs.getString("name");
				  user = new User(ID1,sex,name);
			}  	    
		 } catch (SQLException e) {
			e.printStackTrace();
		 }  catch (Exception e) {
			e.printStackTrace();
		 }finally {
			 db.close(pstmt, con, rs);
		}
	    return user;
	}
		
	}


