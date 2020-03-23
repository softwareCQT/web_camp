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
 * 查询名字
 * @author 陈起廷
 * @version 2019年5月13日
 */
@WebServlet("/QueryByName")
public class QueryByName extends HttpServlet {
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String currentPage = request.getParameter("currentPage");
		 String name = request.getParameter("name");
		 
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
		 p = service.queryByName(name, p);
		 if (p.getList() == null)
		 {   
			 request.setAttribute("Message", "查询无果");
			 request.getRequestDispatcher("QueryAllGoods").forward(request, response);
		 }
		 else
		 {   
			 //记录Servlet的坐标，让用户可以点击下一页
			 p.setServletName("QueryByName?name="+name+"&");
			 request.setAttribute("p", p);
			 request.getRequestDispatcher("index.jsp").forward(request, response);
		 } 
	}

}
