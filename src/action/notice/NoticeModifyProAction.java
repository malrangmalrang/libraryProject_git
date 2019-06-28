package action.notice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.notice.NoticeModifyProService;
import vo.ActionForward;
import vo.BoardBean;
import vo.MemberBean;

public class NoticeModifyProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeModifyProAction");
		
		ActionForward forward = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeModifyProService noticeModifyProService = new NoticeModifyProService();
//		boolean isRightUser = noticeModifyProService.isArticleWriter(no, request.getParameter("notice_pass"));
//		boolean isRightUser = noticeModifyProService.isArticleWriter(request.getParameter("member_no"));
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean)session.getAttribute("memberBean");
		boolean isRightUser = memberBean.getType().equals("관리자");
				
		System.out.println("isRightUser = " + isRightUser);
		
		if(!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('수정할 권한이 없습니다.')"); 
			out.println("history.back()"); 
			out.println("</script>"); 
		} else {
			BoardBean article = new BoardBean();
			article.setNo(no);
			article.setTitle(request.getParameter("title"));
			article.setContent(request.getParameter("content"));
			article.setFile(request.getParameter("file"));
			
			boolean isModifySuccess = noticeModifyProService.modifyArticle(article);
			
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>"); 
				out.println("alert('수정 실패!')"); 
				out.println("history.back()"); 
				out.println("</script>"); 
			} else {
				System.out.println("글 수정 성공!");
				forward = new ActionForward();
				forward.setPath("noticeDetail.bo?no=" + no + "&page=1");
				forward.setRedirect(true);
			}
			
		}
		
		return forward;
	}

}





















