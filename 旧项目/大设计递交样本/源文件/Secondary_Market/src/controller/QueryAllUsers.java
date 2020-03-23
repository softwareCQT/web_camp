package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Page;
import service.UserService;
import service.UserServiceImpl;

/**
 * 管理员查询所有用户
 * @author 陈起廷
 * @version 2019年5月9日
 */
@WebServlet("/QueryAllUsers")
public class QueryAllUsers extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  //只需要从页面中获取页数数据
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
		
		 //调用服务层
		 UserService service = new UserServiceImpl();
		 //重新赋值给p
		 p = service.queryAllUsers(p);
		 //对p进行判断
		 if (p.getList() != null)
		 {
			 request.setAttribute("p", p);
			 //跳转到管理员管理商品的页面
		 }
		 request.getRequestDispatcher("managerUser.jsp").forward(request, response);
		
	}

}
