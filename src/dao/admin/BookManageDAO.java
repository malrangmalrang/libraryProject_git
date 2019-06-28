package dao.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class BookManageDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private BookManageDAO bookManageDAO;
	
	
	private BookManageDAO() {}
	
	public static BookManageDAO getinstance() {
		if(bookManageDAO==null) {
			bookManageDAO=new BookManageDAO();
		}
		return bookManageDAO;
	}
	
	public void setConnection(Connection con) {
		this.con=con;
	}

	public  ArrayList getBookList(int page, int limit, String Class) {
		ArrayList bookList=new ArrayList();
		int startRow = (page - 1) * 10;
		
		
		if(Class.equals("All")) {
		String sql="Select * from book order by reg_date desc LIMIT ?,10";
		
	    try {
			System.out.println("테스트, startrow :"+startRow);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				vo.admin.BookList bookListVo=new vo.admin.BookList();
				bookListVo.setNo(rs.getInt(1));
				bookListVo.setTitle(rs.getString(2));
				bookListVo.setAuthor(rs.getString(3));
				bookListVo.setPublisher(rs.getString(4));
				bookListVo.setPublish_date(rs.getDate(5));
				bookListVo.setPrice(rs.getInt(6));
				bookListVo.setIsbn(rs.getString(7));
				bookListVo.setImage(rs.getString(8));
				bookListVo.setStatus(rs.getString(9));
				bookListVo.setKeyword1(rs.getString(10));
				bookListVo.setKeyword2(rs.getString(11));
				bookListVo.setKeyword3(rs.getString(12));
				bookListVo.setCategory(rs.getString(13));
				bookListVo.setRent_code(rs.getString(14));
				bookListVo.setBar_code(rs.getString(15));
				bookListVo.setReg_date(rs.getDate(16));
				
				bookList.add(bookListVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		}
		
		else {
			String sql="Select * from book WHERE rent_code LIKE ? order by reg_date desc LIMIT ?,10";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, Class+"%");
				pstmt.setInt(2, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.BookList bookListVo=new vo.admin.BookList();
					bookListVo.setNo(rs.getInt(1));
					bookListVo.setTitle(rs.getString(2));
					bookListVo.setAuthor(rs.getString(3));
					bookListVo.setPublisher(rs.getString(4));
					bookListVo.setPublish_date(rs.getDate(5));
					bookListVo.setPrice(rs.getInt(6));
					bookListVo.setIsbn(rs.getString(7));
					bookListVo.setImage(rs.getString(8));
					bookListVo.setStatus(rs.getString(9));
					bookListVo.setKeyword1(rs.getString(10));
					bookListVo.setKeyword2(rs.getString(11));
					bookListVo.setKeyword3(rs.getString(12));
					bookListVo.setCategory(rs.getString(13));
					bookListVo.setRent_code(rs.getString(14));
					bookListVo.setBar_code(rs.getString(15));
					bookListVo.setReg_date(rs.getDate(16));
					bookList.add(bookListVo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
		}
		
		
		return bookList;
	}//getBookList()

	
	public vo.admin.BookList getBookManageListDetail_DAO(int no) {
		vo.admin.BookList bookListVo=new vo.admin.BookList();
		String sql="select * from book where no=? ";
		
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			rs.next();
			
			bookListVo.setNo(rs.getInt(1));
			bookListVo.setTitle(rs.getString(2));
			bookListVo.setAuthor(rs.getString(3));
			bookListVo.setPublisher(rs.getString(4));
			bookListVo.setPublish_date(rs.getDate(5));
			bookListVo.setPrice(rs.getInt(6));
			bookListVo.setIsbn(rs.getString(7));
			bookListVo.setImage(rs.getString(8));
			bookListVo.setStatus(rs.getString(9));
			bookListVo.setKeyword1(rs.getString(10));
			bookListVo.setKeyword2(rs.getString(11));
			bookListVo.setKeyword3(rs.getString(12));
			bookListVo.setCategory(rs.getString(13));
			bookListVo.setRent_code(rs.getString(14));
			bookListVo.setBar_code(rs.getString(15));
			bookListVo.setReg_date(rs.getDate(16));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		   close(rs);
		   close(pstmt);
		}
		return bookListVo;
	}//getBookManageListDetail_DAO();

	
	public int bookManagedetailUpdate_DAO(vo.admin.BookList bookListVo) {
		int updateSuccess=0;
		String sql="UPDATE book SET status=?, keyword1=?, keyword2=?"
				+ ", keyword3=?, category=?, rent_code=?, bar_code=? where no=?";
		
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bookListVo.getStatus());
			pstmt.setString(2, bookListVo.getKeyword1());
			pstmt.setString(3, bookListVo.getKeyword2());
			pstmt.setString(4, bookListVo.getKeyword3());
			pstmt.setString(5, bookListVo.getCategory());
			pstmt.setString(6, bookListVo.getRent_code());
			pstmt.setString(7, bookListVo.getBar_code());
			pstmt.setInt(8, bookListVo.getNo());
			updateSuccess=pstmt.executeUpdate();
			System.out.println(updateSuccess);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateSuccess;
	}//bookManagedetailUpdate_DAO()

	public int bookManagedetailDelete_DAO(int no) {
		int deleteSuccess=0;
		String sql="DELETE FROM book WHERE no=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			deleteSuccess=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		
		return deleteSuccess;
	}//bookManagedetailDelete_DAO

	public int bookManageListCheckDelete_DAO(String[] checkList) {
		int checkDelete=0;
		int subcheck=0;
		try {
			for(int i=0; i<checkList.length; i++) {
				String sql="DELETE FROM book where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(checkList[i]));
				
				if(pstmt.executeUpdate()==1) {
					subcheck++;
				}		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		if(subcheck==checkList.length) {
			checkDelete=1;
		}
		
		
		
		return checkDelete;
	}//bookManageListCheckDelete_DAO


	public int getListCount_DAO(String Class) {
		int listCount=0;
		
		if(Class.equals("All")) {
		String sql="SELECT count(*) FROM book";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		}
		
		else {
			String sql="SELECT count(*) FROM book Where rent_code LIKE ?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, Class+"%");
				rs=pstmt.executeQuery();
				if(rs.next()) {
					listCount=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
		}
		
		
		
		return listCount;
	}//getListCount_DAO

	public int getListSearchCount_DAO(String search) {
		int listCount=0;
		String sql="SELECT count(*) FROM book where title like ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		return listCount;
	}// getListSearchCount_DAO

	public ArrayList getBookSearchList_DAO(int page, int limit, String search) {
		ArrayList bookList=new ArrayList();
		int startRow = (page - 1) * 10;
		
		
			String sql="Select * from book WHERE title LIKE ? order by reg_date desc LIMIT ?,10";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.BookList bookListVo=new vo.admin.BookList();
					bookListVo.setNo(rs.getInt(1));
					bookListVo.setTitle(rs.getString(2));
					bookListVo.setAuthor(rs.getString(3));
					bookListVo.setPublisher(rs.getString(4));
					bookListVo.setPublish_date(rs.getDate(5));
					bookListVo.setPrice(rs.getInt(6));
					bookListVo.setIsbn(rs.getString(7));
					bookListVo.setImage(rs.getString(8));
					bookListVo.setStatus(rs.getString(9));
					bookListVo.setKeyword1(rs.getString(10));
					bookListVo.setKeyword2(rs.getString(11));
					bookListVo.setKeyword3(rs.getString(12));
					bookListVo.setCategory(rs.getString(13));
					bookListVo.setRent_code(rs.getString(14));
					bookListVo.setBar_code(rs.getString(15));
					bookListVo.setReg_date(rs.getDate(16));
					bookList.add(bookListVo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
		
		
		
		return bookList;
		
	}//getBookSearchList_DAO

	
	
}//Class BookManageDAO
