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
import action.notice.NoticeDeleteProAction;
import action.notice.NoticeDetailAction;
import action.notice.NoticeListAction;
import action.notice.NoticeModifyFormAction;
import action.notice.NoticeModifyProAction;
import action.notice.NoticeWriteProAction;
import action.qna.QnaCommentDeleteProAction;
import action.qna.QnaCommentModifyProAction;
import action.qna.QnaCommentProAction;
import action.qna.QnaDeleteFormAction;
import action.qna.QnaDeleteProAction;
import action.qna.QnaDetailAction;
import action.qna.QnaListAction;
import action.qna.QnaListSearchAction;
import action.qna.QnaModifyFormAction;
import action.qna.QnaModifyProAction;
import action.qna.QnaWriteProAction;
import action.qna.qnaWriteAction;
import vo.ActionForward;
import vo.MemberBean;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
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
		System.out.println(requestURI);
		System.out.println(contextPath);
		System.out.println(command);
		// 기본 qna페이지 리스트페이지
		if (command.equals("/qnaList.bo")) {
			action = new QnaListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaListSearch.bo")) {
//			String keyword = request.getParameter("keyword");
//			String option = request.getParameter("option");
//			request.setAttribute("option", option);
//			request.setAttribute("keyword", keyword);

			action = new QnaListSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/qnaWrite.bo")) {
			/*
			 * forward = new ActionForward(); forward.setPath("/center/qna/qna_Write.jsp");
			 */
			HttpSession session = request.getSession();
			MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
			if (memberBean == null) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");// 자바스크립트 시작 태그
				out.println("alert('로그인이 필요합니다.')");// 오류 메세지 다이얼로그 표시
				out.println("history.back()");// 이전 페이지로 돌아가기
				out.println("</script>");// 자바스크립트 종료 태그
			}
			action = new qnaWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaWriteForm.bo")) {
			forward = new ActionForward();
			forward.setPath("/center/qna/qna_Write.jsp");

		} else if (command.equals("/qnaWritePro.bo")) {
			System.out.println("Controller - /qnaWritePro.bo");
			action = new QnaWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (command.equals("/qnaDetail.bo")) {
			System.out.println("/qnaDetail.bo에 왔다!");
			action = new QnaDetailAction();
			System.out.println("QnaDetailAction()을 성공했다!");
			try {
				System.out.println("FrontController page : " + request.getParameter("page"));
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (command.equals("/qnaModifyForm.bo")) {
			System.out.println("qnaModifyForm.bo 에 왔다!");
			HttpSession session = request.getSession();
			MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
			if (memberBean == null) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");// 자바스크립트 시작 태그
				out.println("alert('로그인이 필요합니다.')");// 오류 메세지 다이얼로그 표시
				out.println("history.back()");// 이전 페이지로 돌아가기
				out.println("</script>");// 자바스크립트 종료 태그
			}
			action = new QnaModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaModifyPro.bo")) {
			System.out.println("qnaModifyPro.bo 에 왔다!");
			action = new QnaModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/qnaDeleteForm.bo")) {
			System.out.println("qnaDeleteForm.bo 에 왔다!");
			HttpSession session = request.getSession();
			MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
			System.out.println("qnaDeleteForm.bo - memberBean : " + memberBean);
			action = new QnaDeleteFormAction();
			if (memberBean != null) {
//				int board_num = Integer.parseInt(request.getParameter("board_num"));
//				System.out.println("qnaDeleteForm - board_num : " + board_num);
//				request.setAttribute("board_num", board_num);
//				request.setAttribute("page", request.getParameter("page"));
//				forward = new ActionForward();
//				forward.setPath("/center/qna/qna_Delete.jsp");
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("memberBean이 Null");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");// 자바스크립트 시작 태그
				out.println("alert('로그인이 필요합니다.')");// 오류 메세지 다이얼로그 표시
				out.println("history.back()");// 이전 페이지로 돌아가기
				out.println("</script>");// 자바스크립트 종료 태그
			}
		} else if (command.equals("/qnaDeletePro.bo")) {
			System.out.println("qnaDeletePro.bo에 왔다!");
			action = new QnaDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaCommentPro.bo")) {
			System.out.println("qnaCommentPro.bo에 왔다!");
			String comment_content = request.getParameter("comment_content");
			String board_num = (String) request.getParameter("board_num");
//			int page = Integer.parseInt(request.getParameter("page"));
			System.out.println("qnaCommentPro - board_num : " + board_num);
			request.setAttribute("board_num", board_num);
			request.setAttribute("comment_content", comment_content);
//			request.setAttribute("page", page);
			action = new QnaCommentProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaCommentDeletePro.bo")) {
			System.out.println("qnaCommentDeletePro.bo에 도착");
//			String comment_num = request.getParameter("comment_num");
//			String page = request.getParameter("page");
			action = new QnaCommentDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaCommentModifyPro.bo")) {
			System.out.println("qnaCommentModifyPro.bo에 도착");
			action = new QnaCommentModifyProAction();
			System.out.println("QnaCommentModifyProAction()을 성공했다!");
//			int page = Integer.parseInt(request.getParameter("page"));
//			request.setAttribute("page", page);
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} else if (command.equals("/noticeWriteForm.bo")) {
			forward = new ActionForward();
			forward.setPath("./center/notice/notice_write.jsp");
		}

		else if (command.equals("/noticeWritePro.bo")) {
			action = new NoticeWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/noticeList.bo")) {
			System.out.println("/noticeList.bo");
			action = new NoticeListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/noticeDetail.bo")) {
			action = new NoticeDetailAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/noticeModifyForm.bo")) {
			action = new NoticeModifyFormAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/noticeModifyPro.bo")) {
			action = new NoticeModifyProAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/noticeDeleteForm.bo")) {

			forward = new ActionForward();
			forward.setPath("./center/notice/notice_delete.jsp");

		} else if (command.equals("/noticeDeletePro.bo")) {
			action = new NoticeDeleteProAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// -----------qna 끝 --------------------------------

		// --------------faq-----------------------

		else if (command.equals("/faq.bo")) {
			forward = new ActionForward();
			forward.setPath("center/faq/faq.jsp");
		}
		// --------------faq끝---------------------

		// ------------------------------------------------------------------------------
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
