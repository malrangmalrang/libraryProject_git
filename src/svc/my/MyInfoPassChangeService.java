package svc.my;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MyDAO;

public class MyInfoPassChangeService {

	public int updatePass(int memberNo, String nowPass, String newPass) {
		
		Connection con = getConnection();
		
		MyDAO myDAO = MyDAO.getInstance();
		myDAO.setConnection(con);
		
		int updateCount = myDAO.updatePass(memberNo, nowPass, newPass);
		
		if(updateCount == 1) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return updateCount;
	}

}
