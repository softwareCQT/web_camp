package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import service.UserServiceImpl;

/**
 * 通过账号查找其他用户
 * @author 陈起廷
 * @version 2019年5月3日
 */
@WebServlet("/SearchUser")
public class SearchUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String otherUserId = request.getParameter("otherUserId");
		
		UserService service = new UserServiceImpl();
		User user = service.queryUser(otherUserId); 
		if (user == null)
		{
			request.setAttribute("Message", "查无此人");
		}
		else
		{
			request.setAttribute("otherUser",user);
		}
		//调到其他用户的展示页
		request.getRequestDispatcher("showOtherUser.jsp");
	   }
}
