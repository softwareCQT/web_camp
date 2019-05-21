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
 * 用户注册账号
 * @author 陈起廷
 * @version 2019年5月3日
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String id = request.getParameter("id");
	    String password = request.getParameter("password");
	    String sex = request.getParameter("sex");
	    String email = request.getParameter("email");
	    String name = request.getParameter("name");
	    String phone = request.getParameter("phone");
	  
	    //组装成user数据给后台，前台验证账号、密码、email、名字、电话号码格式是否正确
	    //初始化用户可售卖、金额为0，星级为0
	    User user = new User(id, password, sex, 
	    		              email, name, 
	    		              phone, "普通用户",
	    		              0,0,"是");
	    UserService service = new UserServiceImpl();
	    PrintWriter out = response.getWriter();
	    //注册成功则返回页面
	    if (service.register(user))
	    {
	    	  request.setAttribute("Message", "注册成功，请登录");
		      request.getRequestDispatcher("login.jsp").forward(request, response);
            
	    }
	    else 
	    {
	    	 request.setAttribute("Message", "该账号已存在,请重新注册");
		     request.getRequestDispatcher("register.jsp").forward(request, response);
	    }
  }
}
