package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Shopcart;
import service.ShopcartService;
import service.ShopcartServiceImpl;
/**
 * 添加商品到购物车
 * @author 陈起廷
 * @version 2019年5月7日
 */
@WebServlet("/AddCart")
public class AddCart extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		  * 因为购买添加购物车功能页面是允许游客点击的
		  * 如果用户未登录，则提示并跳转到登录页面
		  */
		if (request.getSession().getAttribute("user") == null)
		{  
			//提示消息去到login页面
			request.setAttribute("Message", "您未登录,请登录再进行此操作");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//获取商品id
		String goodId = request.getParameter("goodId");
		String userId = request.getParameter("userId");
		int sum = Integer.parseInt(request.getParameter("sum"));
		//加入购物车时间自动获取
		Timestamp date = new Timestamp(System.currentTimeMillis());
		//组装成购物车数据传到service层
		Shopcart one = new Shopcart(goodId, userId, sum, date);
		ShopcartService service = new ShopcartServiceImpl();
		//判断是否添加成功
		if (service.addCart(one))
		{
			request.setAttribute("Message", "添加成功");
		}
		else
		{
			request.setAttribute("Message", "系统错误");
		}	
		request.getRequestDispatcher("QueryAllGoods").forward(request, response);
	}

}
