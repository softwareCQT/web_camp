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
	   boolean flag = false;
	   if(!ID.matches("[1-9][0-9]{8,12}"))    //对密码、ID、姓名简单验证
	   {  
		  request.setAttribute("Ierror","IDError");
	      flag = true;
	   }
	   if(!name.matches("[\u4e00-\u9fa5]{2,5}" ))
	   {  
		  request.setAttribute("Nerror","NAMEError");
	      flag = true;
	   }
	   if(!pwd.matches("^[a-zA-Z]{1,}\\d{8,}$"))
	   { 
		   request.setAttribute("Perror","PWDError");
		   flag = true;
	   }
	   if(flag == true)  //为真则转发回去
		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   else     //为假就是验证成功
	   {   
		   UserDaoImpl method = new UserDaoImpl();
		   Boolean flag1 = false;	 
		   try {
			if(method.userIDIsExist(ID))
				flag1 = false; 
			else  
				flag1 = method.addUser(new User(ID, pwd, priority, name));
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		   response.setCharacterEncoding("utf-8");
		   if(flag1 == true)
			    request.setAttribute("error","addError");
		   else
			    request.setAttribute("error","Error");
		   request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	  }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
		}

}
