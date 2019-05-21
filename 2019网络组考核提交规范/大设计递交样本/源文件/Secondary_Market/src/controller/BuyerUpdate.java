package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrderService;
import service.OrderServiceImpl;

/**
 * 在订单未被确认之前买家 可以修改用户订单 
 * 但只可以修改收货地址、电话号码和申请信息
 * @author 陈起廷
 * @version 2019年5月4日
 */
@WebServlet("/BuyerUpdate")
public class BuyerUpdate extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sendAddress = request.getParameter("sendAddress");
		String phone = request.getParameter("phone");
		String relateMessage = request.getParameter("relateMessage");
		String id = request.getParameter("id");
		
		OrderService service = new OrderServiceImpl();
		if (service.buyerUpdate(sendAddress, phone, relateMessage, id))
		{
			request.setAttribute("Message", "修改成功");
		}
		else 
		{
			request.setAttribute("Message", "已禁止修改");
		}
		//跳到展示个人订单的页面(先查后跳)
		request.getRequestDispatcher("QueryOrder").forward(request, response);
	}

}
