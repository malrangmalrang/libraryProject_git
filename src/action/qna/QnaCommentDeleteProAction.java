package action.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.QnaCommentDeleteProService;
import vo.ActionForward;

public class QnaCommentDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		int page = Integer.parseInt(request.getParameter("page"));
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		QnaCommentDeleteProService qnaCommentDeleteProService = new QnaCommentDeleteProService();
		boolean isDeleteSuccess = qnaCommentDeleteProService.removeComment(comment_num,page,board_num);
		if(!isDeleteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");// 자바스크립트 시작 태그
			out.println("alert('삭제실패!')");// 오류 메세지 다이얼로그 표시
			out.println("history.back()");// 이전 페이지로 돌아가기
			out.println("</script>");// 자바스크립트 종료 태그				
		}
		//성공시 ActionForward 객체 생성, "boardList.bo"로 포워딩시 파라미터로 page(form태그 hidden으로 전달된 파라미터) 전달
		else {
			forward = new ActionForward();
			forward.setPath("./qnaDetail.bo?board_num="+board_num+"&page="+nowPage);
			forward.setRedirect(true);//Redirect 방식
		}
		return forward;
	}

}
