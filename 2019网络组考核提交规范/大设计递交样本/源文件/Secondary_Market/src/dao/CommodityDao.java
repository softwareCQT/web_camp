/**
 * 
 */
package dao;

import java.util.List;

import model.Commodity;

/**
 * @author 陈起廷
 * @version 2019年5月1日
 */
public interface CommodityDao {
      /**
       * 添加商品信息、商品状态默认为 审核中
       * @param goods
       * @return
       */
	  public boolean addGood(Commodity goods);
	  /**
	   * 通过商品Id删除商品但是不影响曾经的订单
	   * @param goodsId
	   * @return
	   */
	  public boolean deleteGood(String goodsId);
	  /**
	   * 管理员操作：禁止商品发售、允许商品发售
	   * @param condition
	   * @param id
	   * @return
	   */
	  public boolean sellOrNot(String condition, String id);
	  /**
	   * 通过商品id查出数据从而展示商品
	   * @param id
	   * @return
	   */
	  public Commodity queryForShow(String id);
	  /**
	   * 以下操作均是查找允许商品发售的商品
	   */
	  /**
	   * 通过名字模糊查找商品并分页返回
	   * @param name
	   * @param currentPage
	   * @param pageSize
	   * @return
	   */
	  public List<Commodity> queryByName(String name, int currentPage, int pageSize);
	  /**
	   * 分页返回商品信息
	   * @param currentPage
	   * @param pageSize
	   * @return
	   */
	  public List<Commodity> queryAllGoods(int currentPage, int pageSize);
	  /**
	   * 通过账号查询某个人的发售的所有商品
	   * @param owner
	   * @param currentPage
	   * @param pageSize
	   * @return
	   */
	  public List<Commodity> queryByOwner(String owner, int currentPage, int pageSize);
	  /**
	   * 根据商品类型查询商品
	   * @param type
	   * @param currentPage
	   * @param pageSize
	   * @return
	   */
	  public List<Commodity> queryByType(String type, int currentPage, int pageSize);
	  /**
	   * 待审核的商品
	   * @param sql
	   * @param currentPage
	   * @param pageSize
	   * @return
	   */
	  public List<Commodity> queryByCheck(String sql, int currentPage, int pageSize);
	  /**
	   * 查询商品的审检情况
	   * @param id
	   * @return
	   */
	  public String queryCheck(String id);
	  /**
	   * 修改商品库存的数量和购买热度
	   * @param id
	   * @param num
	   * @param hot
	   * @return
	   */
	  public boolean sum(String id, int num, int hot);
	  /**
	   * 返回商品库存的数量
	   * @param id
	   * @return
	   */
	  public int number(String id);
	  /**
	   * 返回商品库存的购买热度
	   * @param id
	   * @return
	   */
	  public int buySum(String id);
		/**
		 * 当管理员修改用户的销售权限时也要修改他上传的商品的状态
		 * @param check
		 * @param id
		 * @return
		 */
	  boolean UpAndDown(String check, String id);
}
