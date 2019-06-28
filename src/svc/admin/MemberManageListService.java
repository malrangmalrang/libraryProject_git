package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;


public class MemberManageListService {

	public ArrayList getMemberList_Service(int page, int limit,String Class) {
		Connection con=getConnection();
	    dao.admin.MemberManageDAO memeberManageDAO=dao.admin.MemberManageDAO.getinstance();
	    memeberManageDAO.setConnection(con);
	    
	    ArrayList memberList=memeberManageDAO.getMemberList_DAO(page,limit,Class);
	    
	    close(con);
	    
		return memberList;
	}

	public int getListCount(String Class) {
		int listCount = 0;
		Connection con = getConnection();
		   dao.admin.MemberManageDAO memeberManageDAO=dao.admin.MemberManageDAO.getinstance();
		    memeberManageDAO.setConnection(con);
		    
		    listCount=memeberManageDAO.getListCount_DAO(Class);
		    close(con);
		return listCount;
	}
	

}
