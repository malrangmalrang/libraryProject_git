package svc.my;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MyDAO;

public class MyInfoProfilProService {

	public boolean updateProfilImage(int member_no, String image) {
		Connection con = getConnection();
		
		MyDAO myDAO = MyDAO.getInstance();
		myDAO.setConnection(con);
		
		int updateCount = myDAO.updateProfilImage(member_no, image);
		boolean isUpdate = false;
		
		if(updateCount == 1) {
			isUpdate = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isUpdate;
	}

}
