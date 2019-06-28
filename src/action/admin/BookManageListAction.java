package action.admin;

import java.sql.Date;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;
import vo.PageInfo;

public class BookManageListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String Class;
		if(request.getParameter("Class")==null) {
			Class="All";
		}
		else {
			Class=request.getParameter("Class");
		}
        ActionForward forward=null;
        
		ArrayList bookListVo=new ArrayList();
		
		int page = 1;
		int limit = 10;

		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		svc.admin.BookManangeListService bookManangeListService=new svc.admin.BookManangeListService();	
		
		int listCount=bookManangeListService.getListCount(Class);
		bookListVo=bookManangeListService.getBookList(page, limit, Class);
		
		int maxPage = (int)((double)listCount / limit + 0.95); // 총 페이지 수 계산(올림처리를 위해 + 0.95)
		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1; // 현재 페이지에 표시할 시작 페이지) 번호
		int endPage = startPage + 10 - 1; // 현재 페이지에 표시할 마지막 페이지 번호
		
		if(endPage > maxPage) { // 마지막 페이지 번호가 최대 페이지 번호보다 클 경우
			endPage = maxPage; // 마지막 페이지 번호를 최대 페이지 번호로 대체
		}
		
		// 페이지 번호 관련 정보를 PageInfo 객체에 저장
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		
		
		
		request.setAttribute("bookList", bookListVo);
		request.setAttribute("pageInfo", pageInfo);
		forward=new ActionForward();
		forward.setPath("./admin/page_admin_book.jsp?Class="+Class);
		return forward;
	}

}

	
