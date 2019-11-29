<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>我的歌单</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" type="text/css" />  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/smusic.css"  type="text/css"/>
   <style type="text/css">
    body{
    	background-image: url('${pageContext.request.contextPath}/images/bgimg.jpg');
    	background-repeat: no-repeat;
    	background-size:100% 100%;		
    }
   </style>
</head>
<body >
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
       


<div id="douban" style="margin:auto">


<div class="grid-music-container f-usn">
    <div class="m-music-play-wrap">
        <div class="u-cover"></div>
        <div class="m-now-info">
            <h1 class="u-music-title"><strong>标题</strong><small>歌手</small></h1>
            <div class="m-now-controls">
                <div class="u-control u-process">
                    <span class="buffer-process"></span>
                    <span class="current-process"></span>
                </div>
                <div class="u-control u-time">00:00/00:00</div>
                <div class="u-control u-volume">
                    <div class="volume-process" data-volume="0.50">
                        <span class="volume-current"></span>
                        <span class="volume-bar"></span>
                        <span class="volume-event"></span>
                    </div>
                    <a class="volume-control"></a>
                </div>
            </div>
            <div class="m-play-controls">
                <a class="u-play-btn prev" title="上一曲"></a>
                <a class="u-play-btn ctrl-play play" title="暂停"></a>
                <a class="u-play-btn next" title="下一曲"></a>
                <a class="u-play-btn mode mode-list current" title="列表循环"></a>
                <a class="u-play-btn mode mode-random" title="随机播放"></a>
                <a class="u-play-btn mode mode-single" title="单曲循环"></a>
            </div>
        </div>
  	  </div>
   			 <div class="f-cb">&nbsp;</div>
   			 <div class="m-music-list-wrap"></div>
		</div>
	</div>


   			  
   			
   
   
	       </section>
        
      </section>
      
    </section>    
     
  </section>
 

  
  
  
  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
   <!-- Bootstrap -->
  <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  <script src="${pageContext.request.contextPath}/js/smusic.min.js"></script>
  
  <script type="text/javascript">

  	var musicList = new Array();
  </script>
 <s:iterator  value="musicList">
 	<script type="text/javascript">
 		var music = {
 				title :  '${musicName}',
		  		singer : '暂无信息 ',//写死
		  		cover  : '${pageContext.request.contextPath}/images/mp3/Arston.jpg',//写死
		  		src    : '${pageContext.request.contextPath}/${musicPath}'	
 		};
 	    musicList.push(music);
 	</script>
 </s:iterator>
<script type="text/javascript">

 
          new SMusic({
          	musicList:musicList
          });
    				
 </script>  


</body>

</html>