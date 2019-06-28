package svc.review;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewDAO;
import vo.ReviewBean;


public class ReviewListService {
	private String searchType;
	private String searchWord;
	private String category;
	private int page;
	private int limit;
	public ReviewListService(String searchType, String searchWord, String category, int page, int limit) {
		this.searchType = searchType;
		this.searchWord = searchWord;
		this.category = category;
		this.page = page;
		this.limit = limit;
	}
	public ReviewListService() {
		// TODO Auto-generated constructor stub
	}
	public int getListCount() throws Exception{
		System.out.println("ReviewListService - getListCount");
		int listCount = 0;
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getinstance();
		reviewDAO.setConnection(con);
		
		
//		if (category.isEmpty()) {
//			if (searchWord.isEmpty()) { // 전체 검색
//				listCount = reviewDAO.selectCount();
//			} else { // 단어 검색
//				listCount = reviewDAO.selectCount(searchType, searchWord);
//			}
//		} else { // 카테고리 검색
//			if (searchWord.isEmpty()) { // 전체 검색
////				listCount = reviewDAO.selectCount(category);
//			} else { // 단어 검색
////				listCount = reviewDAO.selectCount(category, searchType, searchWord);
//			}
//		}
		
		listCount = reviewDAO.selectCount();
		
		System.out.println("게시물 갯수 : " + listCount);
		
		close(con);
		
		return listCount;
	}
	public int getListCount(String option, String keyword) {
		System.out.println("ReviewListService - getListCount");
		int listCount = 0;
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getinstance();
		reviewDAO.setConnection(con);
		
		listCount = reviewDAO.selectCount(option, keyword);
		
		System.out.println("게시물 갯수 : " + listCount);
		
		close(con);
		
		return listCount;
	}
	
	public ArrayList getArticleList(int page,int limit) throws Exception{
		System.out.println("ReviewListService - getArticleList");
		ArrayList articleList = null;
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getinstance();
		reviewDAO.setConnection(con);
		
//		if (category.isEmpty()) {
//			if (searchWord.isEmpty()) { // 전체 검색
//				articleList = reviewDAO.selectArticleList(page, limit);
//			} else { // 단어 검색
//				articleList = reviewDAO.selectArticleList(page, limit, searchType, searchWord);
//			}
//		} else { // 카테고리 검색
//			if (searchWord.isEmpty()) { // 전체 검색
////				articleList = reviewDAO.selectArticleList(page, limit, category);
//			} else { // 단어 검색
//				
////				articleList = reviewDAO.selectArticleList(page, limit, category, searchType, searchWord);
//			}
//		}
		
		articleList = reviewDAO.selectArticleList(page, limit);
		
		close(con);
		
		return articleList;
	}
	public ArrayList getArticleList(int page, int limit, String option, String keyword) {
		System.out.println("ReviewListService - getArticleList(keyword)");
		ArrayList articleList = null;
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getinstance();
		reviewDAO.setConnection(con);
		
		articleList = reviewDAO.selectArticleList(page, limit, option, keyword);
		
		close(con);
		
		return articleList;
		}


}
