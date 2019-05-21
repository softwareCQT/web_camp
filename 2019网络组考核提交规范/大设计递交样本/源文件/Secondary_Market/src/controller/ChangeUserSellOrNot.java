package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;

/**
 * 管理员改变用户是否可以提交商品
 * @author 陈起廷
 * @version 2019年5月9日
 */
@WebServlet("/ChangeUserSellOrNot")
public class ChangeUserSellOrNot extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取被修改用户的id、新修改的销售权限、之前的销售权限
		String id = request.getParameter("id");
		String sell = request.getParameter("sell");
		String beforeSell = request.getParameter("beforeSell");
		//调用方法
		UserService service = new UserServiceImpl();
		//修改是否成功，前台提示信息
		if (!sell.equals(beforeSell))
		{
			if (service.updateUserSell(id,sell))
			{
				request.setAttribute("Message", "修改成功");
			}
			else
			{
				request.setAttribute("Message", "系统出错");
			}
		}
		else 
		{
			request.setAttribute("Message", "初始权限一样，无须修改");
		}
		request.getRequestDispatcher("QueryAllUsers").forward(request, response);
	}

}
