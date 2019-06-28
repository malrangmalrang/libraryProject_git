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

@WebServlet("*.wm")
public class ControllerWishBookManage extends HttpServlet {

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
		
		if(command.equals("/wishBookManageList.wm")) {
			action= new action.admin.WishBookManageListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/wishBookManageListUpdate.wm")) {
			action=new action.admin.WishbookManageListUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/wishBookManageListUpdatePro.wm")) {
			action=new action.admin.WishBookManageListUpdateProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/wishBookManageListRefuse.wm")) {
			action=new action.admin.WishBookManageListRefuseAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/wishBookManageListDetail.wm")) {
			System.out.println("ControllerWishBookManage - /wishBookManageListDetail.wm ");
			action=new action.admin.WishBookManageListDetailAction();
			System.out.println("ControllerWishBookManage - /wishBookManageListDetail.wm 성공 ");
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
