/**
 * 
 */
package dao;

import java.util.List;

import model.Shopcart;
import model.ShowCart;

/**
 * @author 陈起廷
 * @version 2019年4月30日
 */
public interface ShopCartDao {
	/**
	 * 添加商品到购物车
	 * @param one
	 * @return
	 */
	public boolean addOne(Shopcart one);
	/**
	 * 在购物车删除一个商品信息
	 * @param one
	 * @return
	 */
	public boolean deleteOne(Shopcart one);
	/**
	 * 删除购物车所有商品
	 * @param id
	 * @return
	 */
	public boolean deleteAll(String id);
	/**
	 * 修改商品总量
	 * @param one
	 * @return
	 */
	public boolean updateSum(Shopcart one);
	/**
	 * 检验该商品是否已存在购物车当中
	 * @param goodId
	 * @return 如果存在则返回商品数目,不存在则返回0
	 */
	public int isExist(String goodId);
	/**
	 * @param id
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	List<ShowCart> queryAll(String id, int currentPage, int pageSize);
}
