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
import action.my.MyInfoPassChangeAction;
import action.my.MyInfoProfilProAction;
import vo.ActionForward;
import vo.MemberBean;

@WebServlet("*.my")
public class MyFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		Action action = null;
		ActionForward forward = null;
		System.out.println(requestURI);
		System.out.println(contextPath);
		System.out.println(command);
		
		// 회원정보
		if(command.equals("/myInfo.my")) {
			HttpSession session = request.getSession();
			MemberBean memberBean = (MemberBean)session.getAttribute("memberBean");
			
			if(memberBean == null) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 후 이용해주세욧!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("/mypage/page_my_info.jsp");
			}
		}
		// 회원정보 - 비밀번호 체크
		else if(command.equals("/myInfoPassChange.my")) {
			action = new MyInfoPassChangeAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원정보 - 프로필 이미지 변경
		else if(command.equals("/myInfoProfilForm.my")) {
			forward = new ActionForward();
			forward.setPath("./mypage/page_my_profil.jsp");
		}
		else if(command.equals("/myInfoProfilPro.my")) {
			
			action = new MyInfoProfilProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
