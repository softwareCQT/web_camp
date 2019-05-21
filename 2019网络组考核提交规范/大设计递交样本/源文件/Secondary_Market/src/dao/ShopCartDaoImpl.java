/**
 * 
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Commodity;
import model.Shopcart;
import model.ShowCart;
import util.DbUtil;

/**
 * @author 陈起廷
 * @version 2019年4月30日
 */
public class ShopCartDaoImpl implements ShopCartDao {
   /**
    * 添加一条购物车商品
    */
	@Override
	public boolean addOne(Shopcart one) {
	    Object[] obj = new Object[] {
	    		one.getCommodity_Id(), one.getUser_Id(),
	    		one.getSum(),one.getDate()};
		String sql = "insert into Shopcart values(?,?,?,?)";
		return DbUtil.update(sql, obj);
	}
	/**
	 * 删除一条购物车信息
	 */
	@Override
	public boolean deleteOne(Shopcart one) {
		Object[] obj = new Object[] {
				one.getCommodity_Id(),one.getUser_Id()
		};
		String sql = "delete from Shopcart where Commodity_Id=? and User_Id=?";
		return DbUtil.update(sql, obj);
	}
    /**
     * 删除所有购物车信息
     */
	@Override
	public boolean deleteAll(String id) {
		Object[] obj = new Object[] {id};
		String sql = "delete from Shopcart where User_Id=?";
		return DbUtil.update(sql, obj);
	}

	@Override
	public boolean updateSum(Shopcart one) {
		Object[] obj = new Object[] {
				one.getSum(),one.getCommodity_Id(), one.getUser_Id() };
		String sql = "update Shopcart set Sum=? where Commodity_Id=? and User_Id=?";
		return DbUtil.update(sql, obj);
	}
	/**
	 * 查询购物车信息  组合两个javabean 购物车和商品以显示购物车
	 */
	@Override
	public List<ShowCart> queryAll(String id, int currentPage, int pageSize) {
		List<ShowCart> list = null;
		Object[] obj = new Object[] {id, (currentPage-1)*pageSize, pageSize};
	    String sql = "select s.*, t.* from commodity as t,shopcart as s where " + 
					"s.Commodity_Id=t.Id and User_Id=? order by s.date desc limit ?,?";
		ResultSet rs = DbUtil.query(sql, obj);
		try {
			if (rs != null && rs.next())
			{   
				rs.beforeFirst();
				//存在结果集则生成ArrayList，否则list仍然为空
				list = new ArrayList<ShowCart>();
				while (rs.next())
				{   
					//因为Sum有两项，采取列数方式取
					String Id = rs.getString("Id");
				    String userId = rs.getString("User_Id");
				    int sum = rs.getInt(3);
				    Timestamp date = rs.getTimestamp("Date");
				    String name = rs.getString("Name");
				    String message = rs.getString("Message");
				    double price = rs.getDouble("Price");
				    String photo = rs.getString("Photo");
				    int buySum = rs.getInt("Buy_Sum");
				    int sumGood = rs.getInt(8);
				    String type = rs.getString("Type");
				    String owner = rs.getString("Owner");
				    String check = rs.getString("Condition1");
				    //组合成ShowCart字段
				    ShowCart showcart = new ShowCart(
				    	new Shopcart(Id, userId, sum, date),
				    	new Commodity(Id, name, message,sumGood, price, photo, 
				    			buySum, type, owner, check));
				    list.add(showcart);
			     }
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
		}
	  
	  return list;
	}
	/**
	 * 确认该商品是否已经存在购物车，若已经存在，则返回其商品数量
	 */
	@Override
	public int isExist(String goodId)
	{
		Object[] obj = new Object[] {goodId};
		String sql = "select Sum from Shopcart where Commodity_Id=?";
		ResultSet rs = DbUtil.query(sql, obj);
		//用result来接收结果
		int result = 0;
		try {
			//若有数据则获取数据否则result仍为0，则此商品不在购物车中
			if (rs != null && rs.next())
			{   
				result = rs.getInt("Sum");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
		}
	  return result;
	}
}

