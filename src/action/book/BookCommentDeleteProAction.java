package action.book;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.book.BookCommentDeleteProService;
import vo.ActionForward;
import vo.MemberBean;

public class BookCommentDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//int memberNo = Integer.parseInt(String.valueOf(request.getSession().getAttribute("memberNo")));
		MemberBean memberBean = (MemberBean)request.getSession().getAttribute("memberBean");
		int memberNo = memberBean.getNo();
		int bookCommentNo = Integer.parseInt(request.getParameter("bookCommentNo"));

		System.out.println("memberNo: " + memberNo);
		System.out.println("bookCommentNo: " + bookCommentNo);
		
		BookCommentDeleteProService bookCommentDeleteProService = new BookCommentDeleteProService();
		boolean isDeleteSuccess = bookCommentDeleteProService.deleteBookComment(bookCommentNo, memberNo);
		
		if (isDeleteSuccess) {
			// INSERT 수행 결과가 true이면 ActionForward 객체를 사용하여 이동
			// boardList.bo 서블릿 주소 지정, isRedirect 변수 값을 true로 설정(Redirect 방식으로 포워딩)
			forward = new ActionForward();
			forward.setPath("bookContent.do?isbn=" + request.getParameter("isbn") + "&page=" + request.getParameter("page"));
			forward.setRedirect(true);		
		} else {
			// INSERT 수행 결과가 false이면 자바스크립트를 사용하여 "등록 실패" 메세지를 표시(alert())
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('댓글 삭제 실패!')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		}
		
		return forward;
	}

}
