package action.book;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.book.BookCommentWriteProService;
import vo.ActionForward;
import vo.MemberBean;
import vo.book.BookCommentBean;

public class BookCommentWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//int memberNo = Integer.parseInt(String.valueOf(request.getSession().getAttribute("memberNo")));
		MemberBean memberBean = (MemberBean)request.getSession().getAttribute("memberBean");
		int memberNo = memberBean.getNo();
		String isbn = request.getParameter("isbn");
		String page = request.getParameter("page");
		int grade = Integer.parseInt(request.getParameter("grade"));
		String content = request.getParameter("content");
		
		System.out.println("memberNo: " + memberNo);
		System.out.println("isbn: " + isbn);
		System.out.println("page: " + page);
		System.out.println("grade: " + grade);
		System.out.println("content: " + content);
		
		BookCommentBean bookCommentBean = new BookCommentBean();
		bookCommentBean.setContent(content);
		bookCommentBean.setGrade(grade);
		bookCommentBean.setIsbn(isbn);
		bookCommentBean.setMember_no(memberNo);
		
		BookCommentWriteProService bookCommentWriteProService = new BookCommentWriteProService();
		boolean isInsertSuccess = bookCommentWriteProService.insertBookComment(bookCommentBean);
		
		if (isInsertSuccess) {
			// INSERT 수행 결과가 true이면 ActionForward 객체를 사용하여 이동
			// boardList.bo 서블릿 주소 지정, isRedirect 변수 값을 true로 설정(Redirect 방식으로 포워딩)
			forward = new ActionForward();
			forward.setPath("bookContent.do?isbn=" + isbn + "&page=" + page);
			forward.setRedirect(true);		
		} else {
			// INSERT 수행 결과가 false이면 자바스크립트를 사용하여 "등록 실패" 메세지를 표시(alert())
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('게시물 등록 실패!')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		}
		
		return forward;
	}

}
