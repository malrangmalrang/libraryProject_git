package action.review;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.review.ReviewWriteProService;
import vo.ActionForward;
import vo.ReviewBean;
import vo.MemberBean;
import action.Action;

public class ReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isPublic = request.getParameter("isPublic");
		int rental_no = Integer.parseInt(request.getParameter("rentalNo"));
		
		ReviewBean reviewBean = new ReviewBean();
		reviewBean.setTitle(title);
		reviewBean.setContent(content);
		reviewBean.setRental_no(rental_no);
		reviewBean.setIspublic(isPublic);
		
		ReviewWriteProService reviewWriteProService  = new ReviewWriteProService();
		boolean isWriteSuccess=reviewWriteProService.insertArticle(reviewBean);
		
		if(isWriteSuccess) {
			forward = new ActionForward();
			forward.setPath("reviewList.rv");
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
