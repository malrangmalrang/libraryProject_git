package action.admin;

import java.io.PrintWriter;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;
import vo.PageInfo;

public class MemberManageModifyMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=null;
		String Class=request.getParameter("Class");
		System.out.println("수정할때 받은 Class값 :"+Class);
		



		String update_type=request.getParameter("update_type");
		
		String [] values=request.getParameterValues("check");
		



		

		
		svc.admin.MemberManageModifyMemberService  memberManageModifyMemberService=new svc.admin.MemberManageModifyMemberService();
		
		int isModifySucces=memberManageModifyMemberService.memberManageModifyMember_Service(values, update_type);
		
		if(isModifySucces==1) {
			int page = 1;
			int limit = 10;
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			
			
			
			forward=new ActionForward();
			svc.admin.MemberManageListService memberManageListService=new svc.admin.MemberManageListService();
			
			int listCount=memberManageListService.getListCount(Class);
			
			
			
			ArrayList memeberList=memberManageListService.getMemberList_Service(page, limit,Class);
			int maxPage = (int)((double)listCount / limit + 0.95); // 총 페이지 수 계산(올림처리를 위해 + 0.95)
			int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1; // 현재 페이지에 표시할 시작 페이지) 번호
			int endPage = startPage + 10 - 1; // 현재 페이지에 표시할 마지막 페이지 번호
			
			if(endPage > maxPage) { // 마지막 페이지 번호가 최대 페이지 번호보다 클 경우
				endPage = maxPage; // 마지막 페이지 번호를 최대 페이지 번호로 대체
			}
			
			// 페이지 번호 관련 정보를 PageInfo 객체에 저장
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPage(page);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setStartPage(startPage);
			pageInfo.setEndPage(endPage);
			pageInfo.setListCount(listCount);
			
			
			request.setAttribute("memberList", memeberList);
			request.setAttribute("pageInfo", pageInfo);
			forward.setPath("./admin/page_admin_member.jsp?Class="+Class);


		}
		
		else if(isModifySucces==0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('변경실패')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		}
		
		
		
		
		return forward;
	}

}
