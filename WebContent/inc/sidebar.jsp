<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:4;width:300px;" id="mySidebar">
   
  <a href="index.ma"><img src="${pageContext.request.contextPath}/images/main/logo.png" alt="logo" style="padding: 10px; width: 96%" ></a>
  <hr style="margin: 1px 0 10px 0">
  <div class="w3-container w3-row">
  
 <%
	MemberBean memberBean = (MemberBean)session.getAttribute("memberBean");
	if(memberBean == null) { 
%> 
   	<!--------------------------------------------- 로그인 전 --------------------------------------------------------------------->
   	<button class="w3-container w3-blue w3-text-white w3-border login-join-button" onclick="loginpopup()">
      <span class="w3-large"><strong>로그인</strong></span>
   	</button>
   	<br>
   	<button class="w3-container w3-white w3-border login-join-button" onclick="joinpopup()">
      <span class="w3-large"><strong>회원가입</strong></span>
   	</button>
    <!--------------------------------------------- 로그인 전 --------------------------------------------------------------------->
    
    <!--------- 로그인, 회원가입 팝업 ----------->
    <jsp:include page="../member/login/popup-login.jsp" />
	<jsp:include page="../member/join/popup-join.jsp" />
    <!--------- 로그인, 회원가입 팝업 ----------->
    
<%} else { %>
    
    <div class="w3-col s4">
    <%if(memberBean.getImage().contains("http")) { %>
      <img src="<%=memberBean.getImage() %>" class="w3-circle w3-margin-right" style="width:60px; height: 60px; margin-top: 8px;">
    <%} else { %> 	
      <img src="${pageContext.request.contextPath}/images/member/<%=memberBean.getImage() %>" class="w3-circle w3-margin-right" style="width:60px; height: 60px; margin-top: 8px;">
    <%} %>  	
    </div>
    <div class="w3-col s8 w3-bar">
      <span><a href="myInfo.my""><strong class="w3-xxlarge"><%=memberBean.getName() %></strong></a></span>
      <a href="myInfo.my"" class="w3-right" style="padding: 8px 16px;" id="gear_a"><i class="fa fa-cog" id="gear"></i></a>
      <br><span>님 환영합니다!</span>
      <button class="w3-container w3-border w3-white">
      	<span onclick="location.href='logoutAction.me'"><strong>로그아웃</strong></span>
   	  </button>
    </div>
    <!--------------------------------------------- 로그인 후 --------------------------------------------------------------------->
    
<%} %>    
  </div>
  
  <!----------------------------------------------- 메뉴 --------------------------------------------------------------------->
  <hr>
  <div class="w3-container">
    <h3>Menu</h3>
  </div>
  <div class="w3-bar-block">
    <a href="#" class="w3-bar-item w3-button w3-padding main-menu"><i class="fas fa-book"></i>  도서</a>
    	<div class="sub-menu" style="margin-left: 20px">
    		<a href="bookList.do" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  도서목록</a>
    		<a href="bookBestRent.do" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  베스트대출</a>
    		<a href="bookWish.do" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  희망도서신청</a>
    	</div>	
    <a href="#" class="w3-bar-item w3-button w3-padding main-menu"><i class="fas fa-pen-fancy"></i>  리뷰</a>
    	<div class="sub-menu" style="margin-left: 20px">
    		<a href="reviewList.rv" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  리뷰 게시판</a>
    	</div>	
    <a href="#" class="w3-bar-item w3-button w3-padding main-menu"><i class="far fa-comment-dots"></i>  안내</a>
    	<div class="sub-menu" style="margin-left: 20px">
    		<a href="guideMap.gd" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  오시는길</a>
    		<a href="guideHowTo.gd" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  대여방법</a>
    		<a href="guideDeveloper.gd" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  제작자소개</a>
    		<a href="guideChart.gd" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  도서현황</a>
    	</div>	
    <a href="#" class="w3-bar-item w3-button w3-padding main-menu"><i class="fas fa-wrench"></i>  고객센터</a>
    	<div class="sub-menu" style="margin-left: 20px">
    		<a href="noticeList.bo" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  공지사항</a>
    		<a href="qnaList.bo" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  Q&A</a>
    		<a href="faq.bo" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  FAQ</a>
    	</div>
   	<a href="#" class="w3-bar-item w3-button w3-padding main-menu"><i class="fas fa-user"></i>  나의 서재</a>
    	<div class="sub-menu" style="margin-left: 20px">
    		<a href="myInfo.my" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  나의 정보 보기</a>
    	</div>		
    	
    	<%if(memberBean!=null&&memberBean.getType().equals("관리자")){%>
    	<a href="#" class="w3-bar-item w3-button w3-padding main-menu"><i class="fas fa-wrench"></i>  관리페이지</a>
    	<div class="sub-menu" style="margin-left: 20px">
    		<a href="bookManageList.bm" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  도서관리</a>
    		<a href="memberManageList.mm" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  회원관리</a>
    		<a href="rentalManageList.rm" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  대출관리</a>
    		<a href="wishBookManageList.wm" class="w3-bar-item w3-button w3-padding"><i class="fas fa-angle-right"></i>  희망도서관리</a>
    	</div>
    	<%	
    	 }
    	%>
    	
    		
  </div>
  <hr>
  <!----------------------------------------------- 메뉴 --------------------------------------------------------------------->
  
  <!----------------------------------------------- 공지사항 --------------------------------------------------------------------->
  <script>
  	$.ajax({
		url: "mainNotice.ma",
  		async: true,
  		type: 'GET',
  		dataType: 'json',
  		success: function(data) {
			$.each(data, function(index, item){
				$('.main-notice-content').append(
						'<li><a href="noticeDetail.bo?no=' + item.no + '&page=1" class="w3-bar-item">' + item.content + '</a></li>');
			});
			
			$(function(){
				  $.simpleTicker($("#ticker-roll"),{'effectType':'roll'});
			});
  		},
  		error: function() {
  			alert("요청 실패");
  		}
  	});
  </script>
  <div class="w3-bar-block">
  	<a href="noticeList.bo" class="w3-bar-item w3-button w3-padding w3-large" style="margin-top: 20px">공지사항</a>
  </div>
  <div class="w3-bar-block main-notice-space" id="ticker-roll">
  	<ul class="main-notice-content">
  	</ul>
  </div>
  
  <!----------------------------------------------- 공지사항 --------------------------------------------------------------------->
</nav>
