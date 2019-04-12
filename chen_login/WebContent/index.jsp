<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="chen.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/x.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息页面</title>
</head>
<body style="background:url(image/4cdfbd710d1728026e1952af1cc8dfef.jpg);background-size:cover; ">
        <%
           String a = (String)request.getAttribute("NO");
           if(a != null)
           {
        	   if(a.equals("NO") == true)
        		  out.print("查询失败，你没有权限");  
           }%>
         <% String flag1 =(String)request.getAttribute("Nerror");
           if(flag1 != null)
        	   out.print("姓名修改失败，姓名格式不对,应为2-5个中文字符");%>
         <% String flag2 =(String)request.getAttribute("Perror");
           if(flag2 != null)
      	     out.print("密码修改失败，密码格式不对,开头应为因为字母,密码应包含至少8个数字,只允许字母和数字组合");
             HttpSession hs = request.getSession(true);
             User user =(User)hs.getAttribute("User"); %> 
      <div id="login">
        <div id="form">
      <form action="UpdateServelet"> 
               账号:<input type="text" name="ID" value="<%=user.getID()%>"  readonly="readonly"/>（不可修改）<br/>				
	        姓名:<input type="text" name="name" value="<%=user.getName()%>"/><br/>	
	        密码:<input type="text" name="pwd" value="<%=user.getPWD() %>"/><br/>	
	 	 权限:<input type="text" name="priority" value="<%=user.getPriority()==1?"普通用户":user.getPriority() == 2?"管理员":"超级管理员" %>" readonly="readonly"/>（不可修改）<br/>	
		           <input type="submit" value="修改"/> </br>
		<a href="QueryAll?priority=<%=user.getPriority()%>&name=<%=user.getName()%>&pwd=<%=user.getPWD() %>
		                            &ID=<%=user.getID()%>">查询所有人的信息(权限为普通用户不可查)</a> </br>
		 <a href="login.jsp">退出登录</a>
      </form> 
      </div>     
      </div>     
</body>
</html>