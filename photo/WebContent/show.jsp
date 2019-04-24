<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/x.css" type="text/css">
<link rel="stylesheet" href="css/login.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息页面</title>
</head>
<body > 
   <div id="login">
       <div id="form">	
        <h4 color="red">${requestScope.success}</h4>	
         <h4 color="red">${requestScope.error}</h4>  
	    <form action = "UpLoad" method="post" enctype = "multipart/form-data"> 
	          账号:<input type="test" name="ID"  value="${sessionScope.user.id}" readonly><br/>
	          姓名:<input type="test" name="name"  value="${sessionScope.user.name}" readonly><br/>
	          性别:<input type="test" name="sex" value="${sessionScope.user.sex }" readonly><br/>
		   上传照片:<input type="file" name="spicture"/><br/>
		    <button>上传新照片</button> <br/>
         </form>
         <a href = "ShowPhoto?ID=${sessionScope.user.id}"><h3 color="red">查看个人相册</h3></a>
       </div>
     </div>   
</body>
</html>
 
 
 