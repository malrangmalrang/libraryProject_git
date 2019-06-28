package action.book;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.book.BookListService;
import vo.ActionForward;
import vo.MemberBean;
import vo.book.BookBean;
import vo.book.PageInfo;

public class BookListAction implements Action {

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
		String searchType = "";
		String searchWord = "";
		String category = "";
		int page = 1;
		int limit = 10;
		if (request.getParameter("searchType") != null) {
			searchType = request.getParameter("searchType");
		}
		
		if (request.getParameter("searchWord") != null) {
			searchWord = request.getParameter("searchWord");
		}
		
		if (request.getParameter("category") != null) {
			category = request.getParameter("category");
		}
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		BookListService bookRentService = new BookListService(searchType, searchWord, category, page, limit, memberNo);
		int listCount = bookRentService.getBookListCount();
		bookList = bookRentService.getBookList();

		int maxPage = (int)((double)listCount / limit + 0.95); // 총 페이지 수 (0.95를 더해서 올림 처리)		
		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1; // 현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21, ...)
		int endPage = startPage + 10 - 1; // 현재 페이지에 보여줄 마지막 페이지 수 (10, 20, 30, ...)

		if (endPage > maxPage) { // 마지막 페이지 번호가 최대 페이지 번호보다 클 경우
			endPage = maxPage; // 마지막 페이지 번호를 최대 페이지 번호로 대체
		}

		// 페이지 번호 관련 정보를 PageInfo 객체에 저장
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setNowPage(page);
		pageInfo.setStartPage(startPage);

		// PageInfo 객체와 ArrayList 객체를 request 객체의 setAttribute() 메서드를 사용하여 저장
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("bookList", bookList);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("category", category);
		
		// 로그인 유무 확인을 위한 memberNo 추가
		request.setAttribute("memberNo", memberNo);
		

		// ActionForward 객체를 사용하여 board 폴더의 qna_board_list.jsp 페이지로 이동 처리 => Dispatch 방식 포워딩
		// => 기존 boardList.do 주소를 변경하지 않고 바로 jsp 페이지로 이동하기 위해서
		ActionForward forward = new ActionForward();
		forward.setPath("/book/rental/bookList.jsp");
		return forward;
	}

}
