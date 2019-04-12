<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/x.css" type="text/css">
<title>登录系统</title>
</head>
<body style="background:url(image/4cdfbd710d1728026e1952af1cc8dfef.jpg);background-size:cover; ">
  	   <% String error = (String)request.getAttribute("error");
		   if(error!=null){
			if(error.equals("addError")){
				out.print("注册成功！请登录");
			}else if(error.equals("loginerror")){
				out.print("账号或密码有误，请重新输入");}
			else 
				out.print("您已注册过，可直接登录");}%>
		<div id="login">
        <div id="form">
        <form action="LoginServelet" method="post" >
				账号：<input type="text" name="ID" id="ID"/><br/>
				密码：<input type="password" name="upwd" id="upwd"/><br/>
				<input type="submit" value="登录" /><br/>
			<a href = "register.jsp">注册账号</a></br>
		</form> 
		</div>     
        </div> 
</body>
</html>