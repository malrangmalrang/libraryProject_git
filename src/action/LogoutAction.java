package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;


public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		
		ActionForward forward=null;
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		forward = new ActionForward();
		forward.setPath("index.ma");
		forward.setRedirect(true);
		
		return forward;
	}

}
