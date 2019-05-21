package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Commodity;
import service.CommodityService;
import service.CommodityServiceImpl;

/**
 * 商品详情页
 * @author 陈起廷
 * @version 2019年5月8日
 */
@WebServlet("/ShowGood")
public class ShowGood extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	          doPost(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//展示商品的详情页,得到商品的id,把该商品全部查出来
		String id = request.getParameter("id");
		//进行数据库操作和信息返回
		CommodityService service = new CommodityServiceImpl();
		Commodity good = service.queryForShow(id);
		request.setAttribute("good", good);
		request.getRequestDispatcher("showGood.jsp").forward(request, response);
	}

}
