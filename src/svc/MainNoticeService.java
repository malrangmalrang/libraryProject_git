package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import dao.MainDAO;
import vo.BoardBean;

public class MainNoticeService {

	public JSONArray getRecentNoticeList() {
		Connection con = getConnection();
		
		MainDAO mainDAO = MainDAO.getInstance();
		mainDAO.setConnection(con);
		
		JSONArray noticeList = mainDAO.getRecentNoticeList();
		
		close(con);
		
		return noticeList;
	}


}
