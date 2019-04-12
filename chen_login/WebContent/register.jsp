<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/x.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
</head>
<body style="background:url(image/4cdfbd710d1728026e1952af1cc8dfef.jpg);background-size:cover; "> 
  <%
     String flag1 = (String)request.getAttribute("Ierror");
     if (flag1 != null)
    	out.print("账号格式错误！");
     String flag2 = (String)request.getAttribute("Perror");
     if (flag2 != null)
     		out.print("密码格式错误！");
     String flag3 = (String)request.getAttribute("Nerror");
    if (flag3 != null)
     	out.print("姓名格式错误！");
  %>
  <br/>
   <div id="login">
        <div id="form">
        <h5 style="color: red;">
		账号: 9-12位的数字<br> 密码：开头为字母,结尾为数字,字母数字组合
	    </h5>
  <form action="addservelet">
      姓名:<input type="text" name="name"><br/>
      账号:<input type="text" name="ID"><br/>
      密码:<input type="text" name="upwd"><br/>
     <input type="submit" value="立即注册"><br/>
     <a href = "login.jsp">返回登录</a>       
  </form>
  </div>     
     </div>
</body>
</html>