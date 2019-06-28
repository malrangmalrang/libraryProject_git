package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.notice.NoticeDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class NoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int no = Integer.parseInt(request.getParameter("no")); 
//		int page = Integer.parseInt(request.getParameter("page"));
		String page = request.getParameter("page");
		
		NoticeDetailService noticeDetailService = new NoticeDetailService();
		BoardBean article = noticeDetailService.getArticle(no);
		
		
		request.setAttribute("page", page);
		
		request.setAttribute("article", article);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/center/notice/notice_view.jsp");
		return forward;
	}

}




















