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
 * 用户登录
 * @author 陈起廷
 * @version 2019年5月3日
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        //获取user的服务
        UserService service = new UserServiceImpl();
        //返回用户的权限
        String priority = service.login(id, password);
        PrintWriter out = response.getWriter();
        if (priority != null)
        {   //除了密码的用户信息都放到Session里，以此显示已经登录
        	request.getSession(true).setAttribute(
        			"user",service.queryUser(id));
        	out.write("{\"msg\":\"true\"}");
        }
        else
        {
        	out.write("{\"msg\":\"false\"}");
        }
        out.close();
	}

}
