package chen_servelet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chen.Dao.UserDao;
import chen.DaoImp.UserDaoImpl;
import chen.model.User;

@WebServlet("/LoginServelet")
public class LoginServelet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String ID = request.getParameter("ID");
		String upwd = request.getParameter("upwd");
		//组装调用方法
		User user = new User(ID, upwd);
		UserDaoImpl dao = new UserDaoImpl();
		Boolean flag = dao.login(user);
		request.setCharacterEncoding("utf-8");
		if(flag == true)
		{ 
		  request.setAttribute("User", dao.Query(ID));
		  request.getRequestDispatcher("index.jsp");
		}
		else 
		  response.sendRedirect("login.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
