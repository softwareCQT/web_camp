<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>QG二手商城</title>
  <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
  <script type="text/javascript">
    function change()
    {
    	window.location ="updatePassword.jsp"
    }
    function out()
    {  
	   	if(confirm("确认要退出该系统吗？我们舍不得你离开唉"))
	   	{
	   		window.location.href="Logout";
	   	}
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
			text-decoration: none;
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
  <link rel="shortcut icon" href="img/jspImg/2_BigPic.ico" /> 
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
     <form action="UpdateUser">
         <table border="1px" align="center"  height="70%" background="img/jspImg/1.jpeg">
	         <caption><h1>个人信息</h1></caption>
	          <tr><!--第1行开始-->
	          <th><h2>头像</h2></th>
	            <th>
	              <img alt="无上传" src="${sessionScope.user.photo}" width="300" height="200">
	            </th>
	            <th>
	         </tr><!--第1行结束-->
	         <tr><!--第2行开始-->
	         <th><h2>姓名</h2></th>
	            <th>
	               <input name="name" value="${sessionScope.user.name}" class="formclass" 
	                    required="required">
	            </th>
	         </tr><!--第2行结束-->
	         <tr>
	            <th><h2>账号</h2></th>
	            <th>
	               ${sessionScope.user.id}
	            </th>
	         </tr>
	         <tr>
	            <th><h2>性别</h2></th>
	            <th>
	               <select name="sex" class="formclass">
	                  <option value="${sessionScope.user.sex}">${sessionScope.user.sex}</option>
	                  <option value="男">男</option>
	                  <option value="女">女</option>
	               </select>
	            </th>
	         </tr>
	          <tr>
	            <th><h2>邮箱</h2></th>
	            <th>
	              <input type="email" name="email" value="${sessionScope.user.email}" class="formclass" placeholder="输入新的邮箱" 
		               autocomplete="off"required="required">
	            </th>
	         </tr>
	         <tr>
	            <th><h2>电话号码</h2></th>
	            <th>
	              <input type="text"value="${sessionScope.user.phone}" name="phone" class="formclass" placeholder="输入新的电话号码" 
		              pattern="^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$" autocomplete="off"required="required">
	            </th>
	         </tr>
	         <tr>
	            <th><h2>身份</h2></th>
	            <th>
	               ${sessionScope.user.priority}
	            </th>
	         </tr>
	         <tr>
	            <th><h2>是否具有购买权限</h2></th>
	            <th>
	               ${sessionScope.user.sell}
	            </th>
	         </tr>
	         <tr>
	           <th><h2>修改用户信息</h2></th>
	          <th>
	           <input type="submit" value="提交修改"  class="formclass">
	         </th>
	         </tr>
         </table>
       </form>
       
       <table border="1px" align="center"  height="10%" background="img/jspImg/1.jpeg">
        <tr>
	           <th><h2>修改用户密码</h2></th>
	          <th>
	           <button onclick="change()" class="formclass">点击修改</button>
	          </th>
	         </tr>
       </table>
       
        <form action="UpdatePhoto" method="post" enctype="multipart/form-data">
          <table border="1px" align="center" width="20%" height="10%" background="img/jspImg/1.jpeg">
             <caption><h1>上传新头像</h1></caption>
	        	  <tr> 
	        	  <th><input type="file" name="picture" class="formclass"accept=".gif,.jpg,.jpeg,.png" required="required"></th>
	              <th><input type="submit" value="上传照片" class="formclass"></th>
	              </tr>
          </table>
        </form>
</body>
</html>