package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Page;
import model.User;
import service.OrderService;
import service.OrderServiceImpl;

/**
 * 查询个人的订单
 * @author 陈起廷
 * @version 2019年5月7日
 */
@WebServlet("/QueryOrder")
public class QueryOrder extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     User user = (User)request.getSession(true).getAttribute("user");
	     String id = user.getId();
	     String currentPage = request.getParameter("currentPage");
	     Page p = null;
		 //默认的pageSize为10,构造一个page给服务端
		 final int pageSize = 10;
		 //若第一次查询,则页数为1,否则页数为所获取页数	
		 if (currentPage == null)
		 {  
			int i = 1;
			p = new Page(i, pageSize);
		 }
		 else 
		 {
			 p = new Page(Integer.parseInt(currentPage), pageSize);
		 }
		 //获取服务
		 OrderService service = new OrderServiceImpl();
		 //查询个人的所有订单
		p = service.queryByPage(id, p);
		if (p.getList() == null)
		{  
	       request.setAttribute("Message", "没有订单,去购买商品吧！");
	       request.getRequestDispatcher("QueryAllGoods").forward(request, response);
		}
		else
		{
	      request.setAttribute("p", p);
		  request.getRequestDispatcher("showOrder.jsp").forward(request, response);
	    }
	}
}
