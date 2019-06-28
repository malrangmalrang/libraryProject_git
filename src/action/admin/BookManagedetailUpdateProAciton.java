package action.admin;

import java.io.PrintWriter;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class BookManagedetailUpdateProAciton implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		ActionForward forward=null;
		request.setCharacterEncoding("UTF-8");
		int no=Integer.parseInt(request.getParameter("no"));
//		String title=request.getParameter("title");
//		String author=request.getParameter("author");
//		String publisher=request.getParameter("publisher");
//		Date publish_date=Date.valueOf(request.getParameter("publish_date"));
//		int price=Integer.parseInt(request.getParameter("price"));
//		String isbn=request.getParameter("isbn");
//		String image=request.getParameter("image");
		String status=request.getParameter("status");
		String keyword1=request.getParameter("keyword1");
		String keyword2=request.getParameter("keyword2");
		String keyword3=request.getParameter("keyword3");
		String category=request.getParameter("category");
		String rent_code=request.getParameter("rent_code");
		String bar_code=request.getParameter("bar_code");
//		Date reg_date=Date.valueOf(request.getParameter("reg_date"));
		
		
		
		
		vo.admin.BookList bookListVo=new vo.admin.BookList();
		bookListVo.setNo(no);
//		bookListVo.setTitle(title);
//		bookListVo.setAuthor(author);
//		bookListVo.setPublisher(publisher);
//		bookListVo.setPublish_date(publish_date);
//		bookListVo.setPrice(price);
//		bookListVo.setIsbn(isbn);
//		bookListVo.setImage(image);
		bookListVo.setStatus(status);
		bookListVo.setKeyword1(keyword1);
		bookListVo.setKeyword2(keyword2);
		bookListVo.setKeyword3(keyword3);
		bookListVo.setCategory(category);
		bookListVo.setRent_code(rent_code);
		bookListVo.setBar_code(bar_code);
//		bookListVo.setReg_date(reg_date);

		
		
        svc.admin.BookManagedetailUpdateProService bookManagedetailUpdateProService=new svc.admin.BookManagedetailUpdateProService();
	    int updaetSuccess=bookManagedetailUpdateProService.bookManagedetailUpdate_Service(bookListVo);
		
	    if(updaetSuccess==1) {
	    	response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('수정성공')"); // 오류 메세지 다이얼로그 표시
			out.println("window.close()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 태그
	    }
	    
	    else if(updaetSuccess==0) {
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
