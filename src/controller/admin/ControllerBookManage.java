package controller.admin;

import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

@WebServlet("*.bm")
public class ControllerBookManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=requestURI.substring(contextPath.length());

		System.out.println(requestURI);
		System.out.println(contextPath);
		System.out.println(command);
		
		ActionForward forward=null;
		Action action=null;
		
		
		if(command.equals("/bookManageList.bm")) {
			action=new action.admin.BookManageListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
		else if(command.equals("/bookManageListDetail.bm")) {
			action=new action.admin.BookManageListDetailAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
			}
		
		}
		
		else if(command.equals("/bookManagedetailUpdatePro.bm")) {
			action=new action.admin.BookManagedetailUpdateProAciton();
			
		    try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/bookManagedetailUpdate.bm")) {
			action=new action.admin.BookManageListUpdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/bookManagedetailDelete.bm")) {
			action=new action.admin.BookManagedetailDeleteAciton();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/bookManageListCheckDelete.bm")) {
			action=new action.admin.BookManageListCheckDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
//	   주말=========================================================================
		
		
		
		else if(command.equals("/bookManageListSearch.bm")) {
			action=new action.admin.BookManageListSearchAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}
			else {
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}//doProcess
	

}
