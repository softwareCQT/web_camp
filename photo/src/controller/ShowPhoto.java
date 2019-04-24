package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Picture;
import Dao.PictureImpl;
import model.photo;

/**
 * Servlet implementation class ShowPhoto
 */
@WebServlet("/ShowPhoto")
public class ShowPhoto extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("utf-8");
	   String ID = request.getParameter("ID");
	   //调用dao层的方法
	   if (ID != null)
	   {
		   Picture P = new PictureImpl();
		   List<photo> p = P.queryphoto(ID);
		   System.out.println(p);
		   request.setAttribute("list", p);
		  
		   request.getRequestDispatcher("showPhoto.jsp").forward(request, response);
	   }
	   else 
		   response.sendRedirect("show.jsp");
	}

}
