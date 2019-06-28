package svc.admin;

import java.sql.Connection;

import java.util.ArrayList;


import static db.JdbcUtil.*;
public class WishBookManageListDetailService {

	public ArrayList getWishBookDetail_Service(String bookTitle, String bookStatus) {
		Connection con=getConnection();
		dao.admin.WishBookManageDAO wishBookManageDAO=dao.admin.WishBookManageDAO.getinstance();
		wishBookManageDAO.setConnection(con);
		ArrayList wishBookDetailList=wishBookManageDAO.getWishBookDetail_DAO(bookTitle,bookStatus);
		close(con);
		return wishBookDetailList;
	}

}
