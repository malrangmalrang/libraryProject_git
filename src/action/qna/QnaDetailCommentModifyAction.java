package action.qna;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;
import vo.MemberBean;

public class QnaDetailCommentModifyAction implements Action {
		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("QnaDetailAction 왔당");
//			ActionForward forward = null;
			System.out.println("QnaDetailAction -1");
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			System.out.println("QnaDetailAction 0");
			int page;
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
			}else {
				page=1;
			}
			
			BoardDetailService boardDetailService = new BoardDetailService();
			ArrayList beans = boardDetailService.getArticle(board_num);
			BoardBean article = (BoardBean)beans.get(0);
			MemberBean memberBean = (MemberBean)beans.get(1);
			ArrayList commentList = (ArrayList) beans.get(2);
			
			request.setAttribute("page", page);
			request.setAttribute("article", article);
			request.setAttribute("memberBean", memberBean);
			request.setAttribute("commentList", commentList);
			
			ActionForward forward = new ActionForward();
			forward.setPath("/center/qna/qna_Detail_Comment_Modify.jsp");
			
			return forward;
		}

	}
