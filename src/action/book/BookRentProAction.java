package action.book;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.book.BookRentProService;
import vo.ActionForward;
import vo.MemberBean;
import vo.book.DbCode;
import vo.book.RentBean;

public class BookRentProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//int memberNo = Integer.parseInt(String.valueOf(request.getSession().getAttribute("memberNo")));
		MemberBean memberBean = (MemberBean)request.getSession().getAttribute("memberBean");
		int memberNo = memberBean.getNo();
		String isbn = request.getParameter("isbn");
		
		System.out.println("isbn: " + isbn);
		
		BookRentProService bookRentProService = new BookRentProService();
		
		int bookNo = bookRentProService.getRentableBookNo(isbn);
		//RentBean rentBean = new RentBean(0, null, null, null, "out", memberNo, bookNo);
		RentBean rentBean = new RentBean(0, null, null, null, DbCode.RENTAL_STATUS_REQ, memberNo, bookNo);
		boolean isInsertSuccess = bookRentProService.insertBookRent(rentBean);
		
		// UPDATE book SET status = 'out' WHERE no = '1';
		
		if (isInsertSuccess) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("bookList.do"); // 이동할 jsp 페이지 지정
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('대출 실패.........................')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		}
		
		return forward;
	}

}
