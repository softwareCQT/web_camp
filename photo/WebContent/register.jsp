<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/x.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
     <div id="login">
        <div id="form">
        <h4 style="color: red;">
		  账号: 6-20位的数字<br> 密码: 开头为字母,结尾为数字,字母数字15位组合
	     </h4>
         <h4 style="color: red;"> ${requestScope.nameError} </h4>
         <h4 style="color: red;"> ${requestScope.passwordError} </h4>
         <h4 style="color: red;">${requestScope.IDError}</h4>
         <form action="Add" method="post" >
				账号：<input type="text" name="ID" /><br/>
				密码：<input type="password" name="password"/><br/>
				姓名：<input type="text" name="name"/><br/>
				性别：<select name="sex" >
				   <option value="男">男</option>
				   <option value="女">女</option>
				</select><br/>
		      <button style="color: #FF5722">确定</button>
		</form> 
		 <a href="login.jsp">返回</a>
	   </div>     
    </div>
</body>
</html>