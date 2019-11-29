<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>首页</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" type="text/css" />  
 <style type="text/css">
   html,body{
    	background-image: url('${pageContext.request.contextPath}/images/bg1.jpg');
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
        <section id="content">
          <section class="hbox stretch">
            <section>
              <section class="vbox">
                <section class="scrollable padder-lg w-f-md" id="bjax-target">
                  <a href="${pageContext.request.contextPath}/user/toIndexUI.action?curPage=${param.curPage}" class="pull-right text-muted m-t-lg" data-toggle="class:fa-spin" ><i class="icon-refresh i-lg  inline" id="refresh"></i></a>
                  <h2 class="font-thin m-b">Music<span class="musicbar animate inline m-l-sm" style="width:20px;height:20px">
                    <span class="bar1 a1 bg-primary lter"></span>
                    <span class="bar2 a2 bg-info lt"></span>
                    <span class="bar3 a3 bg-success"></span>
                    <span class="bar4 a4 bg-warning dk"></span>
                    <span class="bar5 a5 bg-danger dker"></span>
                  </span></h2>
                 
                 <s:if test="records.size() != 0">
                 	<s:iterator value="records">
                 		  <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
	                      <div class="item">
	                        <div class="pos-rlt">
	                         
	                         <s:a namespace="/music" action="toPlayMusicUI.action">
								<s:param name="musicId" value="%{musicId}"></s:param>
								<s:param name="musicName" value="%{musicName}"></s:param>
								<s:param name="musicPath" value="%{musicPath}"></s:param>
							    <img src="${pageContext.request.contextPath}/images/p11.jpg" alt="" class="r r-2x img-full">
							</s:a>
	                        
	                        </div>
	                        <div class="padder-v">
	                          <s:a namespace="/music" action="toPlayMusicUI.action" class="text-ellipsis">
								<s:param name="musicId" value="%{musicId}"></s:param>
								<s:param name="musicName" value="%{musicName}"></s:param>
								<s:param name="musicPath" value="%{musicPath}"></s:param>	
								${musicName}		
								<%-- <a href="${pageContext.request.contextPath}/music/toPlayMusicUI.action?musicName=${musicName}">${musicName}</a> --%>
							  </s:a>
	                        
	                        </div>
	                      </div>
	                    </div>
                 	</s:iterator>
                 	 <!-- 页码 -->
	                      <ul class="pagination pagination">
	                        <li>
	                        <s:a namespace="/music" action="/findAllMusic.action">
	                        	<s:param name="curPage" value="%{prePageNum}"></s:param>
	                        	 <i class="fa fa-chevron-left"></i>
	                        </s:a>
	                        </li>
	                       
	                       <c:forEach var="curPage" begin="1" end="${totalPageNum}">
	                       
	                       <c:choose>
	                       	<c:when test="${curPage == currentPageNum}">
	                       		<li class="active"><a href="javaScript:void(0)">${curPage}</a></li>
	                       	</c:when>
	                       
	                        <c:otherwise>
	                        	<li>
	                        	<a href="${pageContext.request.contextPath}/music/findAllMusic.action?curPage=${curPage}">
	                       			${curPage} 	
	                        	</a>
                       	  		</li>
	                        </c:otherwise>                      
	                       </c:choose>                      
	                       </c:forEach>
	                 
    
                        <li>
                           <a href="${pageContext.request.contextPath}/music/findAllMusic.action?curPage=${nextPageNum}">
                            	<i class="fa fa-chevron-right"></i>
                           </a>                       	
                        </li>
                        <li style="color: red">总页数:${totalPageNum}</li>
                        <li style="color: red">共${totalRecords}条记录</li>
                    	
                      </ul>
                 </s:if>
                 
                 <s:else>
                 	<h3 style="color: red">无搜索结果</h3>
                 </s:else>
                 
             
                 	
                 
                 
    			           
                   
              </section>
   
	       </section>
        
      </section>
      
    </section>    
     
  </section>
  
  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
   
<script type="text/javascript">
	

		
		
    				
 </script>  


</body>

</html>