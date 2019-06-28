package action.admin;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;
import vo.PageInfo;

public class RentalManageListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		String rmClass;
		if(request.getParameter("Class")==null) {
			rmClass="전체";
		}
		else {
			rmClass=request.getParameter("Class");
		}
		
		int page = 1;
		int limit = 10;

		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		

		svc.admin.RentalManageListAService rentalManageListAService=new svc.admin.RentalManageListAService();
		int listCount=rentalManageListAService.getListCount(rmClass);
		ArrayList rentalList=rentalManageListAService.RentalManageList_Service(page,limit,rmClass);
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
		
		request.setAttribute("rentalList", rentalList);
		request.setAttribute("pageInfo", pageInfo);
		
		forward.setPath("./admin/page_admin_rental.jsp?Class="+rmClass);
		
		return forward;
	}

}
