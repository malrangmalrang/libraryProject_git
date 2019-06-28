package svc;

import java.sql.Connection;

import dao.BoardDAO;
import dao.CommentDAO;
import dao.MemberDAO;
import vo.BoardBean;
import vo.CommentBean;
import vo.MemberBean;

import static db.JdbcUtil.*;
public class QnaCommentProService {

	public boolean registComment(CommentBean comment, String email) {
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		MemberBean memberBean = memberDAO.selectMemberBean(email);
		
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(con);
		
		int insertCount = commentDAO.insertComment(comment, memberBean);
		if(insertCount>0) {
			
			commit(con);
			isWriteSuccess = true; // 성공 표시
		} else {
			rollback(con);
		}
		close(con);
		return isWriteSuccess;
		
	}

}
