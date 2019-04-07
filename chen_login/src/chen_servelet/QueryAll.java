package chen_servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chen.DaoImp.UserDaoImpl;
import chen.model.User;

/**
 * Servlet implementation class Query
 */
@WebServlet("/Query")
public class QueryAll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("utf-8");
	   int flag;
	   User L = (User)request.getAttribute("user");
	   int result =new UserDaoImpl().userPriority(L.getID());
	   if(result == 1)
	   {
		   request.getRequestDispatcher("index.jsp").forward(request, response);;
	   }
	   else
	   {   
		   request.setAttribute("user", new UserDaoImpl().QueryAll());
		   request.getRequestDispatcher("show.jsp").forward(request, response);
	   }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
