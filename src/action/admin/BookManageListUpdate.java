package action.admin;

import java.util.Set;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class BookManageListUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		int no=Integer.parseInt(request.getParameter("no"));
		System.out.println(no);
		
		svc.admin.BookManageListDetailService bookManageListDetailService= new svc.admin.BookManageListDetailService();
		vo.admin.BookList bookList=new vo.admin.BookList();
		
		bookList=bookManageListDetailService.getBookManageListDetail_Service(no);
		
//		HttpSession session=request.getSession();
		request.setAttribute("BookManageListDetail", bookList);
//		session.setAttribute("BookManageListDetail", bookList);
		

		
		
		forward.setPath("./admin/page_admin_bookdetailUpdate.jsp");
		return forward;
	}

}
