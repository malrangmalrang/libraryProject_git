package action.notice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.notice.NoticeDeleteProService;
import vo.ActionForward;

public class NoticeDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeDeleteProAction");
		
		ActionForward forward = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeDeleteProService noticeDeleteProService = new NoticeDeleteProService();
		
//		boolean isRightUser = noticeDeleteProService.isArticleWriter(no, request.getParameter("notice_pass"));
		
//		if(!isRightUser) {
//			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>"); 
//			out.println("alert('삭제할 권한이 없습니다.')"); 
//			out.println("history.back()"); 
//			out.println("</script>"); 
//		} else {
		
			boolean isDeleteSuccess = noticeDeleteProService.removeArticle(no);
			
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>"); 
				out.println("alert('삭제실패!')"); 
				out.println("history.back()"); 
				out.println("</script>"); 
			} else {
				forward = new ActionForward();
				forward.setPath("noticeList.bo?page=" + request.getParameter("page"));
				forward.setRedirect(true); 
			}
			
		
		return forward;
	}

}
