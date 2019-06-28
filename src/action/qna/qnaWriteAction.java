package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.MemberInfoService;
import vo.ActionForward;
import vo.MemberBean;

public class qnaWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
		String email = memberBean.getEmail();
		
		memberBean.setEmail(email);
		MemberInfoService memberInfoService = new MemberInfoService();
		memberBean = memberInfoService.getMemberInfo(memberBean);
		
		request.setAttribute("memberBean", memberBean);
		forward = new ActionForward();
		forward.setPath("qnaWriteForm.bo");
		return forward;
	}

}
