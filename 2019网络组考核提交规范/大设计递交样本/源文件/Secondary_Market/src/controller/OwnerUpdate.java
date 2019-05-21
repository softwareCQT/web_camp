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
 * 在前段点击“发货”可以到达此servlet
 * 卖家修改订单状态为需要修改订单状态为“已发货”时且需要填上地址
 * @author 陈起廷
 * @version 2019年5月4日
 */
@WebServlet("/OwnerUpdate")
public class OwnerUpdate extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//修改为已发货时需要传一个发货地址
		String fromAddress = request.getParameter("fromAddress");
		String id = request.getParameter("id");
		
		OrderService service = new OrderServiceImpl();
		//先修改地址确认是否正确
		if (service.ownerUpdate(fromAddress, id))
		{
			//正确就开始修改订单状态字段
			service.changeCondition("已发货", id);
			request.setAttribute("Message", "发货成功");
		}
		else 
		{
			request.setAttribute("Message", "系统错误");
		}
		request.getRequestDispatcher("QueryOrder").forward(request, response);	
	}
}
