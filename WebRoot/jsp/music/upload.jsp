<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>上传音乐</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" type="text/css" />  
  <style type="text/css">
   body{
    	background-image: url('${pageContext.request.contextPath}/images/sdj.jpg');
    	background-repeat: no-repeat;
    	background-size:100% 100%;		
    }
  </style>
    
</head>
<body class="">
 <section class="vbox">
    <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">
      <div class="navbar-header aside bg-info nav-xs">
        
        <a href="${pageContext.request.contextPath}/jsp/default.jsp" class="navbar-brand text-lt">
          <i class="icon-earphones"></i>
          <img src="${pageContext.request.contextPath}/images/logo.png" alt="." class="hide">
         
        </a>
       
      </div>      <ul class="nav navbar-nav hidden-xs">
       
      </ul>

     	<form action="${pageContext.request.contextPath}/music/findAllMusic.action" method="post " id="search_FormID" class="navbar-form navbar-left input-s-lg m-t m-l-n-xs hidden-xs">
     		<div class="form-group">
          <div class="input-group">
            <span class="input-group-btn" onclick="search()">
              <button  class="btn btn-sm bg-white btn-icon rounded"><i class="fa fa-search"></i></button>
            </span>
            <s:textfield name="musicName"  id ="searchID" class="form-control input-sm no-border rounded" placeholder="搜索歌曲"></s:textfield>
          </div>
        </div>
     	</form>
     	
    
    	
   
      
      <div class="navbar-right ">
        <c:choose>
        	<c:when test="${ empty sessionScope.user }">
	        	<ul class="nav navbar-nav m-n hidden-xs nav-user user">
	  						<li><a href="${pageContext.request.contextPath}/user/toLoginUI.action" style="color: red;">登录</a></li>
	  						<li><a href="${pageContext.request.contextPath}/user/toRegisterUI.action" style="color: green;">注册</a></li>
	  			</ul>
        	</c:when>
        	<c:otherwise>
        		 <ul class="nav navbar-nav m-n hidden-xs nav-user user">
          
			          <li class="dropdown">
			            <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">
			              <span class="thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm">
			                <img src="${pageContext.request.contextPath}/images/a0.png" alt="...">
			              </span>
			             ${sessionScope.user.username} 
			             <b class="caret"></b>
			            </a>
			            <ul class="dropdown-menu animated fadeInRight">            
			              <li>
			                <a href="${pageContext.request.contextPath}/user/loginOut.action" data-toggle="ajaxModal" >退出登陆</a>
			              </li>
			            </ul>
			          </li>
                  </ul>
        	</c:otherwise>
        </c:choose>
       
      
        
      </div>      
    </header>
    <section>
      <section class="hbox stretch">
        <!-- .aside -->
        <aside class="bg-black dk nav-xs aside hidden-print" id="nav">          
          <section class="vbox">
            <section class="w-f-md scrollable">
              <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="10px" data-railOpacity="0.2">
                

  <!-- nav -->                 
                <nav class="nav-primary hidden-xs">
                  <ul class="nav bg clearfix">
                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                      Music
                    </li>
                    <li>
                      <a href="${pageContext.request.contextPath}/jsp/default.jsp">
                        <i class="icon-disc icon text-success"></i>
                        <span class="font-bold">回到主页</span>
                      </a>
                    </li>
                    
                    <li>
                      <a href="${pageContext.request.contextPath}/music/toPersonalMusicUI.action">
                        <i class="icon-list icon  text-info-dker"></i>
                        <span class="font-bold">我的歌单</span>
                      </a>
                    </li>
                      <li>
                      <a href="${pageContext.request.contextPath}/music/toUpLoadMusicUI.action">
                        <i class="icon-music-tone icon"></i>
                        <span>上传歌曲</span>
                      </a>
                    </li>
                    <li class="m-b hidden-nav-xs"></li>
                  </ul>
                  
   
                </nav>
                <!-- / nav -->
              </div>
            </section>
            
           
          </section>
        </aside>
        <!-- /.aside -->
        <section id="content">
          <section class="hbox stretch">
            <section>
              <section class="vbox">
                <section class="scrollable padder-lg w-f-md" id="bjax-target">
                  <a href="${pageContext.request.contextPath}/jsp/music/upload.jsp" class="pull-right text-muted m-t-lg" data-toggle="class:fa-spin" ><i class="icon-refresh i-lg  inline" id="refresh"></i></a>
                  <h2 class="font-thin m-b">Music<span class="musicbar animate inline m-l-sm" style="width:20px;height:20px">
                    <span class="bar1 a1 bg-primary lter"></span>
                    <span class="bar2 a2 bg-info lt"></span>
                    <span class="bar3 a3 bg-success"></span>
                    <span class="bar4 a4 bg-warning dk"></span>
                    <span class="bar5 a5 bg-danger dker"></span>
                  </span></h2>
                 
                   <form id = "upload_tableID" action="${pageContext.request.contextPath}/music/upLoadMusic.action" method="post" enctype="multipart/form-data">
				
  			<table align = "center">
  		
  				<tr>
  					<td>
  						选择上传歌曲:<input type="file" name="upload" id="upload"/>
  					</td>
  					<td><input type="submit" value="上传"></td>
  				</tr>
  			
  			 
  			
  			</table>
  		</form>	
  		
  		<c:choose>
  			<c:when test="${upLoadFlag == 'true'}">
  				<script type="text/javascript">
  				alert("上传成功");
  			</script>
		  	</c:when>
		  	
		  	<c:when test="${upLoadFlag == 'false'}">
		  		<script type="text/javascript">
		  			alert("上传失败");
		  		</script>
		  	</c:when>
		  	
		  	<c:when test="${upLoadFlag == '音乐名已存在'}">
		  		<script type="text/javascript">
		  			alert("音乐已存在 ,请选择其他音乐后上传");
		  		</script>
		  	</c:when>
	  </c:choose>
	  
                 
    			           
                   
              </section>
   
	       </section>
        
      </section>
      
    </section>    
     
  </section>
  
  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
   
<script type="text/javascript">

	

		$("#upload_tableID").submit(function(){
			var file = $("#upload").val();
			var typeName = file.substring(file.lastIndexOf(".")).toLowerCase();//把路径的type类型中所有的字幕全部转换为小写
			var allMusicType = ".mp3|.wav|.ogg";
			if(file == null || file.length == 0){
			  	alert("请选择文件");
				
			  	return false;
				
			}else{
				var size = $("#upload")[0].files[0].size;
		  	    if(file.lastIndexOf(".") == -1){//如果不存在"."
		  			alert("路径不正确");
		  			return false;
		  		}else if(allMusicType.indexOf(typeName+"|")==-1){
		  			ErrMsg = "该文件类型不允许上传。请上传"+ allMusicType + "类型的文件,当前文件类型为" + typeName;
		  			alert(ErrMsg);
		  			return false;
		  		}else if(size > 1024*1024*100){
		  			alert("上传文件过大，请上传20M以下的文件");
		  			return false;
		  		}
		  		
		  		return true;
			}
		
		});
    				
   </script>  


</body>


