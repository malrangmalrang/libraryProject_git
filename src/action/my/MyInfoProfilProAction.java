package action.my;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.my.MyInfoProfilProService;
import vo.ActionForward;
import vo.MemberBean;

public class MyInfoProfilProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean)session.getAttribute("memberBean");
		int member_no = memberBean.getNo();
		
		// 파일 이름 추출
		String uploadPath = request.getRealPath("/images/member");
		int fileSize = 5 *1024 * 1024;
		String fileName = "";
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "utf-8", new DefaultFileRenamePolicy());
			
			Enumeration files = multi.getFileNames();
			
			String file = (String)files.nextElement();
			fileName = multi.getFilesystemName(file);
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		MyInfoProfilProService myInfoProfilProService = new MyInfoProfilProService();
		
		boolean isUpdate = myInfoProfilProService.updateProfilImage(member_no, fileName);
		
		ActionForward forward = null;
		
		if(isUpdate) {
			memberBean.setImage(fileName);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('업로드 성공')");
			out.println("opener.window.location.reload();");
			out.println("self.close();");
			out.println("</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('업로드 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
