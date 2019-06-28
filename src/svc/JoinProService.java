package svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.JdbcUtil.*;


import vo.MemberBean;

public class JoinProService {
	
	public boolean joinMember(MemberBean memberBean) {
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		
		int insertCount = memberDAO.insertMember(memberBean);
		
		//DAO 클래스의 insertMember() 메서드를 호출하여 MemberBean 객체 전달 후 INSERT 구문 실행하여 레코드 추가
		
		boolean isInsertSuccess = false;
		
		if(insertCount > 0) {
			isInsertSuccess = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		
		close(con);
		
		return isInsertSuccess;
		
		
	}
	
	
	

}
