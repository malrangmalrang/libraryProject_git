package svc;


import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class EmailCheckService {
	
	

	public int emailCheck(MemberBean memberBean) {
	
		int ischeck;
		
		Connection con=getConnection();
		MemberDAO memberDAO=MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		ischeck=memberDAO.emailCheck(memberBean);
		
		
		
		
		close(con);
		
		return ischeck;
	}
	
	 

}
