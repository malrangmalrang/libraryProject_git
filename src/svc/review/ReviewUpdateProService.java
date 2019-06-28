package svc.review;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.ReviewBean;

import static db.JdbcUtil.*;

public class ReviewUpdateProService {
	public boolean isArticleWriter(int no) throws Exception {
		System.out.println("ReviewUpdateProService - isArticleWriter()");
		boolean isArticleWriter = false;
		
		// Connection 객체 가져오기
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getinstance();
		reviewDAO.setConnection(con);

		isArticleWriter = reviewDAO.isArticleReviewWriter(no); // 본인 확인
		
		close(con);
		
		return isArticleWriter;
	}
	public boolean updateArticle(ReviewBean article) {
		boolean isModifySuccess = false;
		
		// DB 작업 전 DB 접속을 위해 JdbcUtil 클래스의 static 메서드 getConnection() 를 호출하여 DB 접속
		Connection con = getConnection();
		
		// 싱글톤 디자인 패턴으로 생성된 BoardDAO 인스턴스를 얻어오기
		ReviewDAO reviewDAO = ReviewDAO.getinstance();
		reviewDAO.setConnection(con); // Connection 객체를 boardDAO 객체에 전달
		int updateCount = reviewDAO.updateArticle(article); // 글 수정 처리(결과를 int형으로 전달받음)
		
		// insertCount 가 0보다 크면 트랜잭션 Commit, 아니면 트랜잭션 Rollback 수행
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true; // 성공 표시
		} else {
			rollback(con);
		}
		
		// DB 접속 해제(Connection 자원 반환)
		close(con);
		
		return isModifySuccess;
	}
}
