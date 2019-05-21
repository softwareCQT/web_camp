package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.User;
import service.UserService;
import service.UserServiceImpl;

/**
 * 用户上传照片
 * @author 陈起廷
 * @version 2019年5月3日
 */
@WebServlet("/UpdatePhoto")
public class UpdatePhoto extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		User user = (User)request.getSession(true).getAttribute("user");
		String id = user.getId();
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			//判断前台的变淡是否有MultipartContent属性
			if (isMultipart) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				//设置上传文件名乱码问题
				upload.setHeaderEncoding("utf-8");
				// 通过parseRequest解析form中的表单字段，目前只有照片上传
				List<FileItem> items = upload.parseRequest(request);
				// 遍历items中的数据photo
				for (FileItem item:items)
				{
					String fileName = item.getName();
					//获取图片后缀
					String ext = fileName.substring(fileName.indexOf("."));
					//用用户名和后缀组成
					fileName = id+ext;
					//得到上传文件的目录
					String savePath = "D:\\eclipse-jee-oxygen-R-win32-x86_64\\Secondary_Market"
							+ "\\WebContent\\img\\userImg";
                    //把绝对路径写进数据库
					UserService service = new UserServiceImpl();
					if (service.updatePhoto(id, "img/userImg/"+fileName))
					{
						
						File file = new File(savePath, fileName);
				        //写进文件
					    item.write(file);
					    request.getSession(true).setAttribute("user", service.queryUser(id));
				    }
				}
				request.setAttribute("Message", "更新头像成功!");
				request.getRequestDispatcher("user-Info.jsp").forward(request, response);
			}
	     }catch (FileUploadException e) {
			e.printStackTrace();
		 }catch (Exception e) {
			e.printStackTrace();
		 }
	 
	}
}
