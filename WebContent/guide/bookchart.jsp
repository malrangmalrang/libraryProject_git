<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>아이티윌 도서관</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!------------------- CSS/JS ---------------------->
<jsp:include page="../inc/common-append.jsp" />
<!------------------- CSS/JS ---------------------->
<script>
window.onload = function () {
	
	var A = $("#A").text().substring(0, $("#A").text().length-1);
	var B = $("#B").text().substring(0, $("#B").text().length-1);
	var C = $("#C").text().substring(0, $("#C").text().length-1);
	var D = $("#D").text().substring(0, $("#D").text().length-1);
	var E = $("#E").text().substring(0, $("#E").text().length-1);
	var F = $("#F").text().substring(0, $("#F").text().length-1);
	var G = $("#G").text().substring(0, $("#G").text().length-1);
	var H = $("#H").text().substring(0, $("#H").text().length-1);
	
	
	var chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		title:{
			text: "아이티윌 도서 현황"
		},
		legend:{
			cursor: "pointer",
			itemclick: explodePie
		},
		data: [{
			type: "pie",
			showInLegend: true,
			toolTipContent: "{name}: <strong>{y}%</strong>",
			indexLabel: "{name} - {y}%",
			dataPoints: [
				{ y: A, name: "프로그래밍", exploded: true },
				{ y: B, name: "네트워크" },
				{ y: C, name: "서버" },
				{ y: D, name: "웹&디자인" },
				{ y: E, name: "오라클" },
				{ y: F, name: "IT&자격증" },
				{ y: G, name: "자기계발"},
				{ y: H, name: "기타"}
			]
		}]
	});
	chart.render();
}

function explodePie (e) {
	if(typeof (e.dataSeries.dataPoints[e.dataPointIndex].exploded) === "undefined" || !e.dataSeries.dataPoints[e.dataPointIndex].exploded) {
		e.dataSeries.dataPoints[e.dataPointIndex].exploded = true;
	} else {
		e.dataSeries.dataPoints[e.dataPointIndex].exploded = false;
	}
	e.chart.render();
}
</script>
</head>
<body class="w3-light-grey">

<!----------- Sidebar/menu ------------>
<jsp:include page="../inc/sidebar.jsp" />
<!----------- Sidebar/menu ------------>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main main-start">
	<button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey w3-xlarge w3-left" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  	
  	<!------------------------------ 메인 내용 ---------------------------------->
  	<header id="portfolio">
		<div class="w3-container">
			<h1><b>도서현황</b></h1>
		</div>
	</header>
  	
  	<div class="w3-container w3-white">
    	<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white w3-large" style="margin-top: 20px">
    		<c:forEach var="map" items="${categoryCount }">
    			<tr>
    				<td>${map.category_name}</td>
    				<td>${map.count}</td>
					<td id="${map.category }">${map.per }%</td>    				
    			</tr>
    		</c:forEach>
    	</table><br>
  	</div>
  	<div id="chartContainer" style="height: 370px; width: 100%;"></div>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>	
    <!------------------------------ 메인 내용 ---------------------------------->
  
  	<!--------------- footer ---------------->
	<jsp:include page="../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
