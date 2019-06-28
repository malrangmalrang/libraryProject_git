package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MainAction;
import action.notice.MainNoticeAction;
import action.notice.NoticeListAction;
import vo.ActionForward;

@WebServlet("*.ma")
public class FrontController extends HttpServlet {
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
		// -----------Main 시작 ---------------------------------
		if (command.equals("/index.ma")) {
			action = new MainAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (command.equals("/main.ma")) {
			forward = new ActionForward();
			forward.setPath("main/index.jsp");
		}
		else if (command.equals("/mainNotice.ma")) {
			action = new MainNoticeAction();
			try {
				action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		// -----------Main 끝 ----------------------------------

		
		

		// -----------notice 시작 ----------------------------
		else if (command.equals("/noticeList.bo")) {
			action = new NoticeListAction();

			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// -----------notice 끝 ----------------------------

		// -----------faq 시작---------------------------------
		else if (command.equals("/faq.bo")) {
			forward = new ActionForward();
			forward.setPath("notice/faq.jsp");
		}
		// -----------faq 끝 ----------------------------------

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
