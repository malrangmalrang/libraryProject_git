<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>희망도서검색</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!------------------- CSS/JS ---------------------->
<jsp:include page="../../inc/common-append.jsp" />
<!------------------- CSS/JS ---------------------->

<!------------------------ append css ------------------------------>
<link rel="stylesheet" href="./css/book_list.css">
<!------------------------ append css ------------------------------>

<script>
	function clearTd() {
		$('td').remove();
	}
	function search(queryInput,targetkey) {
		event.preventDefault();
		if (queryInput !== undefined && queryInput !== "") {
			$
					.ajax({
						url : "https://dapi.kakao.com/v3/search/book",
						headers : {
							'Authorization' : 'KakaoAK 4100fbb20c986e901f35db27dac3f106'
						},
						type : "get",
						data : {
							query : queryInput,
							size : '50',
							target : targetkey
						},
						success : function(result) {
							clearTd();
							for (var i = 0; i < result.documents.length; i++) {

								let data = result.documents[i];
								let title = data.title;
								let thumbnail = data.thumbnail;
								let authors = data.authors;
								let publisher = data.publisher;
								let price = data.price;
								let date = data.datetime.split('T')[0];
								let isbn = data.isbn.split(' ')[1];
								let contents = data.contents;
								let num = i + 1;
								
								if(thumbnail == "") {
									thumbnail = "${pageContext.request.contextPath}/images/main/noimg.jpg";	
								}
								
								$('table')
										.append(
												"<tr><td>"
													+ "<span class='w3-left w3-xxlarge' style='margin-right: 20px;'><b>"
													+ num + "</b></span>"
													+ "<img src=" + thumbnail +" class='w3-left' style='width: 120px; margin-right: 20px;'>"
													+ "<div><span class='w3-large w3-left'><b>제목</b> : "
													+ title 
   													+ "<br><b>저자</b> : "
   													+ authors 
   													+ "<br><b>출판사</b> : "
   													+ publisher 
   													+ "<br><b>출판일</b> : "
   													+ date 
   													+ "<br><b>가격</b> : "
   													+ price
   													+ "<br><b>isbn</b> : "
   													+ isbn
   													+ "<br></span></div></td><td>"
   													+ "<input type='button' value='선택' class='w3-button w3-red w3-left booklist-td-btn' style='margin-top: 120px;' onclick='searchISBN("
													+ isbn
													+ ");'></td></tr>");
													
							}
						}
					});
		}
	}

	function searchISBN(isbn) {
		event.preventDefault();
		if (isbn !== undefined && isbn !== "") {
			$
					.ajax({
						url : "https://dapi.kakao.com/v3/search/book",
						headers : {
							'Authorization' : 'KakaoAK 4100fbb20c986e901f35db27dac3f106'
						},
						type : "get",
						data : {
							query : isbn,
							target : 'isbn'
						},
						success : function(result) {
							let data = result.documents[0];
							let date = data.datetime.split('T')[0];
							let isbn = data.isbn.split(' ')[1];
							window.opener.document.getElementById('wish_image').value = data.thumbnail;
							window.opener.document.getElementById('wish_title').value = data.title;
							window.opener.document
									.getElementById('wish_authors').value = data.authors;
							window.opener.document
									.getElementById('wish_publisher').value = data.publisher;
							window.opener.document
									.getElementById('wish_publish_date').value = date;
							window.opener.document.getElementById('wish_isbn').value = isbn;
							window.opener.document.getElementById('wish_price').value = data.price;

							var image = data.thumbnail;
							window.opener.document.getElementById('imageView').innerHTML = "<img src="+image+">";

							self.close();
						}
					});
		}
	}
	// 	function sendSearchResult(isbn) {
	// 		opener.document.wishbookinfo.wish_isbn.value = isbn;
	// 		window.opener.document.getElementById('wish_bookname').value = title;
	// 		window.opener.document.getElementById('wish_isbn').value = isbn;
	// 		opener.searchISBN(isbn);

	// 		self.close();
	// 	}
</script>

</head>

<%
	request.setCharacterEncoding("UTF-8");
	String queryInput = request.getParameter("wish_title");
	queryInput = queryInput.replaceAll("\\s", "&nbsp;");
%>

<body class="w3-light-grey" onload="search('+<%=queryInput%>+');">

    
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main main-start">
  
  
  
  	<!------------------------------ 메인 내용 ---------------------------------->
  	<header id="portfolio">
    	<div class="w3-container" id="searchTable">
			<h1><label class="mainlabel"><b>도서검색</b></label></h1>
			<!----- 도서 검색 input ----->
			<select name="targetkey" id="targetkey" class="category-select">
  				<option value="title" selected>제목</option>
	   		   	<option value="person">저자</option>
	           	<option value="isbn">ISBN</option>
	           	<option value="publisher">출판사</option>
			</select>    
			<input type="text" name="queryInput" placeholder="검색어를 입력해주세요." class="common-search" required="required" id="queryInput" value="<%=queryInput%>" style="width:70%">
    		<input type="button" value="SEARCH" class="common-search-button" style="width:10%" onclick="search($('#queryInput').val(),$('#targetkey').val());">
   			<!----- 도서 검색 input ----->

    		<br>
    		<br>
    		<div class="w3-section w3-bottombar w3-padding-16"></div>
    	</div>
    </header>
    
    <div class="w3-container">
    	<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white w3-card book-list-table">
    	</table>
    </div>
    
    <!------------------------------ 메인 내용 ---------------------------------->
  
  
  
  	<!--------------- footer ---------------->
	<jsp:include page="../../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
