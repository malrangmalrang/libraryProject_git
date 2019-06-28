<%@page import="vo.BoardBean"%>
<%@page import="vo.MemberBean"%>
<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>아이티윌 도서관</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!------------------- CSS/JS ---------------------->
<jsp:include page="../inc/common-append.jsp" />
<!------------------- CSS/JS ---------------------->

<!------------------------ append css ------------------------------>
<link rel="stylesheet" href="./css/table.css">
<!------------------------ append css ------------------------------>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout_developer.css">
</head>
<body class="w3-light-grey">

	<!----------- Sidebar/menu ------------>
	<jsp:include page="../inc/sidebar.jsp" />
	<!----------- Sidebar/menu ------------>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main main-start">
		<button
			class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey w3-xlarge w3-left"
			onclick="w3_open();">
			<i class="fa fa-bars"></i>  Menu
		</button>



		<!------------------------------ 메인 내용 ---------------------------------->
		<!-- Header -->
		<header id="portfolio">
			<div class="w3-container">
				<h1>
					<b>제작자 소개</b>
				</h1>

			</div>
		</header>

		<br> <br>

		<div class="w3-container">

	<section class="uza-portfolio-area section-padding-80">

		<div class="container-fluid">
			<div class="row">
				<!-- Team Member Slides -->
				<div class="team-sildes owl-carousel">

					<!-- Single Team Slide -->
					<div class="single-team-slide">
						<img src="${pageContext.request.contextPath}/images/developer/염지현.PNG" alt="" height="230px" width="230px" style="border-radius: 50%">
						<!-- Overlay Effect -->
						<div class="overlay-effect">
							<h6>DEVELOPER</h6>
							<h4>염지현</h4>
							<p>맡은역할 : 공지사항 및 차트디자인</p>
							<p>하고 싶은 말 : 모두 고생하셨습니다</p>
							
						</div>
					</div>

					<!-- Single Team Slide -->
					<div class="single-team-slide" style="height: 520px">
						<img style="transform: rotate(90deg); border-radius: 50%; width: 230px; height: 230px" src="${pageContext.request.contextPath}/images/developer/이현욱.jpg" alt="">
						<!-- Overlay Effect -->
						<div class="overlay-effect">
							<h6>DEVELOPER</h6>
							<h4>이현욱</h4>
							<p>맡은 부분 : QNA페이지<br>
							하고싶은 말 : 너무 힘들어요</p>
						</div>
					</div>

					<div class="single-team-slide">
						<img src="${pageContext.request.contextPath}/images/developer/gji.jpg" alt="변정훈" title="변정훈" height="230px" width="230px" style="border-radius: 50%">
						<!-- Overlay Effect -->
						<div class="overlay-effect" style="padding: 0;">
							<h6>CSS왕 변복동</h6>
							<h4>변정훈</h4>
							<p><b>맡은 부분</b> : 메인 CSS, 메인 페이지 기능들, 회원정보 수정, 리뷰 글쓰기 지원, 자잘한 기능들<br>
							<b>하고싶은 말</b> :
							술 한잔 마셨습니다... 사이트가 잘 안돌아가도 좋습니다. 하지만 변정훈 하나만 기억해주세요 진심을 다해 전합니다. 
							사이트가 별로 일수있습니다 밤낮으로 고민하고 만들었습니다...최선을 다했고 열심히 했습니다 저의 진심이 느껴지길 바랍니다 고맙습니다...
							<br>
							(사진의 모습은 프로젝트를 끝낸 제작자의 마지막 모습이라고 한다. 손에 들고 있는 책은 CSS 책이라고...)</p>
						</div>
					</div>

					<div class="single-team-slide">
						<img src="${pageContext.request.contextPath}/images/developer/손희목.jpg" alt="" height="230px" width="230px" style="border-radius: 50%">
						<!-- Overlay Effect -->
						<div class="overlay-effect">
							<h6>DEVELOPER</h6>
							<h4>손희목</h4>
							<p>맡은 부분 : 도서 검색<br>
하고싶은 말 : JAVA가 더 재밌다...</p>
						</div>
					</div>

					<div class="single-team-slide">
						<img src="${pageContext.request.contextPath}/images/developer/이영신.jpg" alt="" height="230px" width="230px" style="border-radius: 50%">
						<!-- Overlay Effect -->
						<div class="overlay-effect">
							<h6>DEVELOPER</h6>
							<h4>이영신</h4>
							<p>맡은 부분 : 회원가입 및 로그인<br>
							하고 싶은말 : 장사가 더 쉬웠어용</p>
						</div>
					</div>

					<div class="single-team-slide">
						<img src="${pageContext.request.contextPath}/images/developer/KMS.jpg" height="230px" width="230px" style="border-radius: 50%">
						<!-- Overlay Effect -->
						<div class="overlay-effect">
							<h6>DEVELOPER</h6>
							<h4>김민섭</h4>
							<p>생년월일 : 1990.09.26</p>
							<p>맡은역할 : 관리자페이지(대출관리,도서관리,회원관리,희망도서관리)</p>
							<p>하고싶은 말 : Fighting</p>
							
						</div>
					</div>

					<div class="single-team-slide">
						<img src="${pageContext.request.contextPath}/images/developer/sktt1클리드.png" alt="" height="230px" width="230px" style="border-radius: 50%">
						<!-- Overlay Effect -->
						<div class="overlay-effect">
							<h6>DEVELOPER</h6>
							<h4>권순화</h4>
							<p>맡은역할 : 희망도서신청</p>
							<p>소속 : SKT T1 Telecom</p>
							<p>한마디 : 솔킬 따이면 갱없음</p>
							
						</div>
					</div>

					<div class="single-team-slide">
						<img src="${pageContext.request.contextPath}/images/developer/KakaoTalk_20190620_141449829.jpg" height="230px" width="230px" style="border-radius: 50%">
						<!-- Overlay Effect -->
						<div class="overlay-effect">
							<h6>DEVELOPER</h6>
							<h4>이상호</h4>
							<p>맡은 부분 : 리뷰게시판<br>
							하고 싶은말 : 수고하셨습니다.</p>
						</div>
					</div>

				</div>
			</div>
		</div>
	</section>

		<!------------------------------ 메인 내용 ---------------------------------->

<!-- ******* All JS Files ******* -->
	<!-- Bootstrap js -->
	<script src="${pageContext.request.contextPath}/scripts/bootstrap.min.js"></script>
	<!-- All js -->
	<script src="${pageContext.request.contextPath}/scripts/uza.bundle.js"></script>
	<!-- Active js -->
	<script src="${pageContext.request.contextPath}/scripts/active.js"></script>

		<!--------------- footer ---------------->
		<jsp:include page="../inc/footer.jsp" />
		<!--------------- footer ---------------->

		<!-- End page content -->
	</div>
</body>
</html>
