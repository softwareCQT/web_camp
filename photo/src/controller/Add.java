package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.userDao;
import Dao.userDaoImpl;
import model.User;

/**
 * Servlet implementation class add
 */
/**
 * @author ����
 * @version 2019��4��20������3:55:02
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	   doPost(request, response);
	}
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   request.setCharacterEncoding("utf-8");
		   /*��ȡע���û�����Ϣ*/
		   String ID = request.getParameter("ID");
		   String name = request.getParameter("name");
		   String sex = request.getParameter("sex"); 
		   String password = request.getParameter("password");
		   
		   /*��ȡ�˺�����ȸ�ʽ*/
		   String IdMatch = "[1-9][0-9]{8,12}";
		   String NameMatch = "[\\u4e00-\\u9fa5]{2,5}";
		   String PasswordMatch = "^[a-zA-Z]{1,}\\d{8,}$";
		   boolean flag = false;
		   
		   if(ID == null ||!ID.matches(IdMatch))   
		   {  
			  request.setAttribute("IDError","�˺Ÿ�ʽ����");
		      flag = true;
		   }
		   if(name == null || !name.matches(NameMatch ))
		   {  
			  request.setAttribute("nameError","������ʽ����");
		      flag = true;
		   }
		   if(password == null || !password.matches(PasswordMatch))
		   { 
			   request.setAttribute("passwordError","�����ʽ����");
			   flag = true;
		   }
		   if(flag == true) 
		   {
			 request.getRequestDispatcher("register.jsp").forward(request, response);
             return;
           }
		   userDao method = new userDaoImpl();
		   Boolean flag1 = false;	 
			try {
					if (method.IsExist(ID))
					{
						flag1 = false; 
					}
					else 
					{  
						flag1 = method.add(new  User(ID,password,sex,name));
					}
			   }
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			response.setCharacterEncoding("utf-8");
			   if (flag1 == true)
			   {
				   request.setAttribute("addError","ע��ɹ�");
			   }
			   else 
			   {
				    request.setAttribute("Error","ע��ʧ�ܣ����˺��Ѿ�����");
			   }
			   request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		  
	}


