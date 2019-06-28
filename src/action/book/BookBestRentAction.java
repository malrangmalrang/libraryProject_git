package action.book;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.book.BookBestRentService;
import vo.ActionForward;
import vo.MemberBean;
import vo.book.BookBean;

public class BookBestRentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int memberNo = 0;
		
		//int memberNo = Integer.parseInt(String.valueOf(request.getSession().getAttribute("memberNo")));
		MemberBean memberBean = (MemberBean)request.getSession().getAttribute("memberBean");
		/*if(memberBean == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.')");
			out.println("history.back()");
			out.println("</script>");
			
			return null;
		}*/
		
		if (memberBean != null) {
			memberNo = memberBean.getNo();	
		}
		
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		
		String category = "";

		if (request.getParameter("category") != null) {
			category = request.getParameter("category");
		}
		
		BookBestRentService bookBestRentService = new BookBestRentService(category, memberNo);
		//int listCount = bookBestRentService.getBookBestRentListCount();
		bookList = bookBestRentService.getBookBestRentList();

		// PageInfo 객체와 ArrayList 객체를 request 객체의 setAttribute() 메서드를 사용하여 저장
		request.setAttribute("bookList", bookList);
		request.setAttribute("category", category);
		
		// 로그인 유무 확인을 위한 memberNo 추가
		request.setAttribute("memberNo", memberNo);

		// ActionForward 객체를 사용하여 board 폴더의 qna_board_list.jsp 페이지로 이동 처리 => Dispatch 방식 포워딩
		// => 기존 boardList.do 주소를 변경하지 않고 바로 jsp 페이지로 이동하기 위해서
		ActionForward forward = new ActionForward();
		forward.setPath("/book/bestRental/bookBestRent.jsp");

		return forward;
	}

}
