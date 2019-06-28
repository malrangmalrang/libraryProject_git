package svc;


import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class EmailFindService {
	
	
	public MemberBean emailFind(MemberBean memberBean) {
		
		
		//String name;
		int isCheck;
		
		System.out.println(memberBean.getPhone());
		
		Connection con=getConnection();
		MemberDAO memberDAO=MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		MemberBean memberBean2=memberDAO.emailFind(memberBean);
		
		
		close(con);
		return memberBean2;
		
		
		
	}

}
