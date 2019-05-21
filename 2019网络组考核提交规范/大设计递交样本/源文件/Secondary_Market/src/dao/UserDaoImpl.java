/**
 * 
 */
package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.AppMD5Util;
import util.DbUtil;

/**
 * @author 陈起廷
 * @version 2019年4月29日
 */
public class UserDaoImpl implements UserDao {
    /**
     * 登录成功则返回用户的权限使用户进入管理员主页或者普通用户主页
     * 登录失败则返回null
     */
	@Override
	public String login(String id, String password) {
		//让flag作为标志返回量
		String flag = null;
		ResultSet rs = null;
		final String pwd = "Password";
		//通过同样的加密算法加密之后和数据库里的密码匹配
		password = AppMD5Util.getMD5(password);
		//调用工具类
		Object[] obj = new Object[] {id};
		String sql = "select Password,Priority from User where Id=?";
		rs = DbUtil.query(sql,obj);
		if (rs != null)
		{
	        try {
	        	//若密码匹配则成功并返回权限值
				if (rs.next()&&password.equals(rs.getString(pwd)))
				{
				  flag = rs.getString("Priority");
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return null;
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}finally {
				DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
			}
		}
		return flag;
	}

	/**
	 * 注册功能的实现，使用javabean获取数据
	 */
	@Override
	public boolean register(User user) {
		//直接调用工具类的方法
		Object[] obj = new Object[] {
				user.getId(), AppMD5Util.getMD5(user.getPassword()),
				user.getSex(), user.getEmail(), user.getName(),
				user.getPhone(), user.getPriority(), user.getStar(),
				user.getMoney(), null, user.getSell()};
		String sql = "insert into User values(?,?,?,?,?,?,?,?,?,?,?)";
		return DbUtil.update(sql, obj);
	}
    /**
     * 通过查询有无结果知道该用户是否存在
     */
	@Override
	public boolean isExist(String id) {
		//调用工具类
		Object[] obj = new Object[] {id};
		ResultSet rs = DbUtil.query("select * from User where Id=?",obj);
		if (rs != null)
		{
	        try { 
	        	    //查询有结果则证明有此Id，反之亦然
					if (rs.next())
					{
					   return true;
					}
					else 
					{
					   return false;
					}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally {
				DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
			}
		}
		return false;
	}
    /**
     * 更新用户的信息,不包括用户密码、账号、权限和金钱、以及评分小星星
     */
	@Override
	public boolean update(User user) {
	    Object[] obj = new Object[] {
	         user.getSex(),user.getEmail(),
	         user.getName(),user.getPhone()
	    };
	    String sql = "update User set Sex=?,Email=?,Name=?,Phone=?";
		return DbUtil.update(sql, obj);
	}
	/**
	 * 修改用户密码
	 */
	@Override
	public boolean revison(String id, String password, String email) {
		//密码通过MD5加密,需要验证邮箱和账号
		Object[] obj = new Object[] {util.AppMD5Util.getMD5(password), id, email};
		String sql = "update User set Password=? where Id=? and email=?";
		
		return DbUtil.update(sql, obj);
	}

	/**
	 * 上传或者更换头像都是该操作
	 * 上传照片采取把照片的相对地址传到数据库去
	 */
	@Override
	public boolean uploadPhoto(String id, String photo) {
	    Object[] obj = new Object[] {photo,id};
	    String sql = "update User set photo=? where Id=?";
	    
		return DbUtil.update(sql, obj);
	}
    /**
     * 返回账户余额，方便服务层判断是否余额不足
     */
	@Override
	public double account(String id) {
		Object[] obj = new Object[] {id};
		String sql = "select Money from User where id=?";
		ResultSet rs = DbUtil.query(sql, obj);
		double result = 0;
		try {
			if (rs != null &&rs.next())
			{  
				result  = rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
		}
		return result;
	}
	/**
	 * 卖家账户+多钱，买家账户-少钱,充值+
	 */
	@Override
	public boolean payAndcharge(String id, double money) {
	    Object[] obj = new Object[] {money,id};
	    String sql = "update User set money=? where id=?";
		return DbUtil.update(sql, obj);
	}

    /**
     * 根据用户id查找用户
     * 也可用来返回登录用户的信息
     */
	@Override
	public User queryUser(String id) {
		Object[] obj = new Object[] {id};
		String sql = "select * from User where id=?";
		ResultSet rs = DbUtil.query(sql, obj);
		User user = null;
		try {
			if (rs != null&&rs.next())
			{
				user = new User(rs.getString("Id"),rs.getString("Sex"),
						rs.getString("Email"),rs.getString("name"),
						rs.getString("Phone"),rs.getString("Priority"),
						rs.getInt("Star"),rs.getString("Photo"),
						rs.getString("Sell")
						);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
		}
		return user;
	}
	/**
	 * 管理员功能，查询所有普通用户，并可以决定他们是否能销售商品
	 * 分页显示用户
	 * @return
	 */
	@Override
	public List<User> queryAllUser(int currentPage, int pageSize)
	{
		Object[] obj = new Object[] {"普通用户", (currentPage-1)*pageSize, pageSize};
		String sql = "select * from User where Priority=? limit ?,?";
		ResultSet rs = DbUtil.query(sql, obj);
		List<User> list = null;
		try {
			if (rs != null&&rs.next())
			{   //判断有数据则可以
				rs.beforeFirst();
				list = new ArrayList<User>();
				while (rs.next())
				{
				  list.add(new User(rs.getString("Id"),rs.getString("Sex"),
						rs.getString("Email"),rs.getString("name"),
						rs.getString("Phone"),rs.getString("Priority"),
						rs.getInt("Star"),rs.getString("Photo"),
						rs.getString("Sell")
						)
					);
			  }
		  }
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
		}
	return list;
	}

	/**
	 * 修改用户的销售权限
	 */
	@Override
	public boolean updateUserSell(String id,String sell) {
		Object[] obj = new Object[] {sell, id};
	    String sql = "update User set sell=? where id=?";
		return DbUtil.update(sql, obj);
	}
}
