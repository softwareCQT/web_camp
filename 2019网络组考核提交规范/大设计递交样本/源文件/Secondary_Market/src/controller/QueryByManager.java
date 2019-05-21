package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Page;
import service.CommodityService;
import service.CommodityServiceImpl;

/**
 * 管理员查询商品
 * @author 陈起廷
 * @version 2019年5月13日
 */
@WebServlet("/QueryByManager")
public class QueryByManager extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String currentPage = request.getParameter("currentPage");
		
		 
		 Page p = null;
		 //默认的pageSize为10,构造一个page给服务端
		 final int pageSize = 10;
		 //若第一次查询,则页数为1,否则页数为所获取页数
		 if (currentPage == null)
		 {  
			int i = 1;
			p = new Page(i, pageSize);
		 }
		 else 
		 {
			 p = new Page(Integer.parseInt(currentPage), pageSize);
		 }
		
		 //调用服务层
		 CommodityService service = new CommodityServiceImpl();
		 //重新赋值给p
		 p = service.queryByManager(p);
		 //对p进行判断
		 if (p.getList() != null)
		 {
			 request.setAttribute("p", p);
			 request.getRequestDispatcher("managerGoods.jsp").forward(request, response);
		 }
		 else
		 {
			//否则跳到先查询所有商品再去到首页
			 request.setAttribute("Message", "没有商品需要管理");
			 request.getRequestDispatcher("QueryAllGoods").forward(request, response);
		 }
	}

}
