package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.chart.BookChartAction;
import vo.ActionForward;

@WebServlet("*.gd")
public class GuideFrontController extends HttpServlet {
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
		
		// -----------guide 시작 ------------------------------
		if (command.equals("/guideMap.gd")) {
			forward = new ActionForward();
			forward.setPath("/guide/map.jsp");
		}
		else if (command.equals("/guideDeveloper.gd")) {
			forward = new ActionForward();
			forward.setPath("/guide/developer.jsp");
		}
		else if (command.equals("/guideHowTo.gd")) {
			forward = new ActionForward();
			forward.setPath("/guide/howTo.jsp");
		}
		else if(command.equals("/guideChartForm.gd")) {
			forward = new ActionForward();
			forward.setPath("/guide/bookchart.jsp");
		}
		else if (command.equals("/guideChart.gd")) {
			action = new BookChartAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// -----------guide 끝--------------------------------
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
