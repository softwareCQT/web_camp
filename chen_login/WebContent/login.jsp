<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录系统</title>
</head>
<body>
  	    <% String error = (String)request.getAttribute("error");
		   if(error!=null){
			if(error.equals("addError")){
				out.print("注册失败！");
			}else{
				out.print("注册成功！");}}%>
        <form action="LoginServelet" method="post" >
				账号：<input type="text" name="ID" id="ID"/><br/>
				密码：<input type="password" name="upwd" id="upwd"/><br/>
				<input type="submit" value="登录" /><br/>
			<a href = "register.jsp">注册账号</a>
		</form> 
</body>
</html>