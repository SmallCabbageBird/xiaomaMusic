<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  
  <title>登录</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" type="text/css" />  
  
</head>
<body class="bg-info dker">
<h2><a href="${pageContext.request.contextPath}/jsp/default.jsp" style="color: orange;">返回主页</a></h2>
  <section id="content" class="m-t-lg wrapper-md animated fadeInUp">    
    <div class="container aside-xl">
      <a class="navbar-brand block" href=""><span class="h1 font-bold">登录</span></a>
      <section class="m-b-lg">
        <form action="${pageContext.request.contextPath}/user/login.action" method="post" id="formID">
          <input type="hidden" name="action" value="login">
          <div class="form-group">
          	<s:textfield name="username" id="username" placeholder="用户名" class="form-control rounded input-lg text-center no-border"></s:textfield>
          	<span id="username_spanID"></span>
          </div>
          <div class="form-group">
         	 <s:password name="password" id="password" placeholder="密码" class="form-control rounded input-lg text-center no-border"></s:password>
          </div>
          <button type="submit" class="btn btn-lg btn-warning lt b-white b-2x btn-block btn-rounded"><i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">登录</span></button>
          
          <div class="text-center m-t m-b"><a href="${pageContext.request.contextPath}/user/ForgetPwd.action"><small>忘记密码?</small></a></div>
          <div class="line line-dashed"></div>
          <a href="${pageContext.request.contextPath}/user/toRegisterUI.action" class="btn btn-lg btn-info btn-block rounded">注册</a>
        </form>
      </section>
    </div>
  </section>
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder">
      <p> 
        	<small>天祥公司所有<br>&copy; 2017</small>
      </p>
    </div>
  </footer>
  <!-- / footer -->
  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

	<c:if test="${flag=='false'}">
  		<script type="text/javascript">
 
  				$("#username_spanID").css("color","red");
	  			$("#username_spanID").html("用户名或者密码错误");
			
  				
  			
  		
  		</script>
  	</c:if>
</body>
</html>