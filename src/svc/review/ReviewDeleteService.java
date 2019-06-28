package svc.review;

import java.sql.Connection;

import dao.ReviewDAO;

import static db.JdbcUtil.*;
public class ReviewDeleteService {
	public boolean removeArticle(int no) {
		boolean isRemoveSuccess = false;
		
		// Connection 객체 가져오기
		Connection con = getConnection();
		
		// BoardDAO 인스턴스 얻어오기 => setConnection() 메서드를 호출하여 Connection 객체 전달
		ReviewDAO reviewDAO = ReviewDAO.getinstance();
		reviewDAO.setConnection(con);
		
		// BoardDAO 클래스의 deleteArticle() 메서드를 호출하여 번호 전달 후 게시물 삭제 수행 => 삭제 성공 여부(deleteSuccess) int형으로 리턴받음
		int deleteSuccess = reviewDAO.deleteArticle(no);
		
		// 삭제 성공 여부 값(deleteSuccess) 가 0보다 크면 commit, isRemoveSuccess 를 true 로 변경
		if(deleteSuccess > 0) {
			commit(con);
			isRemoveSuccess = true;
		} else {
			// 0이면 rollback
			rollback(con);
		}
		
		// Connection 반환
		close(con);
		
		return isRemoveSuccess;
	}
}
