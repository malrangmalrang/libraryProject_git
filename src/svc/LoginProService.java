package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class LoginProService {
	
	
		
		public int loginMember(MemberBean memberBean) {
			
			Connection con=getConnection();
			MemberDAO memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			
			int isRightUser = memberDAO.isRightUser(memberBean);
			close(con);
				
			
			System.out.println("이즈라이트유저값을"+isRightUser);
			
			return isRightUser;
		}

		
	
	

}
