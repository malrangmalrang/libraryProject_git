package action.book;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.book.BookContentService;
import svc.book.NaverAPISearchBook;
import vo.ActionForward;
import vo.MemberBean;
import vo.book.BookBean;
import vo.book.BookCommentBean;

public class BookContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int memberNo = 0;
		
		//int memberNo = Integer.parseInt(String.valueOf(request.getSession().getAttribute("memberNo")));
		MemberBean memberBean = (MemberBean)request.getSession().getAttribute("memberBean");
		if (memberBean != null) {
			memberNo = memberBean.getNo();	
		}
		
		String isbn = request.getParameter("isbn");
		String page = request.getParameter("page");
		String type = request.getParameter("type");

		BookContentService bookRentDetailService = new BookContentService();
		BookBean bookBean = bookRentDetailService.getBook(memberNo, isbn);

		// 관심도서 등록 여부 --> getBook()에 추가 
		//boolean isFavorBook = bookRentDetailService.isFavoriteBook(memberNo, isbn);
		
		// 책 소개(NAVER BOOK API)
		String description = NaverAPISearchBook.getInstance().searchBook(isbn);
		
		// 댓글..
		ArrayList<BookCommentBean> bookCommentList = bookRentDetailService.getBookCommentList(isbn);

		request.setAttribute("memberNo", memberNo);
		request.setAttribute("page", page);
		request.setAttribute("book", bookBean);
		//request.setAttribute("isFavorBook", isFavorBook); --> getBook()에 추가. 생략
		request.setAttribute("description", description);
		request.setAttribute("bookCommentList", bookCommentList);
		request.setAttribute("type", type);
		
		// 로그인 유무 확인을 위한 memberNo 추가
		request.setAttribute("memberNo", memberNo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("book/rental/bookContent.jsp"); // 이동할 jsp 페이지 지정

		return forward;
	}

}
