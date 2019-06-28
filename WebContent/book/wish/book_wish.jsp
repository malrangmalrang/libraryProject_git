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
<link rel="stylesheet" href="./css/book_list.css">
<!------------------------ append css ------------------------------>


<script>
	function openSearch() {

		var wish_title = document.wishbookinfo.wish_title.value;

		if (wish_title == "") {
			window.open("./book/wish/book_wish_search.jsp?wish_title=#", "post",
					"height = 700, width = 1900");
		} else if (wish_title != "") {
			window.open("./book/wish/book_wish_search.jsp?wish_title=" + wish_title,
					"post", "height = 700, width = 1900");
		}
	}

	function searchISBN(isbn) {
		event.preventDefault();
		if (isbn !== undefined && isbn !== "") {
			$
					.ajax({
						url : "https://dapi.kakao.com/v3/search/book",
						headers : {
							'Authorization' : '보안을 위해 비공개'
						},
						type : "get",
						data : {
							query : isbn,
							target : 'isbn'
						},
						success : function(result) {
							let data = result.documents[0];
							
							var imageIn = data.thumbnail;
							$("#wish_title").val(data.title);
							$('#wish_authors').val(data.authors);
							$('#wish_publisher').val(data.publisher);
							$('#wish_price').val(data.price);
							$('#wish_image').val(data.thumbnail);
							let date = data.datetime.split('T')[0];
							$('#wish_publish_date').val(date);

							$('#imageView').empty();
							$('#imageView').append("<img src="+imageIn+">");
							
// 							alert(document.wishbookinfo.wish_price.value);
						}
					});
		}
	}
</script>

</head>
<body class="w3-light-grey">

<!----------- Sidebar/menu ------------>
<jsp:include page="../../inc/sidebar.jsp" />
<!----------- Sidebar/menu ------------>

     
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main main-start">
	<button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey w3-xlarge w3-left" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  
  
  
  	<!------------------------------ 메인 내용 ---------------------------------->
<header id="portfolio">
    	<div class="w3-container">
    		<h1><b>희망 도서 신청</b></h1>
  			<div class="w3-section w3-bottombar w3-padding-16"></div>
    	</div>
  </header>
  
  
   <div class="w3-container">
   	<form action="wishBookSubscriptionPro.do" method="post", name="wishbookinfo">
   		<input type="hidden" id="wish_image" name="wish_image">
    	<table id="wishbook" class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white w3-card book-list-table w3-large">
    		<tr>
    			<td class="n1"><label for="wish_title"><b>도서명</b></label></td>
				<td><input type="text" name="wish_title" id="wish_title" class="wishbook-input" required="required" > 
				<input type="button" value="검색" name="search" onclick="openSearch()" class="w3-button w3-red wishbook-td-btn"></td>
    		</tr>
			<tr>
				<td class="n1"><label for="wish_authors"><b>저자</b></label></td>
				<td><input type="text" name="wish_authors" id="wish_authors" class="wishbook-input" required="required"></td>
			</tr>
			<tr>
				<td class="n1"><label for="wish_publisher"><b>출판사</b></label></td>
				<td><input type="text" name="wish_publisher" id="wish_publisher" class="wishbook-input" required="required"></td>
			</tr>
			<tr>
				<td class="n1"><label for="wish_publish_date"><b>출판일자</b></label></td>
				<td><input type="text" name="wish_publish_date" id="wish_publish_date" class="wishbook-input" required="required"/></td>
			</tr>
			<tr>
				<td class="n1"><label for="wish_isbn"><b>ISBN</b></label></td>
				<td><input type="text" name="wish_isbn" class="wishbook-input" required="required" id="wish_isbn" />
				<input type="button" value="isbn으로 가져오기" onclick="searchISBN($('#wish_isbn').val());" class="w3-button w3-red wishbook-td-btn"></td>
			</tr>
			<tr>
				<td class="n1"><label for="wish_price"><b>가격</b></label></td>
				<td><input type="number" name="wish_price" class="wishbook-input" id="wish_price" required="required"/></td>
			</tr>
			<tr>
				<td><label for="imageView"><b>이미지</b></label></td>
				<td id="imageView"></td>
			</tr>
    	</table><br>
    	<input type="submit" class="w3-button w3-dark-grey booklist-td-btn" style="margin-top: 10px; margin-left: 40%" value="등록">
    	<input type="reset" class="w3-button w3-dark-grey booklist-td-btn" style="margin-top: 10px;" value="다시쓰기">
    </form>
  </div>
    	
    <!------------------------------ 메인 내용 ---------------------------------->
  
  
  
  	<!--------------- footer ---------------->
	<jsp:include page="../../inc/footer.jsp" />
	<!--------------- footer ---------------->
	
 <!-- End page content -->
</div>
</body>
</html>
