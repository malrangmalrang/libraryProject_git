package action;

import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginProService;
import svc.MemberInfoService;
import vo.ActionForward;
import vo.MemberBean;

public class LoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		ActionForward forward=null;
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		System.out.println("LoginProAction - email : "+email);
		System.out.println("LoginProAction - password : "+password);
		
		MemberBean memberBean=new MemberBean();
		memberBean.setEmail(email);
		memberBean.setPassword(password);
		
		LoginProService loginProService=new LoginProService();
		int isLoginSuccess = loginProService.loginMember(memberBean);
		
		
		if(isLoginSuccess!=-1) {
		MemberInfoService memberInfoService = new MemberInfoService();
		memberBean =memberInfoService.getMemberInfo(memberBean); 
		}
		
		
		if(isLoginSuccess==1) {
			
			System.out.println(memberBean.getType());
			
			if(memberBean.getType().equals("미승인")){
					
				
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");			
				out.println("alert('관리자 미승인 상태입니다.')");
				out.println("alert('관리자 승인후 로그인 가능.')");
				out.println("history.back()");
				out.println("</script>");
			
			
			}else{
				
				HttpSession session = request.getSession();
				session.setAttribute("memberBean", memberBean);
				System.out.println("memberBean 가져오기 성공");
				forward = new ActionForward();
				forward.setPath("index.ma");
				
				
			}
		
			
		}else if(isLoginSuccess==0){
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호불일치')");
			out.println("history.back()");
			out.println("</script>");
			
	
		}else if(isLoginSuccess==-1){
			
			System.out.println("-1확인");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디없음')");
			out.println("history.back()");
			out.println("</script>");
			
		}
		
		return forward;
	}

}
