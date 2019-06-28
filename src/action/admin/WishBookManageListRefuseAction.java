package action.admin;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class WishBookManageListRefuseAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=null;
		String[] values=request.getParameterValues("wishcheck");
		System.out.println("refuse에서 가져온 체크값");
		
		svc.admin.WishBookManageListRefuseService wishBookManageListRefuseService=new svc.admin.WishBookManageListRefuseService();
		int isRefuseSuccess=wishBookManageListRefuseService.wishBookManageListRefuse_Service(values);
		
		if(isRefuseSuccess==1) {
			forward=new ActionForward();
			forward.setPath("wishBookManageList.wm");
			forward.setRedirect(true);
		}
		
		else if(isRefuseSuccess==0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('수정실패')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		}
		
		return forward;
	}

}
