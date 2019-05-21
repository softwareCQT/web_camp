package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.CommodityService;
import service.CommodityServiceImpl;

/**
 * 卖家删除商品
 * @author 陈起廷
 * @version 2019年5月3日
 */
@WebServlet("/DeleteGood")
public class DeleteGood extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //从个人商品展示页中获取商品id
		String id = request.getParameter("id");
		
	    CommodityService service = new CommodityServiceImpl();
	    if (service.deleteGood(id))
	    {
	    	request.setAttribute("Message", "删除商品成功");
	    }
	    else
	    {
	    	request.setAttribute("Message", "系统出错");
	    }
	    //成功删除后跳到查询个人的全部商品再跳到页面
	    request.getRequestDispatcher("QueryMyGoods").forward(request, response);
	  }
}
