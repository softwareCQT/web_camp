package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Dao.Picture;
import Dao.PictureImpl;
import model.photo;


/**
 * @author 爱你
 * @version 2019年4月20日下午3:30:46
 */
@WebServlet("/UpLoad")
public class UpLoad extends HttpServlet { 

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) { 
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				String iD = null;
				String url= null;
				//控制上传单个文件的大小  2M ServletFileUpload
				upload.setSizeMax(1024*2048);
		         /*设置文件大小为2M
				  *通过parseRequest解析form中的所有请求字段，并保存到 items集合中
				  *spicture此时就保存在了items中）*/
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					String itemName = item.getFieldName();
					// 判断前台字段 是普通form表单字段，还是文件字段
					if (item.isFormField()) {
						if ("ID".equals(itemName)) 
						{
							iD = item.getString("UTF-8");
				        } 
						else if("name".equals(itemName))
						{
							String name = item.getString("utf-8");
						}
						else 
						{
							String sex = item.getString("utf-8");
						}
					 }
					else
						{
							String fileName = item.getName();
							String ext = fileName.substring(fileName.indexOf(".")+1 ) ;
						    if(!("png".equals(ext) || "gif".equals(ext) ||"jpg".equals(ext)))
						    {   
						    	request.setAttribute("TypeError", "文件类型错误");
						    	request.getRequestDispatcher("show.jsp");
							    return ;
						    }
			           /*更新使url成立特殊化*/
						fileName =  iD + "."+ ext; 
						String location = "D:\\eclipse-jee-oxygen-R-win32-x86_64\\photo\\WebContent\\image";
					     /*调用方法把地址传进数据库*/
					    Picture P = new PictureImpl();
					    //把地址传进数据库
					    P.add(new photo("image/"+fileName, iD));
						File file = new File(location ,fileName);
						item.write(file);// 上传
						request.setAttribute("success", "上传成功");
						request.getRequestDispatcher("show.jsp").forward(request, response);
						}
				  }
				}
			}
		catch (FileUploadBase.SizeLimitExceededException e) {
			request.setAttribute("error", "文件超过限制大小");
			request.getRequestDispatcher("show.jsp").forward(request, response);
		}
		catch (FileUploadException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
				e.printStackTrace();
			}
    }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
			doGet(request, response);
		}
	
	}


