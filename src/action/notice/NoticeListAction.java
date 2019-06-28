package action.notice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.notice.NoticeListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("NoticeListAction ");
		
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		
		int page = 1;
		int limit = 10;
		
		request.setCharacterEncoding("utf-8");
		String search=request.getParameter("search");
		
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		NoticeListService noticeListService = new NoticeListService();
		
		if(search==null) {
			int listCount = noticeListService.getListCount();
			
			articleList = (ArrayList<BoardBean>) noticeListService.getArticleList(page, limit); 
			
		}else {
			int listCount = noticeListService.getListCount(search);
			
			articleList = (ArrayList<BoardBean>) noticeListService.getArticleList(page, limit,search); 
			
		}
		
		int listCount = noticeListService.getListCount();
//		
//		articleList = (ArrayList<BoardBean>) noticeListService.getArticleList(page, limit); 
//		
		int maxPage = (int)((double)listCount / limit + 0.95); 
		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1; 
		int endPage = startPage + 10 - 1; 
		
		if(endPage > maxPage) { 
			endPage = maxPage; 
		}
		
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
	
		ActionForward forward = new ActionForward();
		forward.setPath("/center/notice/notice_list.jsp");
		
		
		return forward;
	}
}


















