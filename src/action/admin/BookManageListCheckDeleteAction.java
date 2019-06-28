package action.admin;

import java.io.PrintWriter;


import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.txw2.Document;

import action.Action;
import vo.ActionForward;



public class BookManageListCheckDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		ArrayList bookList=(ArrayList)request.getAttribute("bookList");
		System.out.println("book체크딜리트액션");
		
		
		String[] checkList=request.getParameterValues("check");

		
		svc.admin.BookManageListCheckDeleteService bookManageListCheckDeleteSerivce=new svc.admin.BookManageListCheckDeleteService();
		int checkDelete=bookManageListCheckDeleteSerivce.bookManageListCheckDelete_Service(checkList);
		
		if(checkDelete==1) {
			forward=new ActionForward();
			forward.setPath("bookManageList.bm");
			forward.setRedirect(true);
		}
		
		else if(checkDelete==0) {
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
