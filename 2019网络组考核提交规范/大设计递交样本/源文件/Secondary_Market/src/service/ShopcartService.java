/**
 * 
 */
package service;



import model.Page;
import model.Shopcart;

/**
 * @author 陈起廷
 * @version 2019年5月3日
 */
public interface ShopcartService {
	/**
	 * 添加商品进购物车
	 * @param one
	 * @return
	 */
	public boolean addCart(Shopcart one);
	/**
	 * 在购物车中删除商品
	 * @return
	 */
	public boolean deleteCart(String userId, String goodId);
	/**
	 * 删除此用户下所有的购物车商品
	 * @param id
	 * @return
	 */
	public boolean deleteAll(String id);
	 /**
	 * 修改购物车里的商品总量
	 * @param one
	 * @return
	 */
	public boolean updateSum(Shopcart one);
	/**
	 * 查询个人购物车所有信息
	 * @param id
	 * @return
	 */
	public Page queryAll(String id,Page p);
	 
}
