package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import service.UserServiceImpl;

/**
 * 修改用户信息
 * @author 陈起廷
 * @version 2019年5月8日
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession(true).getAttribute("user");
		String id = user.getId();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		
		User newUser = new User(name, email, phone, sex);
		UserService service = new UserServiceImpl();
		service.update(newUser);
		PrintWriter out = response.getWriter();
		//更新用户
		request.getSession(true).setAttribute("user", service.queryUser(id));
		request.setAttribute("Message", "修改成功!");
		request.getRequestDispatcher("user-Info.jsp").forward(request, response);	
	}

}
