package action.admin;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class WishBookManageListDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("디테일액션");
		ActionForward forward=new ActionForward();
		String bookTitle=request.getParameter("title");
		String bookStatus=request.getParameter("status");
		
		System.out.println("파라미터받아온값타이틀 : " +bookTitle);
		System.out.println("파라미터받아온값상태 : " +bookStatus);
		svc.admin.WishBookManageListDetailService wishBookManageListDetailService=new svc.admin.WishBookManageListDetailService();
		ArrayList wishBookDetailList=wishBookManageListDetailService.getWishBookDetail_Service(bookTitle,bookStatus);
		
		HttpSession session=request.getSession();
		session.setAttribute("wishBookDetailList", wishBookDetailList);
		
		forward.setPath("./admin/page_admin_wishdetail.jsp");
		
		return forward;
	}

}
