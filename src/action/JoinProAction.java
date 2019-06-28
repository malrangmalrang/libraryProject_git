package action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.JoinProService;

import vo.ActionForward;
import vo.MemberBean;

public class JoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		MemberBean memberBean = null;
		
		String realFolder = "";
		String saveFolder = "/images/member/";
		int fileSize = 5 * 1024 * 1024;
		
		ServletContext context = request.getServletContext(); // 현재 서블릿 컨텍스트 객체 얻어오기
		realFolder = context.getRealPath(saveFolder); // 가상의 경로에 해당하는 실제 경로 얻어오기
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		memberBean = new MemberBean();

		memberBean.setEmail(multi.getParameter("email"));
		memberBean.setPassword(multi.getParameter("password"));
		memberBean.setName(multi.getParameter("name"));
		memberBean.setGender(multi.getParameter("Gender"));
		memberBean.setBirth(multi.getParameter("birth"));
		memberBean.setPhone(multi.getParameter("phone"));
		memberBean.setImage(multi.getParameter("imgad"));
		memberBean.setAddress1(multi.getParameter("address1"));
		memberBean.setAddress2(multi.getParameter("address2"));
		memberBean.setPostcode(Integer.parseInt(multi.getParameter("postcode")));
		
		System.out.println("회원가입시이미지 :"+memberBean.getImage());

		JoinProService joinProService = new JoinProService();

		boolean isInsertSuccess = joinProService.joinMember(memberBean);
		System.out.println(isInsertSuccess);

		if (!isInsertSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입실패')");
			out.println("history.back()");
			out.println("</script>");

		} else {
			forward = new ActionForward();
			forward.setPath("loginForm.me");
			forward.setRedirect(true);
		}
		return forward;
	}

}
