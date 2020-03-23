package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Page;
import model.User;
import service.CommodityService;
import service.CommodityServiceImpl;

/**
 * 查询自己的商品
 * @author 陈起廷
 * @version 2019年5月6日
 */
@WebServlet("/QueryMyGoods")
public class QueryMyGoods extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     doPost(request, response);
	}
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//url里写el表达式把已经登录用户的id传过来
		 String currentPage = request.getParameter("currentPage");
		 User user = (User)request.getSession().getAttribute("user");
		 String id = user.getId();
		 
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
		 p = service.queryMyGoods(id, p);
		 //对p进行判断
		 if (p != null)
		 {
			 request.setAttribute("p", p);
		 }
		 else
		 {
			 request.setAttribute("Message", "没有商品,记得添加喔！");
		 }
		 //跳转到查询商品页
		 request.getRequestDispatcher("showMyGoods.jsp").forward(request, response);
	}
}


