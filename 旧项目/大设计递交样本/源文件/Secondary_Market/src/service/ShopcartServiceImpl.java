/**
 * 
 */
package service;

import dao.ShopCartDao;
import dao.ShopCartDaoImpl;
import model.Page;
import model.Shopcart;
import util.DbUtil;

/**
 * @author 陈起廷
 * @version 2019年5月4日
 */
public class ShopcartServiceImpl implements ShopcartService {
    /**
     * 调用dao层的方法
     */
	ShopCartDao method = new ShopCartDaoImpl();
	/**
	 * 如该商品已存在则增加数量
	 * 添加一个商品到购物车
	 */
	@Override
	public boolean addCart(Shopcart one) {
		//存在则flag不为 0且flag>0
		int flag = method.isExist(one.getCommodity_Id()); 
		if (flag == 0)
		{
			return method.addOne(one);
		}
		else
		{  //总数量 = 新增数量+原来的数量
			one.setSum(one.getSum() + flag);
			return method.updateSum(one);
		}
	}
    /**
     * 删除一个购物车商品
     */
	@Override
	public boolean deleteCart(String userId, String goodId) {
		if (userId == null || goodId == null)
		{   
			return false;
		}
		Shopcart one = new Shopcart(goodId, userId);
		return method.deleteOne(one);
	}
	/**
	 * 删除购物车的所有商品
	 */
	@Override
	public boolean deleteAll(String id) {
		if (id != null)
		{
			return method.deleteAll(id);
		}
		return false;
	}
    /**
     * 需要三个字段 数量、id、和用户id
     * 修改购物车的数字
     */
	@Override
	public boolean updateSum(Shopcart one) {
		return method.updateSum(one);
	}
	/**
	 * 个人id
	 * 查询并分页购物车
	 */
	@Override
	public Page queryAll(String id, Page p) {
		//查询商品还存在的购物车数量
		String sql = "select count(1) from Shopcart,Commodity where User_Id=? and "
				+ " Commodity_Id=Id";
		Object[] obj =new Object[] {id};
		//获取总数据量
		p.setTotalData(DbUtil.totalData(sql, obj));
		//获取该页的数据量
		p.setList(method.queryAll(id, p.getCurrentPage(), p.getPageSize()));
		return p;
	}

}
