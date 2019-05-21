package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Page;
import model.User;
import service.ShopcartService;
import service.ShopcartServiceImpl;

/**
 * 查询购物车所有
 * @author 陈起廷
 * @version 2019年5月10日
 */
@WebServlet("/QueryAllCart")
public class QueryAllCart extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页数和当前的用户id
		 String currentPage = request.getParameter("currentPage");
		 User user = (User) request.getSession(true).getAttribute("user");
		 String id = user.getId();
		 
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
		 //调用服务层
		 ShopcartService service = new ShopcartServiceImpl();
		 //重新赋值给p
		 p = service.queryAll(id, p);
	     request.setAttribute("p", p);
		 //跳转到查询购物车页
		 request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

}
