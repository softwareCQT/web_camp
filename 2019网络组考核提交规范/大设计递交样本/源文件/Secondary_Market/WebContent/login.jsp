<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>QG二手商城</title>
 <link rel="shortcut icon" href="img/jspImg/2_BigPic.ico" />
 <style type="text/css">
  body{
	margin: 0;
	padding: 0;
} 
body{
	height: 1200px;
	font: 14px/1.5 "Helvetica Neue",Helvetica,Arial,"Microsoft Yahei","Hiragino Sans GB","Heiti SC","WenQuanYi Micro Hei",sans-serif;
}
a{
	text-decroation: none;
}
.topbar{
	background-color: black;
	height: 40px;
}
.container{
	/*border: 1px solid black;*/
	margin: 0 auto;
	width: 1226px;
}
.container::before,.container::after{
	content: "";
	display: table;
}
.container::after{
	clear: both;
}
.topbar a{ 
	color: #b0b0b0;
	font-size:20px;
	text-decoration: none;
}
.topbar a:hover{
	color: white;
}
.topbar-nav{
	float: left;
	height: 40px;
	line-height: 40px;
	font-size: 0;
}
.topbar-nav span{
	font-size: 12px;
	color: #424242;
	font-family: sans-serif;
	margin: 0.5em;
}
.topbar-info,.topbar-cart{
	float: right;
}
.topbar-cart a{
	display:block;
	height: 40px;
	line-height: 40px;
	text-align: center;
	width: 120px;
	background: 424242;
}
.topbar-cart hover{
	background-color: #fff;
	color:  #ff0000;
}
.topbar-cart span{
	margin-left: -4px;
	font-size: 12px;
}
.topbar-info a{
	float: left;
	padding: 0 5px;
	line-height: 40px;
	height: 40px;
}
.topbar-info span{
	float: left;
	font-family: sans-serif;
	font-size: 12px;
	color: #424242;
	line-height: 40px;
	height: 40px;
}
 .hh {
  display: inline; 
 }
 .btn3_mouseout {
 BORDER-RIGHT: #2C59AA 1px solid; 
 PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; 
 PADDING-LEFT: 2px; FONT-SIZE: 12px; 
 FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#C3DAF5); 
 BORDER-LEFT: #2C59AA 1px solid; 
 CURSOR: hand; COLOR: black; 
 PADDING-TOP: 2px; 
 BORDER-BOTTOM: #2C59AA 1px solid;
}
.formclass{
	font-size: 20px;
    border-radius: 125px;
    color: black;
    font-family: "30";
    outline: none;
    border: none;
    text-align:center;
}
.label{
    display: inline-block;
    width: 143px;
    text-align: right;
    font-weight: bold;
    font-size: large;
}
</style>
 
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function login()
	{
			var $id = $("#id").val();
		    var $password = $("#password").val();
			$.getJSON(
			    "Login",
			    {"id":$id,"password":$password},
			    function (result)
			    {
			    	if (result.msg == "true")
			            {
			    		 window.location = "QueryAllGoods";
			            }
			    	else
			    		{
			    		  alert("账号或密码错误");
			    		}
			    }
		 );
	}
   function Message()
    {  
   	 var Message = $('#Message').val();
   	 if (Message != "")
       {
   		 alert(Message);
        }
    }
</script>
</head>
<body style="background:url(img/jspImg/center.jpg) ;background-size:cover;text-align:center;"onload="Message()">
  <input id="Message" value="${requestScope.Message}" type="hidden">
       <div class="topbar">
     	<div class="container">
        	<div class="topbar-nav">
	        	 <a href="QueryAllGoods">首页</a><span>|</span>
	         <c:if test="${not empty sessionScope.user.priority}">
	        	 <a href="QueryOrder">我的订单</a><span>|</span>
	        	 <a href="QueryMyGoods">我的商品</a><span>|</span>
	        	 <a href="user-Info.jsp">个人中心</a><span>|</span>
	        	 <c:if test="${sessionScope.user.priority == '管理员'}">
	        	   	   <a href="QueryByManager">管理商品</a><span>|</span>
	        	   	   <a href="QueryAllUsers">管理用户</a>
	        	 </c:if>
	        </c:if>
            </div>
            <c:if test="${not empty sessionScope.user.priority}">
	            <div class="topbar-cart">
	                   <a href="QueryAllCart">购物车</a>
	            </div>
	         </c:if>
        <div class="topbar-info"> 
                      <c:if test="${empty sessionScope.user}">
                            <a href="login.jsp">登录</a>
                      </c:if>
                      <c:if test="${not empty sessionScope.user}">
                           <a onclick="out()"> 注销</a>
                           <a>你好,${sessionScope.user.priority}</a>
                      </c:if>      
        </div> 
     </div>
    </div>
       <h1>用户登录</h1><br>
          <h2 class="hh">账号:</h2> 
             <input type="text"id="id"placeholder="请输入账号"autocomplete="off"class="formclass" 
                                     pattern="[1-9]{10,12}" required="required"/>
          <br/>
          <br/>
          <h2 class="hh">密码:</h2> 
             <input type="password"id="password"placeholder="请输入密码"autocomplete="off"class="formclass" 
                                   pattern="^(?![^a-zA-Z]+$)(?!\D+$).{8,15}$"required="required"/>
          <br/>
          <br/>
      <button type="button" class="formclass" onclick="login()">登录</button> <br/>
    <br/><br/>
   <a href="register.jsp" class="formclass">注册  </a>
   <a href="updatePassword.jsp" class="formclass">  忘记密码?</a>
</body>
</html>