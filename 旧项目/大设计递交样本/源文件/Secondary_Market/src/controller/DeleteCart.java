package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Shopcart;
import service.ShopcartService;
import service.ShopcartServiceImpl;

/**
 * 删除购物车的一个商品
 * @author 陈起廷
 * @version 2019年5月10日
 */
@WebServlet("/DeleteCart")
public class DeleteCart extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String goodId = request.getParameter("goodId");
		   String userId = request.getParameter("userId");
		   //组装成shopcart数据
		   Shopcart one = new Shopcart(goodId, userId);
		   ShopcartService service = new ShopcartServiceImpl();
		   //验证是否删除成功
		   if (service.deleteCart(userId, goodId))
		   {
			   request.setAttribute("Message", "删除成功");
		   }
		   else 
		   {
			   request.setAttribute("Message", "系统出错");
		   }
		   //转发到查询所有购物车
		   request.getRequestDispatcher("QueryAllCart").forward(request, response);
	}

}
