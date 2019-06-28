package action.notice;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.notice.NoticeWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class NoticeWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeWriteProAction");
		ActionForward forward=null;
		BoardBean boardBean=null;
		String realFolder="";
		String saveFolder="/images/notice";
		int fileSize=10*1024*1024;
		ServletContext context=request.getServletContext();
		realFolder=context.getRealPath(saveFolder);
		MultipartRequest multi= new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		
//		HttpSession session=request.getSession(); 
//		MemberBean mb=(MemberBean)session.getAttribute("mb");
		boardBean =new BoardBean();
//	    boardBean.setMember_no(Integer.parseInt(multi.getParameter("member_no")));
	    boardBean.setTitle(multi.getParameter("title"));
	    boardBean.setContent(multi.getParameter("content"));
//	    boardBean.setFile((String)multi.getFileNames().nextElement());
	    boardBean.setFile(multi.getFilesystemName("file"));
//	    boardBean.setMember_no(mb.getNo());
	    boardBean.setMember_no(10);
	    
	    NoticeWriteProService noticeWriteProService=new NoticeWriteProService();
	    int writeSuccess=noticeWriteProService.writeNotice_Service(boardBean);
	    System.out.println(writeSuccess);
	    if(writeSuccess==1) {
	    	forward=new ActionForward();
	    	forward.setPath("noticeList.bo");
	    	forward.setRedirect(true);
	    }
	    
	    else if(writeSuccess==0){
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
