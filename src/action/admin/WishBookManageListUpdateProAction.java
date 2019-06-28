package action.admin;

import java.io.PrintWriter;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class WishBookManageListUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=null;
		HttpSession session=request.getSession();
		ArrayList wishBookUpdateList=(ArrayList)session.getAttribute("wishBookUpdateList");
		ArrayList wishBookUpdateListArray=new ArrayList();
		
		for(int i=0; i<wishBookUpdateList.size(); i++) {
			vo.admin.BookList bookListBean=new vo.admin.BookList();
			vo.admin.WishBookBean wishBookBean=(vo.admin.WishBookBean)wishBookUpdateList.get(i);
			bookListBean.setNo(0);
			bookListBean.setTitle(wishBookBean.getTitle());
			bookListBean.setAuthor(wishBookBean.getAuthor());
			bookListBean.setPublisher(wishBookBean.getPublisher());
			bookListBean.setPublish_date(wishBookBean.getPublish_date());
			bookListBean.setPrice(wishBookBean.getPrice());
			bookListBean.setIsbn(wishBookBean.getIsbn());
			bookListBean.setImage(wishBookBean.getImage());
			bookListBean.setStatus(request.getParameter(wishBookBean.getNo()+"status"));
			bookListBean.setKeyword1(request.getParameter(wishBookBean.getNo()+"keyword1"));
			bookListBean.setKeyword2(request.getParameter(wishBookBean.getNo()+"keyword2"));
			bookListBean.setKeyword3(request.getParameter(wishBookBean.getNo()+"keyword3"));
			bookListBean.setCategory(request.getParameter(wishBookBean.getNo()+"category"));
			wishBookUpdateListArray.add(bookListBean);
		}
		
		
		
		svc.admin.WishBookManageListUpdateProService wishBookManageListUpdateProService=new svc.admin.WishBookManageListUpdateProService();
		int isUpdateSuccess=wishBookManageListUpdateProService.wishBookMangeListUpdate_Service(wishBookUpdateList,wishBookUpdateListArray);
		
		if(isUpdateSuccess==1) {
			forward=new ActionForward();
			forward.setPath("wishBookManageList.wm");
			forward.setRedirect(true);
			
		}
		else if(isUpdateSuccess==0) {
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
