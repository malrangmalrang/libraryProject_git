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

</head>
<body class="w3-light-grey">

	<!----------- Sidebar/menu ------------>
	<jsp:include page="../inc/sidebar.jsp" />
	<!----------- Sidebar/menu ------------>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor: pointer" title="close side menu" id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main main-start">
		<button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey w3-xlarge w3-left" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>

		<!------------------------------ 메인 내용 ---------------------------------->
		<!-- Header -->
		<header>
			<div class="w3-container">
				<h1><b>도서대여순서</b></h1>
			</div>
		</header>
		
		<!-- Right Column -->
    <div class="w3-col m12">
      <img src="${pageContext.request.contextPath}/images/main/howto.PNG" style="width:100%">
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>도서 검색</h2>
        <div class="w3-container">
          <h5>
          	<b>1. 도서검색은 키워드, 제목, 저자, 주제, 청구번호로 검색이 가능합니다</b><br>
          	<b>2. 전체도서 검색 시 키워드 없이 검색 누를 시 전체도서 검색이 가능합니다.</b><br>
          	<b>3. 연도별로 최신도서 검색이 가능합니다.</b>
          </h5>
          <hr>
        </div>
        <h2 class="w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>대여 및 반납</h2>
        <div class="w3-container">
          <h5>
          	<b>1. 대여일은 매주 수요일, 금요일 오후 5:30pm 10층 접수처에서 가능합니다. 따라서 매주 수요일, 금요일 12:00pm 까지 접수처에서 도서대여신청서를 작성하셔야 합니다.</b><br>
          	<b>※ 조퇴 시 : 사전수령 / ※결석 시 : 익일 수령(익일 수령시에도 도서대여일은 수or금요일로 처리됩니다.)</b><br><br>
          	<b>2. 대여/반납은 본인이 도서대여신청서에 직접 작성 합니다.(단, 반납은 대리반납이 가능합니다.)</b><br>
          	<b>※ 신규회원(첫 도서대여)의 경우 신규회원등록서를 작성하셔야 합니다.</b>
          </h5>
          <hr>
        </div>
        <h2 class="w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>규정</h2>
        <div class="w3-container">
          <h5>
          	<b>1. 대여권수 : 2주간 2권</b><br>
          	<b>재대여 : 동일한 도서는 반납 3일 경과 후 재대여 가능</b><br>
          	<b>연장 : 대여기간은 2주간으로 연장 불가</b><br>
          	<b>연체 : 연체일수만큼 도서대여 불가</b><br><br>
          	<b>2.분실 및 훼손 : 동일도서 또는 동일CD로 현물변상 또는 시가에 상당하는 금액으로 변상해야 합니다.</b>
          </h5>
          <br>
          <br>
        </div>
      </div>


    <!-- End Right Column -->
    </div>
		<!------------------------------ 메인 내용 ---------------------------------->



		<!--------------- footer ---------------->
		<jsp:include page="../inc/footer.jsp" />
		<!--------------- footer ---------------->

		<!-- End page content -->
	</div>
</body>
</html>
