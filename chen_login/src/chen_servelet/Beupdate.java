package chen_servelet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chen.DaoImp.UserDaoImpl;
import chen.model.User;


@WebServlet("/Beupdate")
public class Beupdate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User user = (User)request.getSession(true).getAttribute("User");// 获取登录用户的信息
		System.out.println(user.getID());
	    String PTID = (String) request.getParameter("ID");  //获取被修改人的信息
	    int Npriority =Integer.parseInt(request.getParameter("priority"));
	    
	    UserDaoImpl a = new UserDaoImpl();
	    if (PTID.equals(user.getID()))
        { 
	        request.setAttribute("equals","equals");
	        request.getRequestDispatcher("QueryAll").forward(request, response);
         }
	    else if (Npriority > user.getPriority()||Npriority <= a.userPriority(PTID))
	    {
	    	request.setAttribute("priority", "priority");
	    	request.getRequestDispatcher("QueryAll").forward(request, response);
	    }
	    else
	    {  
	    	a.Beupdate(Npriority,PTID);
	    	request.setAttribute("success", "success");
	    	request.getRequestDispatcher("QueryAll").forward(request, response);
	    }
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
