package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.WishBookService;
import vo.ActionForward;
import vo.WishBookBean;
import vo.admin.MemberBean;

public class WishBookAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("wishBookSubscriptionPro.bo");
		
		ActionForward forward = null;
		WishBookBean wishBookBean = null;
		
//		System.out.println("wishBookBean set start");
		
		wishBookBean = new WishBookBean();
		
		wishBookBean.setTitle(request.getParameter("wish_title"));
		wishBookBean.setAuthors(request.getParameter("wish_authors"));
		wishBookBean.setPublisher(request.getParameter("wish_publisher"));
		wishBookBean.setPublish_date(request.getParameter("wish_publish_date"));
		wishBookBean.setIsbn(request.getParameter("wish_isbn"));
		wishBookBean.setPrice(Integer.parseInt(request.getParameter("wish_price")));
		wishBookBean.setImage(request.getParameter("wish_image"));
		
//		System.out.println("wishBookBean set finish");
		
		WishBookService wishBookService = new WishBookService();
		boolean isWriteSuccess = wishBookService.wishBookRegArticle(wishBookBean);
		
		
		if(!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('등록실패')");
			out.print("history.back()");
			out.print("</script>");
			out.close();
		}else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("bookWish.do");
		}
		return forward;
	}

}
