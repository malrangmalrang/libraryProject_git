package action.qna;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.BoardModifyProService;
import vo.ActionForward;
import vo.BoardBean;
import vo.MemberBean;

public class QnaModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String realFolder = ""; // 실제 경로
		String saveFolder = "/images/qna";
		int fileSize = 5 * 1024 * 1024; // 파일 사이즈(5MB)
		
		ServletContext context = request.getServletContext(); // 현재 서블릿 컨텍스트 객체 얻어오기
		
		realFolder = context.getRealPath(saveFolder); // 가상의 경로에 해당하는 실제 경로 얻어오기
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		int board_num = Integer.parseInt(multi.getParameter("board_num"));
		System.out.println("QnaModifyProAction - board_num = "+board_num);
		HttpSession session = request.getSession();
		MemberBean memberBean= (MemberBean) session.getAttribute("memberBean");
		String email = memberBean.getEmail();
//		int nowPage = Integer.parseInt(request.getParameter("page"));
		BoardModifyProService boardModifyProService = new BoardModifyProService();
		boolean isRightUser = boardModifyProService.isArticleWriter(board_num, email);
		if(!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('수정할 권한이 없습니다.')"); // 오류 메세지 다이얼로그 표시
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
		}else {
			BoardBean article = new BoardBean();
			article.setTitle(multi.getParameter("board_subject"));
			article.setContent(multi.getParameter("board_content"));
//			article.setFile(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
			article.setNo(Integer.parseInt(multi.getParameter("board_num")));
			
			boolean isModifySuccess = boardModifyProService.ModifyArticle(article);
			
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>"); // 자바스크립트 시작 태그
				out.println("alert('수정 실패!')"); // 오류 메세지 다이얼로그 표시
				out.println("history.back()"); // 이전 페이지로 돌아가기
				out.println("</script>"); // 자바스크립트 종료 태그
			}else {
				forward = new ActionForward();
				System.out.println("글 수정 성공!");
				// 글 수정 성공 시
				// boardDetail.bo 서블릿 주소로 포워딩 => 주소 뒤에 파라미터로 글번호(board_num) 전달 => Redirect 방식
//				forward.setPath("qnaDetail.bo?board_num=" + board_num+"&page="+nowPage);
				forward.setPath("qnaDetail.bo?board_num=" + board_num);
				forward.setRedirect(true);
			}
		}
		return forward;
	}

}
