package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.userDao;
import Dao.userDaoImpl;

/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//��ȡ�û���ID������
		String ID = request.getParameter("ID");  
		String password = request.getParameter("password");
	
		userDao dao = new userDaoImpl();
		Boolean flag = dao.login(ID, password);
		
		if(flag == true)
		{ 
		   request.getSession(true).setAttribute("user",dao.query(ID));
		   //�ض��򵽸��˽���
		   response.sendRedirect("show.jsp");  
		}
		else 
		{
			request.setAttribute("error","�˺Ż���������������������");
			//��¼ʧ�ܣ����ص�¼����
			request.getRequestDispatcher("login.jsp").forward(request, response); 
		}
	}
}

