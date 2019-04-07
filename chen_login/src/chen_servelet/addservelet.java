package chen_servelet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chen.DaoImp.UserDaoImpl;
import chen.model.User;


@WebServlet("/addservelet")
public class addservelet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("utf-8");
	   String ID = request.getParameter("ID");
	   String name = request.getParameter("name");
	   int priority = 1;  //注册权限不由改变，只能是普通管理员;
	   String pwd = request.getParameter("upwd");
	   UserDaoImpl method = new UserDaoImpl();
	   Boolean flag = false;	 
	   try {
		if(method.userIDIsExist(ID))
					flag = false; 
		   else  
			  flag = method.addUser(new User(ID, pwd, priority, name));
	} catch (Exception e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	  
	   response.setCharacterEncoding("utf-8");
	   if(flag == true)
	   { 
		    request.setAttribute("error","addError");
		    request.getRequestDispatcher("login.jsp").forward(request, response);;
	   }
	   else 
	   {
		   response.sendRedirect("register.jsp");
	   }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

}
