<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/x.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改权限</title>
</head>
<body style="background:url(image/4cdfbd710d1728026e1952af1cc8dfef.jpg);background-size:cover; "> 
     <%
        request.setCharacterEncoding("utf-8");
        String ID = request.getParameter("ID");
        String name = request.getParameter("Name");
        String priority = request.getParameter("Priority");
     %>
      <div id="login">
        <div id="form">
                     仅可修改权限，1为普通用户，2为管理员，3为超级管理员
           <form action="Beupdate">
		           账号:<input type="text" name="ID" value="<%=ID %>" readonly="readonly"><br/>
		           姓名:<input type="text" name="name" value="<%=name%>"  readonly="readonly"><br/> 
		           权限:<input type="text" name="priority"  value="<%=priority%>"><br/>
		           <input type="submit" value="修改">    
           </form>
           <a href="QueryAll">返回</a>
      </div>
    </div>
</body>
</html>