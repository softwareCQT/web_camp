package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
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

import model.Commodity;
import model.User;
import service.CommodityService;
import service.CommodityServiceImpl;


/**
 * 用户添加商品
 * @author 陈起廷
 * @version 2019年5月3日
 */
@WebServlet("/AddGood")
public class AddGood extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
 	}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     //刚添加的商品检查状态为 审核中,在组合数据的时候直接添加便可
	     try {
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if (isMultipart) 
				{ 
					DiskFileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);
					
				   //定义表单字段
				    String name = null;
				    String message = null;
				    int sum = -1;
				    double price = -1.0;
				
				    String type = null;
				    //用户由session获得
				    User user = (User)request.getSession(true).getAttribute("user");
				    
				    String owner = user.getId();
				   
				    //id为自动生成 生成规则(当前的具体时间)
				    String id = String.valueOf(System.currentTimeMillis());
				    //此照片为地址,下面进行表单文件图片获取获取photo的地址
				    String photo = null;
					/*通过parseRequest解析form中的所有请求字段，并保存到 items集合中
					 *所有字段此时就保存在了items中）
					 */
					List<FileItem> items = upload.parseRequest(request);
					Iterator<FileItem> iter = items.iterator();
					while (iter.hasNext()) 
					{
						FileItem item = iter.next();
						String itemName = item.getFieldName();
						// 判断前台字段 是普通form表单字段，还是文件字段
						if (item.isFormField()) 
						{   //对应获取
						   switch (itemName)
						   {
						     case "name":name = item.getString("utf-8");break;
						     case "message":message = item.getString("utf-8");break;
						     case "sum":sum = Integer.parseInt(item.getString("utf-8"));break;
						     case "price":price = Double.parseDouble(item.getString("utf-8"));break;
						     case "type":type = item.getString("utf-8");break;
						     default:return;
						  }
						}
						else 
							{   
								 String fileName = item.getName();
								 String ext = fileName.substring(fileName.indexOf(".")+1 ) ;
					             //更新使fileName成立特殊化,传给后台的是相对地址父目录+fileName
								 fileName = id +"."+ext;
								 photo = "img/goodsImg/" + fileName;
								 //找到存储路径
								 String location = "D:\\eclipse-jee-oxygen-R-win32-x86_64"
										+ "\\Secondary_Market\\WebContent\\img\\goodsImg";
								 File file = new File(location ,fileName);
								 item.write(file);
						  }
					}
					//把整个商品信息传进数据库
				     CommodityService service = new CommodityServiceImpl();
				     /*调用方法把地址传进数据库,check状态是系统内定,购买量初始化是0*/
				     Commodity good = new Commodity(
				    		  id, name, message, sum, price, photo,
						     0, type, owner, "审核中");	
				     //判断是否成功
				     if (service.addGood(good))
				     {    
						  request.setAttribute("Message", "添加成功");
					 }
				     else 
				     {
				    	 request.setAttribute("Message", "系统错误");	 
				     } 
				     request.getRequestDispatcher("QueryMyGoods").forward(request, response);
				} 
	        }catch (FileUploadException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}


