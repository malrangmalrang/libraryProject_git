package action.admin;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class WishbookManageListUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String[] values=request.getParameterValues("wishcheck");
		
		for(int i=0; i<values.length; i++) {
			System.out.println("체크항목파라미터 : " +values[i]);
		}
		ActionForward forward=new ActionForward();
		
		svc.admin.WishBookManageListUpdateService wishBookManageListUpdateService=new svc.admin.WishBookManageListUpdateService();
		ArrayList wishBookUpdateList=wishBookManageListUpdateService.getWishBookUpdateList_Service(values);
		
		HttpSession session=request.getSession();
		session.setAttribute("wishBookUpdateList",wishBookUpdateList);
		
		forward.setPath("./admin/page_admin_wishUpdate.jsp");
		
		return forward;
	}

}
