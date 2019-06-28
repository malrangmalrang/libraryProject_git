package svc.notice;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.NoticeBoardDAO;
import vo.BoardBean;

public class NoticeWriteProService {

	public int writeNotice_Service(BoardBean boardBean) {
		Connection con=getConnection();
		NoticeBoardDAO boardDAO=NoticeBoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int writeSuccess=boardDAO.writeNotice_DAO(boardBean);
		
		if(writeSuccess==1) {
			commit(con);
		}
		
		else if(writeSuccess==0)
		{
			rollback(con);
		}
		close(con);
		
		
		return writeSuccess;
	}

}
