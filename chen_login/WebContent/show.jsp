<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "chen.model.User" %>
<%@page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/x.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全部用户信息</title>
</head>
<body style="background:url(image/4cdfbd710d1728026e1952af1cc8dfef.jpg);background-size:cover; "> 
     <div id="login">
       <div id="form">
         <%
        String flag1 = (String)request.getAttribute("success");
        if (flag1 != null)
        	out.print("修改成功");
        String flag2 = (String)request.getAttribute("priority");
        if (flag2 != null)
        	out.print("权限不足,无法修改");
        String flag3 = (String)request.getAttribute("equals");
        if (flag3 != null)
            out.print("不能修改自己的权限");	
        %>
     <table border = "4px" style="background-color:green">
         <tr>
          <th>账号</th>
		  <th>姓名</th>
		  <th>权限</th>
		  <th>操作</th>
         </tr>
           <%  
				List<User> users = (List<User>)request.getAttribute("users");
                if(users != null)
				  for(User a:users){
			%>
			
						<td><%=a.getID()%></td>
						<td><%=a.getName()%></td>
					    <td>
							 <%=a.getPriority()==1?"普通用户":a.getPriority() == 2?"管理员":"超级管理员"%>
					    </td>   
					     <td> 
					       <a href = "update.jsp?ID=<%=a.getID() %>&Name=<%=a.getName() %>&Priority=<%=a.getPriority()%>">修改权限</a>
					     </td>
					</tr>
			<%
				}
			%>
      </table> 
       <a href = "index.jsp" >返回个人主页</a>
       </div>
       </div>
</body>
</html>