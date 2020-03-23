package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;

/**
 * 用户修改密码
 * @author 陈起廷
 * @version 2019年5月3日
 */
@WebServlet("/UpdatePassword")
public class UpdatePassword extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//在设计页面的时候要获取两次密码，相同时才能提交，但此此处不做验证，前台可以通过js的onclick
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		UserService service = new UserServiceImpl();
		//修改成功则重新登录
		PrintWriter out = response.getWriter();
		if (service.revison(id, password,email))
		{   
			request.getSession(true).removeAttribute("user");
			request.setAttribute("Message", "修改成功,请重新登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		else 
		{   
			request.setAttribute("Message", "邮箱错误或账号错误!");
			request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
		}
	}

}
