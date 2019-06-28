package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.EmailCheckService;
import vo.ActionForward;
import vo.MemberBean;

public class EmailCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward=null;
		int isCheckSuccess=0;
		
		MemberBean memberBean = new MemberBean();
		memberBean.setEmail(request.getParameter("userEmail"));
		EmailCheckService emailCheckService=new EmailCheckService();
		isCheckSuccess= emailCheckService.emailCheck(memberBean);
		
		if(isCheckSuccess==0) {
			
			forward = new ActionForward();
			forward.setPath("./member/join/emailCheck.jsp?email="+request.getParameter("userEmail"));
			forward.setRedirect(true);
			
		}else if(isCheckSuccess==1) {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 중복입니다.')");
			out.println("window.close()");
			out.println("</script>");
		    out.close();			
		}
		
		
		System.out.println(memberBean.toString());
		
		return forward;
	}

}
