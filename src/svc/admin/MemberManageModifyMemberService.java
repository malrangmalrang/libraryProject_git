package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;


public class MemberManageModifyMemberService {

	public int memberManageModifyMember_Service(String[] values, String update_type) {
		
		int isModifySuccess=0;
		
		Connection con=getConnection();
		dao.admin.MemberManageDAO memberManageDAO=dao.admin.MemberManageDAO.getinstance();
		memberManageDAO.setConnection(con);
		
	    isModifySuccess=memberManageDAO.memberManageModifyMember_DAO(values, update_type);
	    
		
	    if(isModifySuccess==1) {
	    	commit(con);
	    }
	    
	    else if(isModifySuccess==0) {
	    	rollback(con);
	    }
		
		close(con);
		return isModifySuccess;
	}

}
