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
<jsp:include page="../../inc/common-append.jsp" />
<!------------------- CSS/JS ---------------------->

<!------------------------ append css ------------------------------>
<link rel="stylesheet" href="./css/table.css">
<!------------------------ append css ------------------------------>
<!-- 아코디언 자체 css -->
<style>
.accordion_q {
	/* background-color: #eee; */
	background-color: white;
	cursor: pointer;
	/* padding: 18px; */
	width: 100%;
	border: none;
	text-align: left;
	outline: none;
	font-size: 22px;
	transition: 0.4s;
}

.accordion_active, .accordion_q:hover {
	background-color: #ccc;
}

.accordion_q:after {
	content: '\002B';
	color: #777;
	font-weight: bold;
	float: right;
	margin-left: 5px;
}

.accordion_active:after {
	content: "\2212";
}

.panel_a {
	padding: 0 18px;
	background-color: white;
	max-height: 0;
	overflow: hidden;
	transition: max-height 0.2s ease-out;
}
</style>
<!-- 아코디언 자체 css -->
</head>
<body class="w3-light-grey">

	<!----------- Sidebar/menu ------------>
	<jsp:include page="../../inc/sidebar.jsp" />
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
					<b>FAQ</b>
				</h1>
			</div>
		</header>

		<br> <br>

		<div class="w3-container">
    		<ul class="w3-ul w3-card-4 w3-white">
      			<li class="w3-padding-16">
        			<button class="accordion_q">
        				<img src="${pageContext.request.contextPath}/images/main/q.png" class="w3-left w3-circle w3-margin-right" style="width:35px">
        				소장 자료 현황은 어떻게 되나요?
        			</button>		
        			<div class="panel_a">
						<p>
							ITWILL 부산교육센터에서는 보유도서를 카테고리(프로그밍/네트워크/서버/웹&디자인/오라클/IT&자격증/자기계발/기타)별로 관리하고 있습니다.<br>
							보유도서 현황은 '메뉴->안내->도서현황'에서 확인하실 수 있으며 보유하고 있지 않은 도서는 희망도서메뉴를 통해 신청하실 수 있습니다.<br>
							그 외에 매년 가치 있는 도서를 선별하여 장서를 더욱 더 확대해 나갈 예정입니다.
						</p>
					</div>
      			</li>
      			<li class="w3-padding-16">
        			<button class="accordion_q">
        				<img src="${pageContext.request.contextPath}/images/main/q.png" class="w3-left w3-circle w3-margin-right" style="width:35px">
        				운영시간은 어떻게 되며, 휴관일은 언제인가요?
        			</button>
        			<div class="panel_a">
						<p>- 운영시간 : 평일 09:00 - 17:40</p>
						<p>- 휴관일 : 주말, 공휴일</p>
						<p>&nbsp;</p>
						<p>
							기타 교육기관에서 임시로 휴관일을 지정할 수 있으며, 공지사항을 통해 안내해드리고 있습니다.<br>
							휴관일에는 원활한 도서 이용을 위해 장서 및 시설 점검이 이루어집니다.
						</p>
					</div>
      			</li>
      			<li class="w3-padding-16">
        			<button class="accordion_q">
        				<img src="${pageContext.request.contextPath}/images/main/q.png" class="w3-left w3-circle w3-margin-right" style="width:35px">
        				도서검색은 어떻게 하나요?
        			</button>
        			<div class="panel_a">
						<p>
							'메뉴->도서목록'에서 보유도서 전체 목록은 물론 카테고리별로 조회가능합니다.<br>
							보유하고 있지 않은 도서는 검색이 불가하며 원하는 도서를 대출신청 할 수 있습니다.<br>
							대출이 불가능한 도서는 '대출불가'라고 표시가 되며, 해당 도서가 대출되어 반납되지 않은 상태입니다.
						</p>
					</div>
      			</li>
      			<li class="w3-padding-16">
        			<button class="accordion_q">
        				<img src="${pageContext.request.contextPath}/images/main/q.png" class="w3-left w3-circle w3-margin-right" style="width:35px">
        				대출 신청은 어떻게 이루어지나요?
        			</button>
        			<div class="panel_a">
						<p>
						  홈페이지를 통해 대출신청이 이루어지면 도서관을 방문하여 관리자를 통해 승인처리를 거쳐 대출하실 수 있습니다.<br>
						</p>
					</div>
      			</li>
      			
      						<li class="w3-padding-16">
        			<button class="accordion_q">
        				<img src="${pageContext.request.contextPath}/images/main/q.png" class="w3-left w3-circle w3-margin-right" style="width:35px">
        				희망도서 신청은 어떻게 이루어지나요?
        			</button>
        			<div class="panel_a">
						<p>
						  홈페이지를 통해 희망도서 신청이 이루어지면 해당 도서의 신청 수 및 필요에 따라 관리자를 통해 승인 후 구매 및 등록하게 됩니다. 
						</p>
					</div>
      			</li>
      			
      				<li class="w3-padding-16">
        			<button class="accordion_q">
        				<img src="${pageContext.request.contextPath}/images/main/q.png" class="w3-left w3-circle w3-margin-right" style="width:35px">
        				회원가입을 하였는데 로그인이 안됩니다.
        			</button>
        			<div class="panel_a">
						<p>
						 최초 회원가입을 하실경우 미승인인 상태로 가입이 되며, 홈페이지는 관리자의 승인 후 이용하실 수 있습니다.<br>
						 홈페이지를 통해 회원가입 후 안내데스크 및 관리자에게 승인 요청을 해주시면 감사하겠습니다.
						</p>
					</div>
      			</li>
      			
      			    <li class="w3-padding-16">
        			<button class="accordion_q">
        				<img src="${pageContext.request.contextPath}/images/main/q.png" class="w3-left w3-circle w3-margin-right" style="width:35px">
        				궁금하거나 불편한 점이 있으면 어디로 연락하나요?
        			</button>
        			<div class="panel_a">
						<p>
						궁금하거나 불편한 점이 있으시면 '메뉴->고객센터->Q&A게시판'에 글을 등록하여주시면 확인 후 직접답변<br>
					    혹은 공지사항에 게시할 수 있도록 하겠습니다.
						</p>
					</div>
      			</li>
      			
      			<li class="w3-padding-16">
        			<button class="accordion_q">
        				<img src="${pageContext.request.contextPath}/images/main/q.png" class="w3-left w3-circle w3-margin-right" style="width:35px">
        				복사나 스캔이 가능한가요?
        			</button>
        			<div class="panel_a">
						<p>승인된 교육생이신 분들은 관리자 허가하에 복사 및 스캔을 하실 수 있습니다.</p>
					</div>
      			</li>
    		</ul>
  		</div>
  		<hr>
		<!------------------------------ 메인 내용 ---------------------------------->



		<!--------------- footer ---------------->
		<jsp:include page="../../inc/footer.jsp" />
		<!--------------- footer ---------------->

		<!-- 아코디언 스크립트 -->
		<script>
			var acc = document.getElementsByClassName("accordion_q");
			var i;

			for (i = 0; i < acc.length; i++) {
				acc[i].addEventListener("click", function() {
					this.classList.toggle("accordion_active");
					var panel = this.nextElementSibling;
					if (panel.style.maxHeight) {
						panel.style.maxHeight = null;
					} else {
						panel.style.maxHeight = panel.scrollHeight + "px";
					}
				});
			}
		</script>

		<!-- JAVASCRIPTS -->
		<script
			src="${pageContext.request.contextPath}/layout/scripts/jquery.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/layout/scripts/jquery.backtotop.js"></script>
		<script
			src="${pageContext.request.contextPath}/layout/scripts/jquery.mobilemenu.js"></script>
		<!-- JAVASCRIPTS -->
		
		<!-- 아코디언 스크립트 -->
		<!-- End page content -->
	</div>
</body>
</html>
