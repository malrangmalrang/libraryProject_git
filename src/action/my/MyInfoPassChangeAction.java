package action.my;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.my.MyInfoPassChangeService;
import vo.ActionForward;

public class MyInfoPassChangeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getParameter("memberNo") == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.')");
			out.println("location.href='index.ma'");
			out.println("</script>");
			
			return null;
		}
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String nowPass = request.getParameter("nowPass");
		String newPass = request.getParameter("newPass");
		
		MyInfoPassChangeService myInfoPassChageService = new MyInfoPassChangeService(); 
		int updateCount = myInfoPassChageService.updatePass(memberNo, nowPass, newPass);
		
		if(updateCount == 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("1");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("0");
		}
		
		return null;
	}

}
