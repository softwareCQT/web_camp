package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ShopcartService;
import service.ShopcartServiceImpl;

/**
 * 删除所有的购物车商品
 * @author 陈起廷
 * @version 2019年5月10日
 */
@WebServlet("/DeleteAllCart")
public class DeleteAllCart extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		ShopcartService service = new ShopcartServiceImpl();
		if (service.deleteAll(id))
		{
			request.setAttribute("Message","清空成功");
		}
		else
		{
			request.setAttribute("Message","系统出错");
		}
		request.getRequestDispatcher("QueryAllCart").forward(request, response);
	}

}
