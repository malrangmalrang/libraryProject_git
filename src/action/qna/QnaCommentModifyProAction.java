package action.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.QnaCommentModifyProService;
import vo.ActionForward;

public class QnaCommentModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		String comment_content = request.getParameter("comment_content");
		QnaCommentModifyProService qnaCommentModifyProService = new QnaCommentModifyProService();
		
		boolean isModifySuccess = qnaCommentModifyProService.modifyComment(comment_num,comment_content);
		if(!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('수정 실패!')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		}else {
			forward = new ActionForward();
			System.out.println("글 수정 성공!");
			// 글 수정 성공 시
			// boardDetail.bo 서블릿 주소로 포워딩 => 주소 뒤에 파라미터로 글번호(board_num) 전달 => Redirect 방식
			forward.setPath("qnaDetail.bo?board_num=" + board_num+"&page="+nowPage);
			forward.setRedirect(true);
		}
		return forward;
	}

}
