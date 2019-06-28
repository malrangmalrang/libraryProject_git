package action.admin;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class BookManagedetailDeleteAciton implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=null;
		int no=Integer.parseInt(request.getParameter("no"));
		System.out.println(no);
		
		svc.admin.BookManagedetailDeleteService bookManagedetailDeleteService=new svc.admin.BookManagedetailDeleteService();
		
		int deleteSucces=bookManagedetailDeleteService.bookManagedetailDelete_Service(no);
		
		if(deleteSucces==1) {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('삭제성공')"); // 오류 메세지 다이얼로그 표시
			out.println("window.close()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그

		}
		
		else if(deleteSucces==0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('삭제실패')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		}
		
		
		
		return forward;
	}

}
