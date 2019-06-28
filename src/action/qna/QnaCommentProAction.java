package action.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.QnaCommentProService;
import vo.ActionForward;
import vo.BoardBean;
import vo.CommentBean;
import vo.MemberBean;

public class QnaCommentProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		CommentBean comment = new CommentBean();
		HttpSession session = request.getSession();
		MemberBean memberBean= (MemberBean) session.getAttribute("memberBean");
		String email = memberBean.getEmail();
		int nowPage = Integer.parseInt(request.getParameter("page"));
		comment.setBoard_no(Integer.parseInt(request.getAttribute("board_num").toString()));
//		comment.setNo((int)request.getAttribute("board_num"));
		comment.setContent(request.getParameter("comment_content"));
		
		QnaCommentProService qnaCommentProService = new QnaCommentProService();
		boolean isWriteSuccess = qnaCommentProService.registComment(comment,email);
		if(!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('코멘트 등록 실패!')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		} else {
			// true 이면 ActionForward 객체를 사용하여 이동
			// => ActionForward 객체 생성, boardList.bo 서블릿주소 지정, isRedirect 변수 값을 true 로 설정
			// => boardList.bo 페이지로 이동하면서 주소가 변경되므로(새로운 요청이 발생하므로) Redirect 방식으로 포워딩
			forward = new ActionForward();
			forward.setPath("qnaDetail.bo?board_num="+comment.getBoard_no()+"&page="+nowPage);
			forward.setRedirect(true);
		}
		return forward;
	}

}
