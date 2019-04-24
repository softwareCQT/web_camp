package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DButil.DButil;
import model.photo;

public class PictureImpl implements Picture {
	private static DButil db = new DButil();   //通用数据库接口
	private static Connection con = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	@Override
	public boolean delete(photo P) {
	    boolean flag = false;//标志是否成功
		try {
			  String sql = "DELETE FROM imga_table where url=? and ID=?";//进行连接和分配
	          con = db.getCon();
	          pstmt = con.prepareStatement(sql);
	          pstmt.setString(1, P.getUrl());
	          pstmt.setString(2, P.getUser_id());
	          
	          if (pstmt.executeUpdate() > 0)
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
  public boolean add(photo P)
  {
	  boolean flag = false;//标志是否成功
		try {
			  String sql = "insert into imga_table values(?,?)";//进行连接和分配
	          con = db.getCon();
	          pstmt = con.prepareStatement(sql);
	          pstmt.setString(1, P.getUrl());
	          pstmt.setString(2, P.getUser_id());
	          
	          if (pstmt.executeUpdate() > 0)
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
	public List<photo> queryphoto(String ID) {  
		//读取照片的地址并返回一个集合;
		List<photo> list = new ArrayList<>();
		try {
			String sql = "select * from imga_table where user_id=?";
			con = db.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ID);
		    rs = pstmt.executeQuery();
			if (rs != null)
			{     //存在url则读取url
				  while (rs.next())  
				  {
					  String user_id = rs.getString("user_id");
					  String url = rs.getString("url");
					  list.add(new photo(url,user_id));
				  }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 db.close(pstmt, con, rs);
		}
	  return list;
	}
	
	@Override
	public int photoTotal(String ID) {    //照片总数，方便分页
		int count = -1 ;
		try {
			String sql = "select count(1) from imag_table where user_id=?";
			con = db.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ID);
			
			rs = pstmt.executeQuery();   
			if(rs.next()) 
				count = rs.getInt(1) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			 db.close(pstmt, con, rs);
		}
		return count;
  }

}


