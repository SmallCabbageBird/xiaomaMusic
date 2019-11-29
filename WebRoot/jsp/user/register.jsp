<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>注册</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" type="text/css" />  
  
</head>
<body class="bg-info dker">
  <h2><a href="${pageContext.request.contextPath}/jsp/default.jsp" style="color: orange;">返回首页</a></h2>
  <section id="content" class="m-t-lg wrapper-md animated fadeInDown">
    <div class="container aside-xl">
      <a class="navbar-brand block" href=""><span class="h1 font-bold">注册</span></a>
      <section class="m-b-lg">
       
        <form action="${pageContext.request.contextPath}/user/register.action" method="post" id="formID">
          <div class="form-group">          
          	<s:textfield  placeholder="用户名(首字母开头,6-16位数字字母组成)" name="username" id="username"  onblur="checkUsername()" class="form-control rounded input-lg text-center no-border"></s:textfield>
          	<span id="username_spanID"></span>
          </div>
          	
          <div class="form-group">
          	<s:password name="password" id="password" onblur="checkPassword()" placeholder="密码(6-16位数字字母组成)" class="form-control rounded input-lg text-center no-border"></s:password>
          	<span id="password_spanID" class="erro"></span>
          </div>
          <div class="form-group">
          	 <s:password name="password2" id="password2" onblur="checkPassword2()" placeholder="重复密码(两次密码要一致)" class="form-control rounded input-lg text-center no-border"></s:password>
          	 <span id="password2_spanID"></span>      	 
          </div>
        
          <button type="submit" class="btn btn-lg btn-warning lt b-white b-2x btn-block btn-rounded"><i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">注册</span></button>
         
        </form>
      </section>
    </div>
  </section>
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder clearfix">
      <p>
        <small>天祥公司所有<br>&copy; 2017</small>
      </p>
    </div>
  </footer>
  <!-- / footer -->
  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<script type="text/javascript">
  <%--检查用户名--%>	 
  	
  var flag = false;
  
  function checkUsername(){
     
	  var username = $("#username").val();
  	 	 
  		 if(username==null||username==""){
  	 		 
  	 		$("#username_spanID").css("color","red");
  	 		 
  	 		 $("#username_spanID").html("用户名不能为空");
  	 		 
  	 		 return flag;
  	 		 
  	 	 } else if(!/^[a-zA-Z0-9]*$/.test(username)){
  	 		 
   	 		$("#username_spanID").css("color","red");
  	 		 
  	 		 $("#username_spanID").html("用户名不能包含非法字符");
  	 		 
  	 		 return flag;
  	 		 
  	 	 }else if(!/^[a-zA-Z][a-zA-Z0-9]*$/.test(username)){
  	 		 
  	 		$("#username_spanID").css("color","red");
 	 		 
 	 		 $("#username_spanID").html("用户名要以字母开头");
 	 		 
 	 		 return flag;
  	 		 
  	 	 }else if(!/^[a-zA-Z][a-zA-Z0-9]{5,15}$/.test(username)){
  	 		
  	 		 $("#username_spanID").css("color","red");
	 		 
	 		 $("#username_spanID").html("用户名由6-16位数字和字母组成");
	 		 
	 		 return flag;
  	 	 }
  		 
  	 	
  	 	var url = "${pageContext.request.contextPath}/user/ajaxCheckUserName.action";
  	 	
  	 	var data = {
  	 			action : "checkUserName",
  	 			username : username
  	 	};
  	 	
  	 	var callback = function(backData){
  	 		
  	 		
  	 		if(backData){
  	 			
				$("#username_spanID").css("color","red");
  	 			
  	 			$("#username_spanID").html("用户名已存在");
 	 			
  	 		
  	 		}else{
  	 			
				$("#username_spanID").css("color","green");
  	 			
  	 			$("#username_spanID").html("用户名可以使用");
				
  	 			flag = true;
  	 			
   	 		
  	 		}
  	 		
  	 	
  	 		 
  	 	};
  	 	
  	 	var type = "json";
  	 
  	 	
  	 	$.post(url,data,callback,type);
  	 
  	 	return flag;
  	 }
   
  
  	<%--检查密码--%>	
  	function checkPassword(){
 		
  		var password = $("#password").val();
 	 	
  		if(password==null||password==""){
  			
  			$("#password_spanID").css("color","red");
 	 		
  			$("#password_spanID").html("密码不能为空");
 	 		
  			return false;
 	 	 
  		}else if(!/^[a-zA-Z0-9]*$/.test(password)){
  			
			$("#password_spanID").css("color","red");
 	 		
  			$("#password_spanID").html("密码不能包含非法字符");
 	 		
  			return false;
 	 	 
  		}else if(!/^[a-zA-Z0-9]{6,16}$/.test(password)){
  			
  			 
			$("#password_spanID").css("color","red");
 	 		
  			$("#password_spanID").html("密码由6-16位数字和字母组成");
 	 		
  			return false;
  		}
  	
  		$("#password_spanID").html("");
 	 	
 	 	 return true;
 	 }
  
  	
  	
  	<%--检查重复密码--%>	
  	function checkPassword2(){
 		 
  		var password2 = $("#password2").val();
 		
  		var password = $("#password").val();
 	 	
  		if(password2==null||password2==""){
  			
  			$("#password2_spanID").css("color","red");
  			
  			$("#password2_spanID").html("重复密码不能为空");
 	 		
  			return false;
 	 	 }
 	 		
 	 	 if(password2!=password){
 	 		
 	 		$("#password2_spanID").css("color","red");
 	 		 
 	 		 $("#password2_spanID").html("两次密码必须相同");
 	 		
 	 		 return false;
 	 	}
 	 		
 	 	 $("#password2_spanID").html("");
 	 	
 	 	 return true;
 	 	
  	}
 	 	 
 	<%--检查所有表单项--%>
  	$("#formID").submit(function(){
  		
  		if(checkUsername()&checkPassword()&checkPassword2()){
  		
  			return true;
  		}
  		
  		return false;
  	});
  	
  </script>
</body>

</html>