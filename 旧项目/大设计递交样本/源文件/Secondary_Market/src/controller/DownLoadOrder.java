package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.OrderService;
import service.OrderServiceImpl;

/**
 * 订单导出
 * @author 陈起廷
 * @version 2019年5月10日
 */
@WebServlet("/DownLoadOrder")
public class DownLoadOrder extends HttpServlet {
	
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

   @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//在前台用a标签实现
			//获取需要下载的文件名
			String fileName = request.getParameter("fileName");
			//用户id从session里拿
			User user = (User) request.getSession(true).getAttribute("user");
			String id = user.getId();
			
			String url = request.getServletContext().getRealPath("/DownLoadOrder");
			//下载文件：需要设置 消息头
			response.addHeader("content-Type","application/octet-stream" );
			//文件名包含后缀
			response.addHeader("content-Disposition","attachement;filename="+fileName);
			OrderService service = new OrderServiceImpl();
			String flag = service.outputFile(id, url);
			//判断是否为空，为空则系统错误
			if (flag != null)
			{  //Servlet通过文件的地址  将文件转为输入流 读到Servlet中
			   InputStream in = getServletContext().getResourceAsStream(flag);
			
				//通过输出流 将 刚才已经转为输入流的文件  输出给用户
				ServletOutputStream out = response.getOutputStream() ;
				byte[] bs = new byte[1024];
				int len=-1 ;
				while( (len=in.read(bs)) != -1)
				{
					out.write(bs,0,len);
				}
				//先开后关
				out.flush();
			    out.close();
			    in.close();
			}
			else 
			{
				request.setAttribute("Message", "系统错误");
				request.getRequestDispatcher("QueryOrder").forward(request, response);
			}
		}
	}

