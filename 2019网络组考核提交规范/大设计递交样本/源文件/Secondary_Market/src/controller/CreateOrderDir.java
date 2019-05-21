package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import service.OrderService;
import service.OrderServiceImpl;

/**
 * 在商品详情页直接创建订单
 * @author 陈起廷
 * @version 2019年5月4日
 */
@WebServlet("/CreateOrderDir")
public class CreateOrderDir extends HttpServlet {
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    /**
     * 在商品上直接点击购买商品即创建订单
     */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /*
		  * 因为购买商品页面是允许游客查看的
		  * 如果用户未登录，则提示并跳转到登录页面
		  */
		if (request.getSession().getAttribute("user") == null)
		{
			response.getWriter().print(
					"<script>alert('您未登录，请登录后再进行该操作');window.location.href='login.jsp'"
					);
		    return;
		}
		/*获取显示的表单数据  分别为 商品所有人 、 购买者、 发送的地址、
		    购买者的电话号码、购买请求的相关信息、订单商品的单价、订单商品数量、订单商品的id
		 */
		 String ownerId = request.getParameter("ownerId");
	     String buyerId = request.getParameter("buyerId");
	     String sendAddress = request.getParameter("sendAddress");
	     String phone = request.getParameter("phone");
	     String relateMessage = request.getParameter("relateMessage");
	     int goodSum = Integer.parseInt(request.getParameter("goodSum"));
	     String goodId = request.getParameter("goodId");
	     double money = Double.parseDouble(request.getParameter("money"));
	     //该订单价格由商品数量*订单商品的单价公式来获得
	     double price = money*goodSum;
	     //订单号按时间获取生成，初始的订单状态为“审核中”
	     String id = String.valueOf(System.currentTimeMillis());
	     String condition = "审核中";
	     Timestamp createTime = new Timestamp(System.currentTimeMillis());
	     //组合成order订单传给服务层
	     Order order = new  Order(id, createTime, condition, ownerId, buyerId, sendAddress,
	    					        null, phone, relateMessage, price, goodId, goodSum); 
	     OrderService service = new OrderServiceImpl();
	     //调用方法并判断是否成功添加
	     if (service.create(order))
	     {
	    	 request.setAttribute("Message", "创建订单成功");	
	    	 request.getRequestDispatcher("QueryOrder").forward(request, response);
	     }
	     else 
	     {
	    	 request.setAttribute("Message", "系统错误");
	    	 //购买失败
	    	 request.getRequestDispatcher("QueryAllGoods").forward(request, response);
	     }
	     
	     
	}

}
