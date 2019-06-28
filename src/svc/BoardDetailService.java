package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardBean;
import vo.MemberBean;

import static db.JdbcUtil.*;
public class BoardDetailService {
	public ArrayList getArticle(int board_num) throws Exception{
		System.out.println("BoardDetailService getArticle 왔당");
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		ArrayList beans = boardDAO.selectArticle(board_num);
		ArrayList list = (ArrayList) beans.get(2);
		ArrayList<MemberBean> commentMemberList = (ArrayList) list.get(1);
		
		for(int i=0;i<commentMemberList.size();i++) {
			System.out.println("commentMember : "+commentMemberList.get(i).getNo());
		}
//		ArrayList commentMemberList = (ArrayList) list.get(1);
		int updateCount = boardDAO.updateReadCount(board_num);
		
		if(updateCount == 1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return beans;
	}
}
