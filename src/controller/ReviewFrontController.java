package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.qna.QnaModifyFormAction;
import action.qna.QnaModifyProAction;
import action.review.ReviewListAction;
import action.review.ReviewViewAction;
import action.review.ReviewWriteFormAction;
import action.review.ReviewWriteProAction;
import vo.ActionForward;
import vo.MemberBean;

@WebServlet("*.rv")
public class ReviewFrontController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

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
		
		if (command.equals("/reviewList.rv")) {
			System.out.println("ReviewFrontController - reviewList.rv");
			action = new ReviewListAction();
//			ArrayList articleList = (ArrayList) request.getAttribute("articleList");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			request.setAttribute("articleList", articleList);

		} else if (command.equals("/reviewWrite.rv")) {
			System.out.println("write");
			action = new ReviewWriteFormAction();

			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (command.equals("/reviewWritePro.rv")) {
			System.out.println("Controller - reviewWritePro.rv");
			action = new ReviewWriteProAction();

			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (command.equals("/reviewView.rv")) {
			System.out.println("Controller - reviewView.rv");
			action = new ReviewViewAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/fileDown.rv")) {
			forward = new ActionForward();
			forward.setPath("review/file_down.jsp");
		}
//		} else if (command.equals("/reviewUpdateForm.rv")) {
//			action = new ReviewUpdateAction();
//
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//		} else if (command.equals("/reviewUpdateForm.rv")) {
//			System.out.println("reviewUpdateForm.rv");
//			HttpSession session = request.getSession();
//			MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
//			if (memberBean == null) {
//				response.setContentType("text/html;charset=UTF-8");
//				PrintWriter out = response.getWriter();
//				out.println("<script>");// 자바스크립트 시작 태그
//				out.println("alert('로그인이 필요합니다.')");// 오류 메세지 다이얼로그 표시
//				out.println("history.back()");// 이전 페이지로 돌아가기
//				out.println("</script>");// 자바스크립트 종료 태그
//			}
//			action = new ReviewUpdateAction();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else if (command.equals("/reviewUpdatePro.rv")) {
//			System.out.println("reviewUpdatePro.rv");
//			action = new ReviewUpdateProAction();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} 
//		else if (command.equals("/reviewDelete.rv")) {
//			action = new ReviewDeleteAction();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
		// -----------review 끝-----------------------------

		// ----------------------------------------------------------------------------
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
