package action.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.QnaDeleteProService;
import vo.ActionForward;
import vo.MemberBean;

public class QnaDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaDeleteProAction");
		//ActionForward 인스턴스 생성
		ActionForward forward = null;
		
		//게시물 번호 가져오기
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
		String email = memberBean.getEmail();
		QnaDeleteProService qnaDeleteProService = new QnaDeleteProService();
		String member_pass = request.getParameter("member_pass");
		//isArticleWriter() 메서드 호출하여 본인확인(파라미터로 글번호, 입력받은 패스워드 전달)
		boolean isArticleWriter = qnaDeleteProService.isArticleWriter(board_num, email);
		boolean isConfirmed = qnaDeleteProService.isConfirmByPass(email, member_pass);
		
		System.out.println("QnaDeleteProAction - isArticleWriter : "+isArticleWriter);
		System.out.println("QnaDeleteProAction - isConfirmed : "+isConfirmed);
		
		if(!isArticleWriter) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");// 자바스크립트 시작 태그
			out.println("alert('삭제할 권한이 없습니다.')");// 오류 메세지 다이얼로그 표시
			out.println("history.back()");// 이전 페이지로 돌아가기
			out.println("</script>");// 자바스크립트 종료 태그
		}else if(!isConfirmed){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");// 자바스크립트 시작 태그
			out.println("alert('비밀번호가 틀렸습니다.')");// 오류 메세지 다이얼로그 표시
			out.println("history.back()");// 이전 페이지로 돌아가기
			out.println("</script>");// 자바스크립트 종료 태그
		}else {
			//본인일 경우 BoardDeleteProService 클래스의 removeArticle() 메서드를 호출하여 글 삭제 수행(매개변수로 글 번호 전달) - boolean 타입 리턴
			boolean isDeleteSuccess = qnaDeleteProService.removeArticle(board_num);
			//글 삭제 여부 판별
			//실패 시 자바스크립트 - "삭제실패!" 출력
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");// 자바스크립트 시작 태그
				out.println("alert('삭제실패!')");// 오류 메세지 다이얼로그 표시
				out.println("history.back()");// 이전 페이지로 돌아가기
				out.println("</script>");// 자바스크립트 종료 태그				
			}
			//성공시 ActionForward 객체 생성, "boardList.bo"로 포워딩시 파라미터로 page(form태그 hidden으로 전달된 파라미터) 전달
			else {
				forward = new ActionForward();
				forward.setPath("./qnaList.bo?page="+request.getParameter("page"));
				forward.setRedirect(true);//Redirect 방식
			}
			
		}
		return forward;
	}

}
