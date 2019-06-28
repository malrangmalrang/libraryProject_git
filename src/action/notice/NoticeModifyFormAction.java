package action.notice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.BoardModifyProService;
import svc.notice.NoticeDetailService;
import vo.ActionForward;
import vo.BoardBean;
import vo.MemberBean;

public class NoticeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		int no = Integer.parseInt(request.getParameter("no"));
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
		boolean isRightUser = memberBean.getType().equals("관리자");
		if (!isRightUser) {
			// 패스워드가 일치하지 않을 경우
			// 자바스크립트를 사용하여 "수정할 권한이 없습니다." 출력 후 이전 페이지로 이동
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('수정할 권한이 없습니다.')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		} else {

			NoticeDetailService noticeDetailService = new NoticeDetailService();
			BoardBean article = noticeDetailService.getArticle(no);

			request.setAttribute("article", article);

			forward.setPath("/center/notice/notice_modify.jsp");
		}
		return forward;
	}

}
