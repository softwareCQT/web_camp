/**
 * 
 */
package dao;

import java.util.List;

import model.Order;

/**
 * @author 陈起廷
 * @version 2019年4月30日
 */
public interface OrderDao {
     /**
      * 生成订单
      * @param order
      * @return
      */
	 public boolean addOrder(Order order);
	 /**
	  * 查询订单并呈现所有订单给用户
	  * @param id
	  * @param currentPage
	  * @param pageSize
	  * @return
	  */
	 public List<Order> queryByPage(String id, int currentPage, int pageSize);
	 /**
	  * 买家修改订单信息
	  * @param sendAddress
	  * @param phone
	  * @param relateMessage
	  * @param id
	  * @return
	  */
	 public boolean buyerUpdate(String sendAddress, String phone, 
				String relateMessage, String id);
	 /**
	  * 卖家修改订单信息
	  * @param fromAddress
	  * @param id
	  * @return
	  */
	 public boolean ownerUpdate(String fromAddress,String id);
	 /**
	  * 买家修改订单状态,前台控制字段
	  * @param condition
	  * @param id
	  * @return
	  */
	 public boolean changeCondition(String condition,String id);
	 /**
	  * 返回订单的状态
	  * @param id
	  * @return
	  */
	 public String nowCondition(String id);
	 /**
	  * 返回一个订单的信息
	  * @param id
	  * @return
	  */
	 public Order query(String id);
	 /**
	  * 获取一个用户的订单文件
	  */
	 public String getFile(String id, String url);
}
