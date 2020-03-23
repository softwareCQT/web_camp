package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommodityService;
import service.CommodityServiceImpl;

/**
 * 改变商品是否可售的状态
 * @author 陈起廷
 * @version 2019年5月3日
 */
@WebServlet("/SellOrNot")
public class SellOrNot extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 doPost(request, response);
	}
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取前台修改商品状态的字段 (select里的change)和商品的id
    	String conditon = request.getParameter("change");
    	String id = request.getParameter("id");
    	
    	CommodityService service = new CommodityServiceImpl();
    	if (service.sellOrNot(conditon, id))
    	{ 
    	   request.setAttribute("Message", "修改成功");
    	}
    	else 
    	{
    		 request.setAttribute("Message", "系统错误");  
    	}
    	request.getRequestDispatcher("QueryByManager").forward(request, response);
	}

}
