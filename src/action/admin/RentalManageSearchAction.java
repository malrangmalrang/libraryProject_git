package action.admin;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class RentalManageSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward =new ActionForward();
		String rentalSearchSelectValue=request.getParameter("rentalSearchSelectValue");
		String rentalSearchText=request.getParameter("rentalSearchText");
		
		svc.admin.RentalManageSearchService rentalManageSearchService=new svc.admin.RentalManageSearchService();
		
		ArrayList rentalList=rentalManageSearchService.rentalSearchList_Service(rentalSearchSelectValue, rentalSearchText);
		
		request.setAttribute("rentalList", rentalList);
		forward.setPath("./templates/pages/rentalManage.jsp");
		
		return forward;
	}

}
