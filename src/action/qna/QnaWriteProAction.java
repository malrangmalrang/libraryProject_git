package action.qna;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.MemberInfoService;
import svc.QnaWriteProService;
import vo.ActionForward;
import vo.BoardBean;
import vo.MemberBean;

public class QnaWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaWriteProAction - execute");
		ActionForward forward = null;
		BoardBean boardBean = null;
		String realFolder = "";
		String saveFolder = "/images/qna";
		int fileSize = 10 * 1024 * 1024;
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize,"UTF-8", new DefaultFileRenamePolicy());
		System.out.println("QnaWriteProAction - Multipart Request 성공");
		boardBean = new BoardBean();
		boardBean.setBoard_type("qna");
		boardBean.setTitle(multi.getParameter("board_subject"));
		boardBean.setContent(multi.getParameter("board_content"));
//		boardBean.setFile(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
//		boardBean.setEmail_reply(Integer.parseInt(multi.getParameter("email_reply")));
		QnaWriteProService qnaWriteProService = new QnaWriteProService();
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
//		memberBean.setEmail(multi.getParameter("email"));
//		System.out.println("QnaWriteProAction-mult.getParameter email : "+multi.getParameter("email"));
		MemberInfoService memberInfoService = new MemberInfoService();
		memberBean = memberInfoService.getMemberInfo(memberBean);
		
//		request.setAttribute("memberBean", memberBean);
		System.out.println("QnaWriteProAction.java - setAttribute");
		boolean isWriteSuccess = qnaWriteProService.registArticle(boardBean, memberBean);
		System.out.println("QnaWriteProAction.java - isWriteSuccess");
		if(isWriteSuccess) {
			forward = new ActionForward();
			forward.setPath("qnaList.bo");
			forward.setRedirect(true);
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('게시물 등록 실패!')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		}
		return forward;
	}

}
