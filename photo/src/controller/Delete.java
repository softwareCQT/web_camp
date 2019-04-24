package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Picture;
import Dao.PictureImpl;
import model.photo;

/**
 * Servlet implementation class delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    String url = request.getParameter("url");
	    String ID = request.getParameter("ID");
	    //É¾³ýÍ¼Æ¬£¬»ñµÃurl
	    Picture P = new PictureImpl();
	    boolean flag =  P.delete(new photo("abcd","123"));
	    if (flag == true)
	    {
	       request.setAttribute("success","É¾³ý³É¹¦" );
	    }
	    else 
	    {
	       request.setAttribute("error", "É¾³ýÊ§°Ü");
	    }
	    request.getRequestDispatcher("show.jsp").forward(request, response);
	}
}
