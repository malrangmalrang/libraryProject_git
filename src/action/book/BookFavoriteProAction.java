package action.book;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.book.BookFavoriteProService;
import vo.ActionForward;
import vo.MemberBean;

public class BookFavoriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//int memberNo = Integer.parseInt(String.valueOf(request.getSession().getAttribute("memberNo")));
		MemberBean memberBean = (MemberBean)request.getSession().getAttribute("memberBean");
		int memberNo = memberBean.getNo();
		String isbn = request.getParameter("isbn");
		String favor = request.getParameter("favor");
		
		System.out.println("BookFavoriteProAction's memberNo: " + memberNo);
		System.out.println("BookFavoriteProAction's isbn: " + isbn);
		System.out.println("BookFavoriteProAction's favor: " + favor);
		
		BookFavoriteProService bookFavoriteProService = new BookFavoriteProService();
		
		boolean isSuccess = false;
		String resultMsg = "";
		if (favor.equals("insert")) {
			isSuccess = bookFavoriteProService.insertBookFavor(isbn, memberNo);
			resultMsg = "등록"; 
		} else { // delete
			isSuccess = bookFavoriteProService.deleteBookFavor(isbn, memberNo);
			resultMsg = "삭제";
		}
		
		// UPDATE book SET status = 'out' WHERE no = '1';
		
		if (isSuccess) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("bookList.do"); // 이동할 jsp 페이지 지정
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('관심도서 " + resultMsg + " 실패')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		}
		
		return forward;
	}

}
