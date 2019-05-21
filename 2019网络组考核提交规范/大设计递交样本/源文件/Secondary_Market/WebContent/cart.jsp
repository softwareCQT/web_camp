<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="img/jspImg/2_BigPic.ico" />
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
   <title>QG二手商城</title>
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
		    text-align: center;
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
		div.desc a{
		    display: block;
		    color: white;
		    text-align: center;
		    font-size:font-size:20px;
		    padding: 14px 16px;
		    text-decoration: none;
		}
		div.desc a:hover{
		 background-color: #111;
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
		    padding: 0px;
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
       
	   p {
	     border:1px solid #333;
	     width:100%;
	     }
     .formBox{
			position: absolute;
			bottom: 10;
			left: 50%;
			transform: translateX(-50%);
		}
		.form-display{
			padding-top: 100px;
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			background-color: rgba(0, 0, 0, 0.9);
			color: #ccc;
			width: 500px;
			height: 400px;
			display: none;
		}
		.form-display .inputBox .label{
			text-align: left;
			display: inline;
		}
		.fileBtn{
			width: 70px;
		}
		.form-display button{
			position: absolute;
			right: 2px;
			bottom: 2px;
		}
   </style>
</head>
<body style="background:url(img/jspImg/center.jpg) ;background-size:cover;text-align:center;" onload="Message()">
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
     <!-- 标题 -->
     <div>
        <h1>购物车</h1>
     </div>
      <div class="container2">
      <c:forEach items="${requestScope.p.list}"  var="item" >
	      <div class="img">
					    <a target="_blank" href="ShowGood?id=${item.good.id}">
					      <img src="${item.good.photo}" title="${item.good.message}" width="200px" >
					    </a>
					    <div class="desc">
					       <p>商品：${item.good.name}</p>
					       <p>价格：${item.good.price}￥</p>
					       <p>销量：${item.good.buy_Sum}</p>
					       <p>商品号：${item.good.id}</p>
					       <p>库存：${item.good.sum}</p>
					       <p>商家：${item.good.owner}</p>
					       <p>加入时间: ${item.cart.date}</p>
					       <!-- 在购物车购买提交的表单 -->
					       <c:if test="${item.good.condition == '允许发售'}">
					         <c:if test="${item.good.sum <= 0}">
					                       <a>商品库存不足</a>
					         </c:if>
					         <c:if test="${item.good.sum > 0}">
					               <button id="btn1" class="formclass" onclick="btn1()">购买</button>
					         </c:if>
					       </c:if>
					       <c:if test="${item.good.condition == '禁止发售'}">
					          <a href="#">该商品已下架</a> 
					       </c:if>
					            <div class="form-display">
     	                          <button id="btn2" onclick="btn2()">取消</button>
     	                             <form action="BuyByCart" method="post">
								        <input type="hidden" name="ownerId" value="${item.good.owner}">
								        <input type="hidden" name="buyerId" value="${sessionScope.user.id}">
							            <input type="hidden" name="money" value="${item.good.price}">
							            <input type="hidden" name="goodId" value="${item.good.id}">
							            <label class="lable">购买数量:</label>
							                    <input type="number" name="goodSum" class="formclass" value="${item.cart.sum}" 
							                          autocomplete="off" max="${item.good.sum}" min="1"><br>
							            <label class="lable">收货地址:</label>
							                    <input type="text" name="sendAddress" required="required"
							                           class="formclass" placeholder="输入收货地址"><br>
							            <label class="lable">联系方式:</label>
							                    <input type="text" name="phone" autocomplete="off" placeholder="输入11位的电话号码"class="formclass"
							                          pattern="^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$" required="required"><br>
							            <label class="lable">信息:</label>
							                   <input type="text" name="relateMessage" autocomplete="off" placeholder="请求购买的要求信息" class="formclass"
							                              required="required"><br>
							                   <br>
							           <input type="submit" value="购买" class="formclass">
	                           </form>
                          </div>
					     <a href="DeleteCart?goodId=${item.good.id}&userId=${sessionScope.user.id}">删除</a>
				 </div>
	       </div> 
     </c:forEach>
   </div>
     <!-- 点击弹出事件 -->
     <script type="text/javascript">
  		var formDisplay = document.getElementsByClassName('form-display')[0];
	
		var btn1 = function(){
			formDisplay.style.display = 'block';
		}
		var btn2 = function(){
			formDisplay.style.display = 'none';
		}
 	 </script>
       <!-- 获取page辅助类进行分析 -->
       <% Page p = (Page)request.getAttribute("p");
          if (p.getList() != null){
        %>
       <%
				if(p.getCurrentPage() == 1){ //尾页
		%>		
		       	<ul>
		       	  <c:if test="${requestScope.p.currentPage != requestScope.p.totalPage}">
			         <li><a href="QueryAllCart?currentPage=${requestScope.p.currentPage+1}">下一页</a></li>
	       	         <li><a href="QueryAllCart?currentPage=${requestScope.p.totalPage}">尾页</a></li>
       	          </c:if>
       	         <li><a href="DeleteAllCart">删除所有商品</a>
       	       </ul>
		<% 
				}
				else if(p.getCurrentPage() == p.getTotalPage()){//首页
		%>	    
       	        <ul>
			         <li><a href="QueryAllCart?currentPage=1">首页</a></li>
					 <li><a href="QueryAllCart?currentPage=${requestScope.p.currentPage-1}">上一页</a></li>
					 <li><a href="DeleteAllCart">删除所有商品</a>
                </ul>  
		<%		
			}
				else{//中间
		%>	  <ul>
					 <li><a href="QueryAllCart?currentPage=1">首页</a></li>
				     <li><a href="QueryAllCart?currentPage=${requestScope.p.currentPage-1}">上一页</a></li>
				     <li><a href="QueryAllCart?currentPage=${requestScope.p.currentPage+1}">下一页</a></li>
       	             <li><a href="QueryAllCart?currentPage=${requestScope.p.totalPage}">尾页</a></li>
       	             <li><a href="DeleteAllCart">删除所有商品</a>
		      </ul> 
		<%			
				}
        } else{
		%> 
		   <ul>
					<li><a href="#">没有商品</a></li>
		  </ul> 
		   
		<%  
          }
		%>
     
     
</body>
</html>