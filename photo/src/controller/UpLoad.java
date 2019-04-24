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
 * @author ����
 * @version 2019��4��20������3:30:46
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
				//�����ϴ������ļ��Ĵ�С  2M ServletFileUpload
				upload.setSizeMax(1024*2048);
		         /*�����ļ���СΪ2M
				  *ͨ��parseRequest����form�е����������ֶΣ������浽 items������
				  *spicture��ʱ�ͱ�������items�У�*/
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					String itemName = item.getFieldName();
					// �ж�ǰ̨�ֶ� ����ͨform���ֶΣ������ļ��ֶ�
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
						    	request.setAttribute("TypeError", "�ļ����ʹ���");
						    	request.getRequestDispatcher("show.jsp");
							    return ;
						    }
			           /*����ʹurl�������⻯*/
						fileName =  iD + "."+ ext; 
						String location = "D:\\eclipse-jee-oxygen-R-win32-x86_64\\photo\\WebContent\\image";
					     /*���÷����ѵ�ַ�������ݿ�*/
					    Picture P = new PictureImpl();
					    //�ѵ�ַ�������ݿ�
					    P.add(new photo("image/"+fileName, iD));
						File file = new File(location ,fileName);
						item.write(file);// �ϴ�
						request.setAttribute("success", "�ϴ��ɹ�");
						request.getRequestDispatcher("show.jsp").forward(request, response);
						}
				  }
				}
			}
		catch (FileUploadBase.SizeLimitExceededException e) {
			request.setAttribute("error", "�ļ��������ƴ�С");
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


