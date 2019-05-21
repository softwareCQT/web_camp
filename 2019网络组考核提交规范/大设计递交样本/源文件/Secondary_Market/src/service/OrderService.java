/**
 * 
 */
package service;

import java.io.File;

import model.Order;
import model.Page;

/**
 * @author 陈起廷
 * @version 2019年5月2日
 */
public interface OrderService {
    /**
     * 生成订单
     * @param order
     * @return
     */
	public boolean create(Order order);
	/**
	 * 查询订单并呈现所有订单
	 * @param id
	 * @param page
	 * @return
	 */
	public Page queryByPage(String id,Page page);
	/**
	 * 查询所有订单并且转换为文件导出到本机
	 * @param id
	 * @return
	 */
	public String outputFile(String id, String url);
	/**
	 * 买家修改订单
	 * @param sendAddress
	 * @param phone
	 * @param relateMessage
	 * @param id
	 * @return
	 */
	public boolean buyerUpdate(String sendAddress, String phone, 
				String relateMessage, String id);
	/**
	 * 卖家修改订单的发货地址 
	 * @param fromAddress
	 * @param id
	 * @return
	 */
	public boolean ownerUpdate(String fromAddress, String id);
	/**
	 * 卖家修改订单状态,前台控制字段
	 * @param condition
	 * @param id
	 * @return
	 */
	public String changeCondition(String condition,String id);
}