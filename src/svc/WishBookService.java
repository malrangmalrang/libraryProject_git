package svc;

import java.sql.Connection;

import dao.WishBookDAO;
import vo.WishBookBean;
import static db.JdbcUtil.*;


public class WishBookService {
	public boolean wishBookRegArticle(WishBookBean wishBookBean) throws Exception{
//		System.out.println("WishBookService");
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		WishBookDAO wishBookDAO = WishBookDAO.getinstance();
		wishBookDAO.setConnection(con);
		int insertCount = wishBookDAO.insertWishBookArticle(wishBookBean);
		
		if(insertCount > 0) {
			commit(con);
			isWriteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isWriteSuccess;
	}
}
