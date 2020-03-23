/**
 * 
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Commodity;
import util.DbUtil;

/**
 * @author 陈起廷
 * @version 2019年5月1日
 */
public class CommodityDaoImpl implements CommodityDao {	
	/**
     * 添加商品信息
     */
	@Override
	public boolean addGood(Commodity goods) {
		Object[] obj = new Object[] {
		    goods.getId(), goods.getName(), goods.getMessage(),
		    goods.getSum(), goods.getPrice(), goods.getPhoto(),
		    goods.getBuy_Sum(), goods.getType(), goods.getOwner(),  
		    goods.getCondition(),
		};
		String sql = "insert into Commodity values(?,?,?,?,?,?,?,?,?,?)";
		return DbUtil.update(sql, obj);
	}
	/**
	 * 通过商品Id删除商品
	 */
	@Override
	public boolean deleteGood(String goodsId) {
		Object[] obj = new Object[] {goodsId};
		String sql = "delete from Commodity where Id=?";
		return DbUtil.update(sql, obj);
	}
    /**
     * 管理员有权限决定该商品是否上架
     */
	@Override
	public boolean sellOrNot(String check, String id) {
        Object[] obj = new Object[] {check,id};
        String sql = "update Commodity set Condition1=? where Id=?";
		return DbUtil.update(sql, obj);
	}
	/**
	 * 管理员有权限决定该用户是否能上架商品，当修改为不能时，原来的所有商品都需要下架
	 * 当修改为能是，还存在于商品表中的所有商品状态变成'审核中'
	 */
	@Override
	public boolean UpAndDown(String check, String id) {
        Object[] obj = new Object[] {check,id};
        String sql = "update Commodity set Condition1=? where Owner=?";
		return DbUtil.update(sql, obj);
	}
    /**
     * 根据商品名字查询商品并分页展示
     */
	@Override
	 public List<Commodity> queryByName(String name,
			                     int currentPage,int pageSize) {
		String newname = "%"+name+"%";
		Object[] obj = {newname,(currentPage-1)*pageSize,pageSize};
		String sql = "select * from Commodity where Name like ? and"
				+ " Condition1='允许发售' order by Buy_Sum desc limit ?,?";
		List<Commodity> list = null;
		ResultSet rs = DbUtil.query(sql, obj);
		try {
			//判断rs是否有数据，如果有则滚回初始位置进行循环，没有则没有生成list，返回null
			if (rs != null && rs.next())
			{   
				rs.beforeFirst();
				list = new ArrayList<>();
				while (rs.next())
				{    //获取rs集合中的信息组合成商品list集合
					 String id = rs.getString(1);
				     String nname = rs.getString(2);
				     String message = rs.getString(3);
				     int sum = rs.getInt(4);
				     double price = rs.getDouble(5);
				     String photo = rs.getString(6);
				     int buySum = rs.getInt(7);
				     String type = rs.getString(8);
				     String owner = rs.getString(9);
				     String check = rs.getString(10);
				     list.add(new Commodity(id, nname, message,sum,
				    		 price, photo, buySum, type, owner, check));
				}
		} } catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
		}
	  return list;
	}

	/**
	 * 查询所有可售商品并且分页显示（按照热度）
	 */
	@Override
	public List<Commodity> queryAllGoods(int currentPage, int pageSize) {
		Object[] obj = {(currentPage-1)*pageSize,pageSize};
		String sql = "select * from Commodity where Condition1='允许发售'"
				+ " order by buy_sum desc limit ?,?";
		List<Commodity> list = null;
		ResultSet rs = DbUtil.query(sql, obj);
		
		try {
			//判断rs是否有数据，如果有则滚回初始位置进行循环，没有则没有生成list，返回null
			if (rs != null && rs.next())
			{   
				rs.beforeFirst();  
				list = new ArrayList<>();
				while (rs.next())
				{    //获取rs集合中的信息组合成商品list集合
					 String id = rs.getString(1);
				     String nname = rs.getString(2);
				     String message = rs.getString(3);
				     int sum = rs.getInt(4);
				     double price = rs.getDouble(5);
				     String photo = rs.getString(6);
				     int buySum = rs.getInt(7);
				     String type = rs.getString(8);
				     String owner = rs.getString(9);
				     String check = rs.getString(10);
				     list.add(new Commodity(id, nname, message,sum,
				    		 price, photo, buySum, type, owner, check));
				}
		} } catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
		}
	  return list;	
	}
	/**
	 * 按用户查询发售的的所有商品
	 */
	@Override
	public List<Commodity> queryByOwner(String owner, int currentPage, int pageSize) {
		Object[] obj = {owner,(currentPage-1)*pageSize,pageSize};
		String sql = "select * from Commodity where Owner=? and Condition1='允许发售' "
				+ "order by buy_sum desc limit ?,?";
		List<Commodity> list = null;
		ResultSet rs = DbUtil.query(sql, obj);
		try {
			//判断rs是否有数据，如果有则滚回初始位置进行循环，没有则没有生成list，返回null
			if (rs != null && rs.next())
			{   
				rs.beforeFirst();
				list = new ArrayList<>();
				while (rs.next())
				{    //获取rs集合中的信息组合成商品list集合
					 String id = rs.getString(1);
				     String name = rs.getString(2);
				     String message = rs.getString(3);
				     int sum = rs.getInt(4);
				     double price = rs.getDouble(5);
				     String photo = rs.getString(6);
				     int buySum = rs.getInt(7);
				     String type = rs.getString(8);
				     String oowner = rs.getString(9);
				     String condition1 = rs.getString(10);
				     list.add(new Commodity(id, name, message,sum,
				    		 price, photo, buySum, type, oowner, condition1));
				}
		} } catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
		}
	  return list;
	}
	/**
	 * 按类型查询所有商品
	 */
	@Override
	public List<Commodity> queryByType(String type, int currentPage, int pageSize) {
		Object[] obj = {type,(currentPage-1)*pageSize,pageSize};
		String sql = "select * from Commodity where Type=?  and Condition1='允许发售'"
				+ "order by buy_sum desc limit ?,?";
		List<Commodity> list = null;
		ResultSet rs = DbUtil.query(sql, obj);
		try {
			//判断rs是否有数据，如果有则滚回初始位置进行循环，没有则没有生成list，返回null
			if (rs != null && rs.next())
			{   
				rs.beforeFirst();
				list = new ArrayList<>();
				while (rs.next())
				{    //获取rs集合中的信息组合成商品list集合
					 String id = rs.getString(1);
				     String name = rs.getString(2);
				     String message = rs.getString(3);
				     int sum = rs.getInt(4);
				     double price = rs.getDouble(5);
				     String photo = rs.getString(6);
				     int buySum = rs.getInt(7);
				     String ttype = rs.getString(8);
				     String owner = rs.getString(9);
				     String condition = rs.getString(10);
				     list.add(new Commodity(id, name, message,sum,
				    		 price, photo, buySum, ttype, owner, condition));
				}
		} } catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
		}
	  return list;
	}
    /**
     * 管理员职能：查出所有需要审核和审核过的商品
     * 卖家可以查询自己的所有提交过且未撤回的的商品
     */
	@Override
	public List<Commodity> queryByCheck(String sql, int currentPage, int pageSize) {
		Object[] obj = {(currentPage-1)*pageSize,pageSize};
		List<Commodity> list = null;
		ResultSet rs = DbUtil.query(sql, obj);
		try {
			//判断rs是否有数据，如果有则滚回初始位置进行循环，没有则没有生成list，返回null
			if (rs != null && rs.next())
			{   
				rs.beforeFirst();
				list = new ArrayList<>();
				while (rs.next())
				{    //获取rs集合中的信息组合成商品list集合
					 String id = rs.getString(1);
				     String name = rs.getString(2);
				     String message = rs.getString(3);
				     int sum = rs.getInt(4);
				     double price = rs.getDouble(5);
				     String photo = rs.getString(6);
				     int buySum = rs.getInt(7);
				     String ttype = rs.getString(8);
				     String owner = rs.getString(9);
				     String condition = rs.getString(10);
				     list.add(new Commodity(id, name, message,sum,
				    		 price, photo, buySum, ttype, owner, condition));
				}
		} } catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
		}
	  return list;
	}
	/**
	 * 返回该商品的审查状况
	 */
	@Override
	public String queryCheck(String id) {
		Object[] obj = new Object[] {id};
		String sql = "select Condition1 from Commodity where Id=?";
		//调用工具类查出结果
		ResultSet rs = DbUtil.query(sql, obj);
		String result = null;
		try {
			if (rs != null&&rs.next())
			{  
				result = rs.getString(1);
			}	
		 } catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
		}
	  return result;
	}
    /**
     * 修改库存数量 
     */
	@Override
	public boolean sum(String id, int num, int hot) {
	    Object[] obj = new Object[] {num, hot, id};
	    String sql = "update Commodity set Sum=?,buy_Sum=? where Id=?";
		return DbUtil.update(sql, obj);
	}
   /**
    * 返回商品库存数量，在付款这个逻辑上进行购买
    */
	@Override
	public int number(String id) {
	    Object[] obj = new Object[] {id};
	    String sql = "select sum from Commodity where id=?";
		ResultSet rs = DbUtil.query(sql, obj);
		int result = -1;
		
			try {
				if (rs != null&&rs.next())
				{
					result = rs.getInt(1);
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
   * 返回该商品的购买量
   */
   @Override
   public int buySum(String id) {
	    Object[] obj = new Object[] {id};
	    String sql = "select Buy_Sum from Commodity where id=?";
		ResultSet rs = DbUtil.query(sql, obj);
		int result = -1;
			try {
				if (rs != null&&rs.next())
				{
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally {
				DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
			} 
		return result;
  }

	@Override
	public Commodity queryForShow(String id) {
		    Object[] obj = new Object[] {id};
		    String sql = "select * from Commodity where id=?";
		    Commodity good = null;
			ResultSet rs = DbUtil.query(sql, obj);
				try { 
					if (rs != null && rs.next())
					{	 
						id = rs.getString(1);
						//获取rs集合中的信息组合成商品
					     String name = rs.getString(2);
					     String message = rs.getString(3);
					     int sum = rs.getInt(4);
					     double price = rs.getDouble(5);
					     String photo = rs.getString(6);
					     int buySum = rs.getInt(7);
					     String type = rs.getString(8);
					     String owner = rs.getString(9);
					     String condition = rs.getString(10);
					     good = new Commodity(id, name, message,sum,
					    		 price, photo, buySum, type, owner, condition);  

					}
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					return null;
				}finally {
					DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
				} 
		return good;
	}
}

