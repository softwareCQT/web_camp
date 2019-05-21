package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrderService;
import service.OrderServiceImpl;

/**
 * 管理员修改和用户修改状态，拥有不一样的权利，具体在服务层判断
 * @author 陈起廷
 * @version 2019年5月4日
 */
@WebServlet("/ChangeCondition")
public class ChangeCondition extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取修改字段
		String condition = request.getParameter("condition");
		String id = request.getParameter("id");
		//调用服务方法
		OrderService service = new OrderServiceImpl();
		//对返回数据进行弹框显示
	    request.setAttribute("Message", service.changeCondition(condition, id));
	    request.getRequestDispatcher("QueryOrder").forward(request, response);		
	    
	}

}
