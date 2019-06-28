<%@page import="vo.CommentBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.MemberBean"%>
<%@page import="vo.BoardBean"%>
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
<script type="text/javascript">
//댓글작성시 엔터키 가능
function comment(){
	var str = document.getElementById("textarea").value;
	str = str.replace(/(?:\r\n|\r|\n)/g, '<br>');
	document.getElementById("result").value = str;
// 	str = str.replace('\r\n', '<br>');
}
//댓글 수정시 엔터키 가능
function comment_modify(id){
	var str = document.getElementById("modify_result_"+id).value;
	str = str.replace(/(?:\r\n|\r|\n)/g, '<br>');
	document.getElementById("willModify_"+id).value = str;
	
// 	str = str.replace('\r\n', '<br>');
}

//댓글창 보이게/숨기게
function SirenFunction(idMyDiv){
	//<br>을 엔터로
	var str = document.getElementById("modify_textarea_"+idMyDiv).value;
	str = str.split("<br>").join("\r\n")
	document.getElementById("modify_result_"+idMyDiv).value = str;
	//<br>을 엔터로
	var objDiv = document.getElementById('SirenDiv'+idMyDiv);
	if(objDiv.style.display=="block"){ objDiv.style.display = "none"; }
	else{ objDiv.style.display = "block"; }
}
</script>
<%
	BoardBean article = (BoardBean) request.getAttribute("article");
	int nowPage = Integer.parseInt(request.getAttribute("page").toString());
	MemberBean board_memberBean = (MemberBean) request.getAttribute("memberBean");
	MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
	ArrayList commentList = (ArrayList) request.getAttribute("commentList");
	ArrayList<CommentBean> commentBeanList = (ArrayList<CommentBean>)commentList.get(0);
	ArrayList<MemberBean> commentMemberList = (ArrayList<MemberBean>)commentList.get(1);
%>
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
		<div class="w3-content w3-margin-top" style="max-width: 1400px;">

			<!-- The Grid -->
			<div class="w3-row-padding">

				<!-- Center Column -->
				<div class="w3-col m11">
					<div class="w3-container w3-card w3-white">
						<div class="w3-center" style="margin-top: 30px">
							<h3><%=article.getTitle()%></h3>
							<h5>
								<span class="w3-opacity">by <%=board_memberBean.getName()%><br><%=article.getReg_date()%>
									&nbsp;&nbsp;&nbsp; 조회수 <%=article.getReadcount()%></span>
							</h5>
						</div>
						<div class="review-content w3-justify w3-margin">
							<!-- 글내용 넣는곳 -->
							<p><%=article.getContent()%>
							</p>
							<!-- 글내용 넣는곳 -->

							<!-- 이미지 넣는곳 -->
							<%
								if (article.getFile() != null) {
							%>
							<p class="w3-center">
								<a href="file_down2.jsp?file_name=<%=article.getFile()%>"><%=article.getFile()%></a>
								<img src="../center/qna/upload/<%=article.getFile()%>"
									class="w3-margin"><img
									src="../center/qna/upload/<%=article.getFile()%>"
									class="w3-margin">
							</p>
							<%
								}
							%>

							<!-- 이미지 넣는곳 -->

							<p>
								<a href="qnaList.bo?page=<%=nowPage%>" class="w3-right" style="margin-left: 10px">목록</a>
								<a href="qnaDeleteForm.bo?board_num=<%=article.getNo()%>&page=<%=nowPage%>" class="w3-right" style="margin-left: 10px">삭제</a> 
								<a href="qnaModifyForm.bo?board_num=<%=article.getNo()%>&page=<%=nowPage%>" class="w3-right">수정</a>
							</p>
						</div>
						<%
							if (memberBean != null) {
								if (memberBean.getType().equals("관리자")) {
						%>
						<div class="w3-margin review-content">
							<hr>
							<br>
							<form action="qnaCommentPro.bo" method="post">
								<input type="hidden" name="page" value="<%=nowPage%>"> <input
									type="hidden" name="board_num" value="<%=article.getNo()%>">
								<textarea id="textarea" class="w3-input w3-border book-comment-input" placeholder="내용을 입력해주세요."></textarea>
								<textarea hidden="hidden" id="result" name="comment_content"></textarea>
								<span>
									<button type="submit" class="w3-button w3-padding w3-white w3-border w3-large" style="vertical-align: top; height: 68px" onclick="comment()">
										<b>등록</b>
									</button>
								</span>
							</form>
							<p>
								<span class="w3-padding-small w3-xlarge"><b>답변 </b></span>
							</p>
							<%
								}
							}
							%>
							<!-------------------------------- 댓글 -------------------------------------->
							<%
								for (int i = 0; i < commentBeanList.size(); i++) {
							%>
							<div class="w3-row" >
								<div class="w3-col m2 text-center">
								<%if(commentMemberList.get(i).getImage().contains("http")) {%>
									<img class="w3-circle"
										src="<%=commentMemberList.get(i).getImage()%>"
										style="width: 96px; height: 96px">
								<%} else { %>
									<img class="w3-circle"
										src="${pageContext.request.contextPath}/images/member/<%=commentMemberList.get(i).getImage()%>"
										style="width: 96px; height: 96px">
								<%} %>
								</div>
								<div class="w3-col m10 w3-container">
									<h4>
										<%=commentMemberList.get(i).getName()%>
										<span class="w3-opacity w3-medium"><%=commentBeanList.get(i).getReg_date()%>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<%if(memberBean!=null){ %>
											<%	if (memberBean.getNo()==commentMemberList.get(i).getNo()){ %>
<%-- 											<a href="qnaCommentModifyForm.bo?comment_num=<%=commentBeanList.get(i).getNo()%>&page=<%=nowPage%>">수정</a> --%>
<%-- 											<a href="#" onclick="SirenFunction('SirenDiv<%=commentBeanList.get(i).getNo()%>'); return false;" class="blind_view">수정</a> --%>
											<a href="#" onclick="SirenFunction('<%=commentBeanList.get(i).getNo()%>'); return false;" class="blind_view">수정</a>
											<a href="qnaCommentDeletePro.bo?comment_num=<%=commentBeanList.get(i).getNo()%>&page=<%=nowPage%>&board_num=<%=article.getNo() %>" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
											
											
											
											<%	} 
											  }%>
										</span>
									</h4>
									<p><%=commentBeanList.get(i).getContent()%></p>
									<div style="display: none;" id="SirenDiv<%=commentBeanList.get(i).getNo()%>">
										<form action="qnaCommentModifyPro.bo?page=<%=nowPage %>" method="post">
											<textarea style="width: 100%; border: 1px solid grey;" rows="8" id="modify_result_<%=commentBeanList.get(i).getNo()%>" class="w3-input" placeholder="내용을 입력해주세요."></textarea>
											<input type="hidden" name="comment_num" value="<%=commentBeanList.get(i).getNo()%>"> 
											<input type="hidden" name="board_num" value="<%=article.getNo()%>">
											<br>
											
											<textarea hidden="hidden" id="modify_textarea_<%=commentBeanList.get(i).getNo()%>" ><%=commentBeanList.get(i).getContent()%></textarea>
											<textarea hidden="hidden" name = "comment_content"  id="willModify_<%=commentBeanList.get(i).getNo()%>"></textarea>
											<input type="submit" class="reply" value="수정" onclick="comment_modify(<%=commentBeanList.get(i).getNo()%>)">
											<input type="button" onclick="SirenFunction('<%=commentBeanList.get(i).getNo()%>'); return false;" value="취소">
										</form>
									</div>
								</div>
							</div>
							<hr>
							<%
								}
							%>
							<!-------------------------------- 댓글 ---------------------------------------->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!------------------------------ 메인 내용 ---------------------------------->



		<!--------------- footer ---------------->
		<jsp:include page="../../inc/footer.jsp" />
		<!--------------- footer ---------------->

		<!-- End page content -->
	</div>
</body>
</html>
