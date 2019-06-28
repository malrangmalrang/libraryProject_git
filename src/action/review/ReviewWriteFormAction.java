package action.review;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.review.ReviewViewService;
import svc.review.ReviewWriteFormService;
import vo.ActionForward;
import vo.ReviewBean;
import vo.book.BookBean;
import vo.MemberBean;

public class ReviewWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewWriteFormAction");
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
		
		if(memberBean == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용해주세요. ')");
			out.println("history.back()");
			out.println("</script>");
			
			return null;
		}
		
		int member_no = memberBean.getNo();
		ReviewWriteFormService reviewWriteFormService = new ReviewWriteFormService();
		ArrayList<HashMap<String, String>> bookList = reviewWriteFormService.getRentalBookList(member_no);
		
		request.setAttribute("bookList", bookList);
		ActionForward forward = new ActionForward();
		
		forward.setPath("review/review_write.jsp");
		
		
		return forward;
	}

}
