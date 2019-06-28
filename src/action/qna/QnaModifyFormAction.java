package action.qna;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import dao.MemberDAO;
import svc.BoardDetailService;
import svc.BoardModifyProService;
import vo.ActionForward;
import vo.BoardBean;
import vo.MemberBean;

public class QnaModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
		String email = memberBean.getEmail();
		int nowPage = Integer.parseInt(request.getParameter("page"));
		BoardModifyProService boardModifyProService = new BoardModifyProService();
		boolean isRightUser = boardModifyProService.isArticleWriter(board_num, email);
		if(!isRightUser) {
			// 패스워드가 일치하지 않을 경우
			// 자바스크립트를 사용하여 "수정할 권한이 없습니다." 출력 후 이전 페이지로 이동
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('수정할 권한이 없습니다.')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		} else {
			BoardDetailService boardDetailService = new BoardDetailService();
			BoardBean article = null;
			ArrayList beans = boardDetailService.getArticle(board_num);  
			article = (BoardBean) beans.get(0);
			memberBean = (MemberBean) beans.get(1);
			
			request.setAttribute("article", article);
			request.setAttribute("memberBean", memberBean);
			request.setAttribute("page", nowPage);
			forward.setPath("/center/qna/qna_Modify.jsp");
		}
		return forward;
	}

}
