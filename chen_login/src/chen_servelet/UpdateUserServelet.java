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

/**
 * Servlet implementation class UpdateServelet
 */
@WebServlet("/UpdateServelet")
public class UpdateUserServelet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 request.setCharacterEncoding("utf-8");
	 String name = request.getParameter("name");
	 String ID = request.getParameter("ID");
	 String pwd = request.getParameter("pwd");
	 int  priority = Integer.parseInt(request.getParameter("priority")); 
	 response.setCharacterEncoding("utf-8");
	 
	 Boolean flag = false;
	 if(!name.matches("[\u4e00-\u9fa5]{2,5}" )) //验正修改的名字和密码是否争取
	  {  
		  request.setAttribute("Nerror","NAMEError");
	      flag = true;
	  }
	 if(!pwd.matches("^[a-zA-Z]{1,}\\d{8,}$"))
	   { 
		   request.setAttribute("Perror","PWDError");
		   flag = true;
	   }
	 if(true == flag)
	 {
		 request.getRequestDispatcher("index.jsp").forward(request, response);
	 }
	 else {
			 User a = new User(ID, pwd,priority,name);
			 UserDaoImpl sevice = new UserDaoImpl();
			 response.setCharacterEncoding("utf-8"); //设置响应编码
			 try {
				if(sevice.updateUser(a))
				 { 
					HttpSession hs = request.getSession(true);
					hs.setAttribute("User", sevice.Query(a.getID()));
					request.getRequestDispatcher("index.jsp").forward(request, response);
				 }
				else 
				{
					request.setAttribute("User", a);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
