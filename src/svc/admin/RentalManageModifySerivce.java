package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;


public class RentalManageModifySerivce {

	public int RentalManageModify_Service(String modifyClass,String[] values) {
		Connection con=getConnection();
		dao.admin.RentalManageDAO rentalManageDAO=dao.admin.RentalManageDAO.getinstance();
		rentalManageDAO.setConnection(con);
		
		int isModifySuccess=rentalManageDAO.RentalManageModify_DAO(modifyClass  ,values);
		
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
