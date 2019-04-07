<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="chen.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息页面</title>
</head>
<body>
        <%
           User user =(User)request.getAttribute("User");
       %> 
      <form action="UpdateServelet"> 
               账号:<input type="text" name="ID" value="<%=user.getID() %>"  readonly="readonly"/><br/>				
		 姓名:<input type="text" name="name" value="<%=user.getName() %>"/><br/>	
		 密码:<input type="text" name="pwd" value="<%=user.getName() %>"/><br/>	
		 权限:<input type="text" name="priority" value="<%=user.getPriority() %>" readonly="readonly"/><br/>	
		  <input type="submit" value="修改"/>
		<a href="QueryAll">查询所有人的信息</a> 
      </form>
                      
</body>
</html>