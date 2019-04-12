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
import javax.servlet.http.HttpSession;

import chen.DaoImp.UserDaoImpl;
import chen.model.User;


@WebServlet("/QueryAll")
public class QueryAll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("utf-8");
	   HttpSession hs = request.getSession(true);
	   User user = (User)hs.getAttribute("User");
	   if(user == null)
		   System.out.println(true);
	   if(user.getPriority() == 1)
	   {   
		   request.setAttribute("NO", "NO");
		   request.getRequestDispatcher("index.jsp").forward(request, response);
	   }
	   else
	   {   
		   request.setAttribute("users", new UserDaoImpl().QueryAll());
		   request.getRequestDispatcher("show.jsp").forward(request, response);
	   }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
