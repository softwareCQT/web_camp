<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QG二手商城</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
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
  <style>
	    body{
		margin: 0;
		padding: 0;
	    } 
		body{
			height: 1000px;
			font: 14px/1.5 "Helvetica Neue",Helvetica,Arial,"Microsoft Yahei","Hiragino Sans GB","Heiti SC","WenQuanYi Micro Hei",sans-serif;
		}
		a{  
		    color: #b0b0b0;
			font-size:12px;
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
		    font-family: sans-serif;
		    outline: none;
		    border: 1px solid #000;
		    text-align:center;
		}
		.label{
		    display: inline-block;
		    width: 143px;
		    text-align: right;
		    font-weight: bold;
		    font-size: large;
		}
		.right{
		 width:14%; float:left; height:100%;
		 }
		.left{width:14%; float:left; height:100%; }
		
		.container2{
			width: 70%;
			margin: 0 auto;
			display: flex;
			flex-wrap: wrap;
			align-content: center;
			justify-content: center;
			background:white;
			position: relative;
			padding-bottom: 100px;
		}
		div.img {
		    margin: 5px;
		    border: 1px solid #ccc;
		    float: left;
		    width: 180px;
		}
		
		div.img:hover {
		    border: 1px solid #777;
		}
		
		div.img img {
		    width: 100%;
		    height: auto;
		}
		
		div.desc {
		    padding: 15px;
		    text-align: center;
		}
		 ul {
		    list-style-type: none;
		    margin: 0;
		    padding: 0;
		    overflow: hidden;
		    background-color: #333;
		    position: fixed;
		    bottom: 0;
		    width: 100%;
		    display: flex;
		    justify-content: center;
		} 
		li a {
		    display: block;
		    color: white;
		    text-align: center;
		    font-size:font-size:20px;
		    padding: 14px 16px;
		    text-decoration: none;
		}
		li a:hover {
		    background-color: #111;
		}
		.formBox{
			position: absolute;
			bottom: 0;
			left: 50%;
			transform: translateX(-50%);
		}
      td{
			overflow: hidden;
			text-overflow:ellipsis;
			white-space: nowrap;
       }
</style>
</head>
<body style="background:url(img/jspImg/center.jpg) ;background-size:cover;text-align:center;" onload="Message()">
   <!-- 写一个隐藏表单，来提示后台传来的信息 -->
    <input type="hidden" id="Message" value="${requestScope.Message}">

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
    <div>
      <h1>管理员管理用户</h1>
    </div>
   <table width='100%' border='1px' cellspacing='0' cellpadding='0' class='mytable' style='table-layout: fixed' background="img/jspImg/1.jpeg">
		      <tr>
		                <td>头像</td>
						<td>用户</td>
						<td>姓名</td>
						<td>性别</td> 
						<td>邮箱</td>
						<td>电话号码</td>
						<td>身份</td>
						<td>评分星级</td>
						<td>是否具有销售权限</td>
						<td>修改</td>
			 </tr>
		  <c:forEach items="${requestScope.p.list}"  var="item">
		   <tr>
		                <td><img src="${item.photo}" style="height:50px" alt="无上传"></td>
						<td>${item.id}</td>
						<td>${item.name}</td>
					    <td>${item.sex}</td>
						<td>${item.email}</td>
						<td>${item.phone}</td>
						<td>${item.priority}</td>
						<td>${item.star}</td>
						<form action="ChangeUserSellOrNot" method="post">
						<td>
						  <select name="sell">
						    <option value="${item.sell}">${item.sell}</option>
						    <option value="是">是</option>
						    <option value="否">否</option>
						  </select>
						   <input type="hidden" name="id" value="${item.id}">
						   <input type="hidden" name="beforeSell" value="${item.sell}">
						 </td>
						<td>
						   <input type="submit" value="修改" class="formclass">
						</td>
					</form>	
	         </tr> 
		</c:forEach>
    </table>
   
       <!-- 获取page辅助类进行分析 -->
       <% Page p = (Page)request.getAttribute("p");
          if (p != null){
        %>
       <%
				if(p.getCurrentPage() ==  1){ //首页
		%>		
		       <ul>
		         <c:if test="${requestScope.p.currentPage != requestScope.p.totalPage}">
		           <li><a href="QueryAllUsers?currentPage=${requestScope.p.currentPage+1}">下一页</a></li>
       	           <li><a href="QueryAllUsers?currentPage=${requestScope.p.totalPage}">尾页</a></li>
       	         </c:if>
       	      </ul> 
		<% 
				}
				else if(p.getCurrentPage() == p.getTotalPage()){//尾页
		%>	   
		       <ul>
			         <li><a href="QueryAllUsers?currentPage=1">首页</a></li>
					 <li><a href="QueryAllUsers?currentPage=${requestScope.p.currentPage-1}">上一页</a></li>
                </ul>   
		<%		
			}
				else{//中间
		%>	  
		        <ul>
					 <li><a href="QueryAllUsers?currentPage=1">首页</a></li>
				     <li><a href="QueryAllUsers?currentPage=${requestScope.p.currentPage-1}">上一页</a></li>
				     <li><a href="QueryAllUsers?currentPage=${requestScope.p.currentPage+1}">下一页</a></li>
       	             <li><a href="QueryAllUsers?currentPage=${requestScope.p.totalPage}">尾页</a></li>
		       </ul> 
		<%			
				}
        } else {
		%>
		    <ul><li>
		      <a href="#">没有用户数据</a>
		    </li></ul>
       <% 
        }
       %>


</body>
</html>