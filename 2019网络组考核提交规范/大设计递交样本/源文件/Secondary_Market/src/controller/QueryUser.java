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
 * 查找用户并看他的信息
 * @author 陈起廷
 * @version 2019年5月10日
 */
@WebServlet("/QueryUser")
public class QueryUser extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取其他用户的id
    	String id = request.getParameter("id");
        UserService service = new UserServiceImpl();
        
        User otherUser = (User)service.queryUser(id);
        //判断是否有此人
        if (otherUser == null)
        {
        	request.setAttribute("Message", "查无此人");
        	request.getRequestDispatcher("QueryAllGoods").forward(request, response);
        }
        else
        {
        	request.setAttribute("Message", "查询成功");
        	request.setAttribute("otherUser", otherUser);
        	request.getRequestDispatcher("showOtherUser.jsp").forward(request, response);
        }
        
	}

}
