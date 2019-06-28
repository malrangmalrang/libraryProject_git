package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.EmailCheckAction;
import action.EmailFindAction;
import action.JoinProAction;
import action.LoginProAction;
import action.LogoutAction;
import action.PasswordFindAction;
import vo.ActionForward;

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {

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
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/loginForm.me")) {
			forward = new ActionForward();
//			forward.setPath("member/loginForm.jsp");
			forward.setPath("index.ma");
		}else if(command.equals("/loginPro.me")) {
			action = new LoginProAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/joinForm.me")) {
			forward = new ActionForward();
			forward.setPath("member/joinForm.jsp");
		}else if(command.equals("/joinPro.me")) {
			System.out.println("컨트롤러 joinpro.me");
			action = new JoinProAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/logoutAction.me")) {
			action=new LogoutAction();

			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} else if(command.equals("/emailCheck.me")) {
			action=new EmailCheckAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/emailFind.me")) {
			action=new EmailFindAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/emailFindAction.me")) {
			action=new EmailFindAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/emailFindForm.me")) {
			forward = new ActionForward();		
			forward.setPath("./member/login/emailFind.jsp");
			
		}else if(command.equals("/passwordFindAction.me")) {
			action=new PasswordFindAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/passwordFindForm.me")) {
			forward = new ActionForward();		
			forward.setPath("./member/login/passwordFind.jsp");
		}
		
	
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




