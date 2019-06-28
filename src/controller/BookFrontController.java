package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.WishBookAction;
import action.book.BookBestRentAction;
import action.book.BookCommentDeleteProAction;
import action.book.BookCommentWriteProAction;
import action.book.BookFavoriteProAction;
import action.book.BookListAction;
import action.book.BookContentAction;
import action.book.BookRentProAction;
import vo.ActionForward;
import vo.MemberBean;

@WebServlet("*.do") // 서블릿 주소의 .sh 매핑
public class BookFrontController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		Action action = null;
		ActionForward forward = null;

		if (command.equals("/bookList.do")) {
			action = new BookListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookContent.do")) {
			action = new BookContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookRentPro.do")) {
			action = new BookRentProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookFavoritePro.do")) {
			action = new BookFavoriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookBestRent.do")) {
			action = new BookBestRentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookCommentWritePro.do")) {
			action = new BookCommentWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookCommentDeletePro.do")) {
			action = new BookCommentDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// -----------WishBook 시작-----------------------------
		else if (command.equals("/wishBookSearchForm.do")) {
			forward = new ActionForward();
			forward.setPath("/book/wish/book_wish_search.jsp");
		}
		else if (command.equals("/bookWish.do")) {
			// 로그인 체크
			MemberBean memberBean = (MemberBean)request.getSession().getAttribute("memberBean");
			if(memberBean == null) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 후 이용해주세요.')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("/book/wish/book_wish.jsp");
			}
		} 
		else if (command.equals("/wishBookSubscriptionPro.do")) {
			action = new WishBookAction();

			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// -----------WishBook 끝-----------------------------

		// 요청된 서블릿 작업 처리 후 포워딩 처리
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		} else {

		}
	}

}
