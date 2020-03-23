/**
 * 
 */
package service;

import java.util.List;

import model.Commodity;
import model.Page;

/**
 * @author 陈起廷
 * @version 2019年5月1日
 */
public interface CommodityService {
    /**
     * 添加商品
     * @param goods
     * @return
     */
	 public boolean addGood(Commodity goods);
	 /**
	  * 删除商品
	  * @param goodsId
	  * @return
	  */
	 public boolean deleteGood(String goodsId);
	 /**
	  * 管理员、卖家功能：是否禁止商品发售
	  * @param check
	  * @param id
	  * @return
	  */
	 public boolean sellOrNot(String check, String id);
	 /**
	  * 按名字模糊查找所有发售的商品
	  * @param name
	  * @param page
	  * @return
	  */
	 public Page queryByName(String name, Page page);
	 /**
	  * 显示所有发售的商品
	  * @param page
	  * @return
	  */
	 public Page queryAllGoods(Page page);
	 /**
	  * 通过账号显示该卖家目前放上网络的所有可售商品
	  * @param owner
	  * @param page
	  * @return
	  */
	 public Page queryByOwner(String owner, Page page);
	 /**
	  * 根据商品类型查询商品
	  * @param type
	  * @param page
	  * @return
	  */
	 public Page queryByType(String type, Page page);
	 /**
	  * 管理员查询待审核的商品
	  * @param page
	  * @return
	  */
	 public Page queryByManager(Page page);
	 /**
	  * 用户查询自己的所有商品(包括上架或者未上架的)
	  * @param userId
	  * @param page
	  * @return
	  */
	 public Page queryMyGoods(String userId, Page page);
	 /**
	  * 查询某个商品
	  * @param id
	  * @return
	  */
	 public Commodity queryForShow(String id);
}
