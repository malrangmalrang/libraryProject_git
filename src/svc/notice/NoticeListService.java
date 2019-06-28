package svc.notice;

import java.sql.Connection;

import java.util.List;

import dao.NoticeBoardDAO;

import static db.JdbcUtil.*;
import vo.BoardBean;

public class NoticeListService {
	
	
	public int getListCount() throws Exception {

		
		int listCount = 0; 
		
		Connection con = getConnection();
		
		NoticeBoardDAO boardDAO = NoticeBoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		listCount = boardDAO.selectListCount();
		
		System.out.println("게시물 갯수 : " + listCount);
		
		close(con);
		
		return listCount;
	}
	
	public List<BoardBean> getArticleList(int page, int limit) throws Exception {
		
		List<BoardBean> articleList = null;
		
		Connection con = getConnection();
		
		NoticeBoardDAO boardDAO = NoticeBoardDAO.getInstance();
	    boardDAO.setConnection(con);
		
		

		articleList = boardDAO.selectArticleList(page, limit);

		close(con);
		
		return articleList;
	}
	
	public int getListCount(String search) throws Exception {

		
		int listCount = 0; 
		
		Connection con = getConnection();
		
		NoticeBoardDAO boardDAO = NoticeBoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		listCount = boardDAO.selectListCount(search);
		
		System.out.println("게시물 갯수 : " + listCount);
		
		close(con);
		
		return listCount;
	}
	
	public List<BoardBean> getArticleList(int page, int limit,String search) throws Exception {
		
		List<BoardBean> articleList = null;
		
		Connection con = getConnection();
		
		NoticeBoardDAO boardDAO = NoticeBoardDAO.getInstance();
	    boardDAO.setConnection(con);
		
		

		articleList = boardDAO.selectArticleList(page, limit,search);

		close(con);
		
		return articleList;
	}
	
	}





















