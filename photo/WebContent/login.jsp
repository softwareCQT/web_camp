<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/x.css" type="text/css">
<title>登录系统</title>
</head>
<body >
		<div id="login">
         <div id="form">
           <h4 >${requestScope.error}</h4>
           <h4>${requestScope.addError}</h4>
           <h4>${requestScope.Error}</h4>
         <form action="Login" method="post" >
				账号：<input type="text" name="ID" id="ID"/><br/>
				密码：<input type="password" name="password" id="password"/><br/>
				<input type="submit" value="登录" /><br/>
		</form>
		 <a href = "register.jsp">注册账号</a><br> 
		 </div>     
       </div> 
</body>
</html>  