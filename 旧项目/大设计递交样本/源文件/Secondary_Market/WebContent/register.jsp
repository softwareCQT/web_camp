<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QG二手商城</title>
 <link rel="shortcut icon" href="img/jspImg/2_BigPic.ico" />
 <link rel="stylesheet" href="css/style.css" type="text/css"/>
 <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
function insertManager() {
    var password = document.getElementById("password").value;
    var repassword = document.getElementById("repassword").value;
         if(password!=repassword){
             alert("您输入的新密码与确认密码不一致");
             signupForm.repassword.focus();
             return false;
             }
          return true;
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
   <!-- 表单代码 -->
   <div>
      <h1>用户注册</h1>
   </div>
   <form action="Register" method="post"> 
		  <label class="label">账号: </label>  
		     <input type="text" name="id" class="formclass" placeholder="大于10且少于13位数字" 
		               autocomplete="off" pattern="[1-9][0-9]{8,12}" maxlength="12" required="required"><br/><br/>
		  <label class="label">密码:</label> 
		      <input type="password" name="password" id="password"class="formclass" placeholder="8到15位数字字母组合" 
		               autocomplete="off" pattern="^(?![^a-zA-Z]+$)(?!\D+$).{8,15}$"required="required"><br/><br/>
          <label class="label">再输入一次密码:</label>
              <input type="password" name="repassword" id="repassword"class="formclass" placeholder="8到15位数字字母组合" 
		               autocomplete="off" pattern="^(?![^a-zA-Z]+$)(?!\D+$).{8,15}$"required="required"><br/><br/>
	      <label class="label">姓名:</label> 
	          <input type="text" name="name" class="formclass" placeholder="输入姓名" 
		               autocomplete="off"pattern="^([a-zA-Z0-9\u4e00-\u9fa5\·]{1,10})$" required="required"><br/><br/>
	      <label class="label">邮箱:</label> 
	          <input type="email" name="email" class="formclass" placeholder="输入邮箱" 
		               autocomplete="off"required="required"><br/><br/>
	      <label class="label">性别:</label> 
		           <select name="sex" style="width:220px; text-align:center;text-align-last:center;border-radius:10px;outline:none">
					    <option value="男">男</option>  
						<option value="女">女</option>
		           </select><br/><br/>
	       <label class="label">电话号码:</label>
	             <input type="text" name="phone" class="formclass"placeholder="输入11位数的电话号码" 
		               autocomplete="off"pattern="^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$" required="required">
	       <br/><br/>
	    <button type="submit" class="formclass" onclick="return insertManager()">注册</button>
	    <a href="QueryAllGoods" class="formclass">返回</a>
   </form>
</body>
</html>