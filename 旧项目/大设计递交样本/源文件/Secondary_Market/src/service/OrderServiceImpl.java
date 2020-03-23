/**
 * 
 */
package service;

import java.io.File;

import dao.CommodityDao;
import dao.CommodityDaoImpl;
import dao.OrderDao;
import dao.OrderDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import model.Order;
import model.Page;
import util.DbUtil;

/**
 * 分为卖家层和买家层两层服务
 * @author 陈起廷
 * @version 2019年5月2日
 */
public class OrderServiceImpl implements OrderService {
	/**
	 * 声明dao方法类
	 */
	OrderDao method = new OrderDaoImpl();
	/**
	 * 创建订单
	 */
	@Override
	public boolean create(Order order) {
		return method.addOrder(order);
	}
    /**
     * 根据页数返回订单
     */
	@Override
	public Page queryByPage(String id, Page page) {
		//调用工具类的查询总量方法
		String sql = "select count(1) from Order1 where Owner=? or Buyer=?";
		Object[] obj = new Object[] {id,id};
		page.setTotalData(DbUtil.totalData(sql, obj));
		page.setList(method.queryByPage(
				id, page.getCurrentPage(), page.getPageSize()
				));
		return page;
	}
   
	@Override
	public String outputFile(String id, String url) {
		return method.getFile(id, url);
	}
    /**
     * 买家在审核前和付款前可以修改地址
     */
	@Override
	public boolean buyerUpdate(String sendAddress, String phone, 
			String relateMessage, String id) {
		String nowCondition = method.nowCondition(id);
		if (sendAddress == null || phone == null || relateMessage == null || id == null)
		{
			return false;
		}
		if ("审核中".equals(nowCondition) || "待付款".equals(nowCondition))
		{
		  return method.buyerUpdate(sendAddress, phone, relateMessage, id);
		}
		else
		{
		  return false;
		}
	}
    /**
     * 卖家显示的订单字段(已发货、收货成功、审核中、待付款、交易失败）
     * 卖家显示的字段是(待发货、待付款、交易失败）
     */
	@Override
	public String changeCondition(String condition, String id) {
	    String nowCondition = method.nowCondition(id);
	    //查询该订单的变量进行组装，方便逻辑判断
	    Order order = method.query(id);
	    switch (nowCondition)
	    {
	      //商家的确认订单操作
	      case "审核中":{
	    	            if ("待付款".equals(condition))
	    	            {  
	    	              CommodityDao good = new CommodityDaoImpl();
	    	              //在此处先把商品的库存数量减少
	    	              if (good.number(order.getGoodId()) >= order.getGoodSum())
	    	              {   //库存量-订单商品数量 商品购买热度加订单商品数量
	    	            	  good.sum(order.getGoodId(),
	    	            			  good.number(order.getGoodId())-order.getGoodSum(),
			    	                  good.buySum(order.getGoodId())+order.getGoodSum()
	    	            		);
	    	            	   if(method.changeCondition(condition, id))
	    	            	   {
	    	            		   return "审核成功,待买家付款";
	    	            	   }
	    	              }
	    	             else
	    	             {
	    	            	 return "库存不足";
	    	             }
	    	            }
	    	           else if ("取消交易".equals(condition))
	    	           {   
	    	            	if ( method.changeCondition(condition, id))
	    	            	{
	    	            		return "已取消交易";
	    	            	}
	    	           }	        
	    	           return "无法进行该操作";
	      }
	     //用户的付款 、 取消交易操作
	     case "待付款" :{
	    	             //用户点击 付款 按钮 
	    	            if ("待发货".equals(condition))
	    	            {   //定义商品的dao方法进行判断,付款后可以对其进行支付
	                        UserDao user = new UserDaoImpl();
	                        CommodityService good = new CommodityServiceImpl();
	                        //三者关系 用户金额、订单
	                        if (user.account(order.getBuyer()) >= order.getPrice())
	                        {   //买家账户余额金钱-订单总金钱
	                        	boolean flag1 = user.payAndcharge(order.getBuyer(),
	                        		 user.account(order.getBuyer())-order.getPrice());
	                        	//卖家账户余额金钱+订单总金钱
	                        	boolean flag2 = user.payAndcharge(order.getOwner(), 
	                        		user.account(order.getOwner())+order.getPrice());
	                        	//若三者都为真，则调用修改字段方法且返回结果
	                        	if (flag1 && flag2)
	                        	{
	                        		if (method.changeCondition(condition, id))
	                        		{
	                        			return "付款成功,待卖家发货";
	                        		}
	                        	}	
	                        }
	                        else
                        	{
                        		return "余额不足";
                        	}
	    	            }
	                    else  if("取消交易".equals(condition))
	                    {
	                    	 CommodityDao good = new CommodityDaoImpl();
		    	              //在此处先把商品的库存数量增加
		    	             
		    	               //库存量+订单商品数量  //购买量+订单商品数量
		    	             good.sum(order.getGoodId(),
		    	            	     good.number(order.getGoodId())+order.getGoodSum(),
		    	            		 good.buySum(order.getGoodId())-order.getGoodSum());
		    	            if(method.changeCondition(condition, id))
		    	            {
		    	                 return "已取消交易";
		    	            }
		    	                 
	                    }
	                  return "无法进行该操作";    
	    	          }
	     case "待发货":{ 
	    	            if ("已发货".equals(condition))
	    	            {
	    	            	if (method.changeCondition(condition, id))
	    	            	{
	    	            		return "发货成功";
	    	            	}
	    	            }
	    	            return "无法进行该操作";
	                  }
	     case "已发货":{
	    	            if ("交易完成".equals(condition))
	    	            {  
	    	            	if (method.changeCondition(condition, id))
	    	            	{
	    	            		return "交易完成";
	    	            	}
	    	            }
	    	            return "无法进行该操作";
	                  }
	     default:return "无法进行该操作";
	      }
	}
	/**
	 * 卖家修改订单、在等待发货字段显示后进行判断
	 */
	@Override
	public boolean ownerUpdate(String fromAddress, String id)
	{
		String nowCondition = method.nowCondition(id);
		if ("待发货".equals(nowCondition))
		{
			return method.ownerUpdate(fromAddress, id);
		}
		return false;
	}
}
