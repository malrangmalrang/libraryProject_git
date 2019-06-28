package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.EmailFindService;
import svc.PasswordFindService;
import vo.ActionForward;
import vo.MemberBean;

public class PasswordFindAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward=null;
		int isCheckSuccess=0;
	//	System.out.println(request.getParameter("name"));
		MemberBean memberBean = new MemberBean();
		memberBean.setEmail(request.getParameter("email"));
		memberBean.setPhone(request.getParameter("phone"));
	
		//System.out.println(memberBean.getName());
		PasswordFindService passwordFindService=new PasswordFindService();
		MemberBean memberBean2=passwordFindService.passwordFind(memberBean);
		
		if(memberBean2!=null) {
			forward = new ActionForward();
			forward.setPath("./member/login/passwordCheck.jsp?password="+memberBean2.getPassword());
			forward.setRedirect(false);
			
		}else if(memberBean2==null) {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 찾기 불가 정보재입력 바랍니다')");
			out.println("window.close()");
			out.println("</script>");
		    out.close();			
		}
		
		
		System.out.println(memberBean.toString());
		
		
		
		
		return forward;
	}
	
	

}
