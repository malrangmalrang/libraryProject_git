package dao.admin;
import static db.JdbcUtil.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.ClassObserver;


public class WishBookManageDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private WishBookManageDAO bookManageDAO;
	
	
	private WishBookManageDAO() {}
	
	public static WishBookManageDAO getinstance() {
		if(bookManageDAO==null) {
			bookManageDAO=new WishBookManageDAO();
		}
		return bookManageDAO;
	}
	
	public void setConnection(Connection con) {
		this.con=con;
	}

	public ArrayList getWishBookList_DAO(int page,int limit,String Class) {
		int startRow = (page - 1) * 10;
		System.out.println("wishBookDAO");
		ArrayList wishBookList=new ArrayList();
		
		
		if(Class.equals("Accept")) {
			try {
				
				String sql="select distinct title,author,publisher,publish_date,price,isbn,image,status from wish_book where status='승인' order by reg_date asc LIMIT ?,10";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.WishBookJoinBean wishBookJoinBean=new vo.admin.WishBookJoinBean();
					wishBookJoinBean.setTitle(rs.getString(1));
					wishBookJoinBean.setAuthor(rs.getString(2));
					wishBookJoinBean.setPublisher(rs.getString(3));
					wishBookJoinBean.setPublish_date(rs.getDate(4));
					wishBookJoinBean.setPrice(rs.getInt(5));
					wishBookJoinBean.setIsbn(rs.getString(6));
					wishBookJoinBean.setImage(rs.getString(7));
					wishBookJoinBean.setStatus(rs.getString(8));
					sql="select count(*) from wish_book where title=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, wishBookJoinBean.getTitle());
					ResultSet rs2=pstmt.executeQuery();
					rs2.next();
					wishBookJoinBean.setCount(rs2.getInt(1));
				    wishBookList.add(wishBookJoinBean);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}

		}
		
		else if(Class.equals("Refuse")) {
			try {
				
				String sql="select distinct title,author,publisher,publish_date,price,isbn,image,status from wish_book where status='거절' order by reg_date asc LIMIT ?,10";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.WishBookJoinBean wishBookJoinBean=new vo.admin.WishBookJoinBean();
					wishBookJoinBean.setTitle(rs.getString(1));
					wishBookJoinBean.setAuthor(rs.getString(2));
					wishBookJoinBean.setPublisher(rs.getString(3));
					wishBookJoinBean.setPublish_date(rs.getDate(4));
					wishBookJoinBean.setPrice(rs.getInt(5));
					wishBookJoinBean.setIsbn(rs.getString(6));
					wishBookJoinBean.setImage(rs.getString(7));
					wishBookJoinBean.setStatus(rs.getString(8));
					sql="select count(*) from wish_book where title=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, wishBookJoinBean.getTitle());
					ResultSet rs2=pstmt.executeQuery();
					rs2.next();
					wishBookJoinBean.setCount(rs2.getInt(1));
				    wishBookList.add(wishBookJoinBean);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}


		}
		
		else if(Class.equals("Wishing")) {
			try {
		
				String sql="select distinct title,author,publisher,publish_date,price,isbn,image,status from wish_book where status='신청' order by reg_date asc LIMIT ?,10";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.WishBookJoinBean wishBookJoinBean=new vo.admin.WishBookJoinBean();
					wishBookJoinBean.setTitle(rs.getString(1));
					wishBookJoinBean.setAuthor(rs.getString(2));
					wishBookJoinBean.setPublisher(rs.getString(3));
					wishBookJoinBean.setPublish_date(rs.getDate(4));
					wishBookJoinBean.setPrice(rs.getInt(5));
					wishBookJoinBean.setIsbn(rs.getString(6));
					wishBookJoinBean.setImage(rs.getString(7));
					wishBookJoinBean.setStatus(rs.getString(8));
					sql="select count(*) from wish_book where title=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, wishBookJoinBean.getTitle());
					ResultSet rs2=pstmt.executeQuery();
					rs2.next();
					wishBookJoinBean.setCount(rs2.getInt(1));
				    wishBookList.add(wishBookJoinBean);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
		}
		
		return wishBookList;
	}// getWishBookList_DAO()

	public ArrayList getWishBookUpdateList_DAO(String[] values) {
		ArrayList wishBookUpdateList=new ArrayList();
		
		try {
			for(int i=0; i<values.length; i++) {
			String sql="select distinct title,author,publisher,publish_date,price,isbn,image,status from wish_book where status='신청' AND title=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, values[i]);
			rs=pstmt.executeQuery();
			rs.next();
			vo.admin.WishBookBean wishBookBean=new vo.admin.WishBookBean();
			wishBookBean.setTitle(rs.getString(1));
			wishBookBean.setAuthor(rs.getString(2));
			wishBookBean.setPublisher(rs.getString(3));
			wishBookBean.setPublish_date(rs.getDate(4));
			wishBookBean.setPrice(rs.getInt(5));
			wishBookBean.setIsbn(rs.getString(6));
			wishBookBean.setImage(rs.getString(7));
			wishBookBean.setStatus(rs.getString(8));
			wishBookUpdateList.add(wishBookBean);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return wishBookUpdateList;
		
	}//getWishBookUpdateList_DAO

	public int wishBookManageListUpdate_DAO(ArrayList wishBookUpdateList, ArrayList wishBookUpdateListArray) {
		int isUpdateSuccess=0;
		
		try {
			for(int i=0; i<wishBookUpdateListArray.size(); i++) {
				vo.admin.WishBookBean wishBookBean=(vo.admin.WishBookBean)wishBookUpdateList.get(i);
				vo.admin.BookList wishBookInsertList=(vo.admin.BookList)wishBookUpdateListArray.get(i);
				String sql="Update wish_book SET status=? where title=? AND status='신청'";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "승인");
				pstmt.setString(2, wishBookBean.getTitle());
				pstmt.executeUpdate();
				
				sql="select title, rent_code from book where title=? order by rent_code desc ";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, wishBookBean.getTitle());
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					String standardRentCode=rs.getString(2);
					
					String frontRentCode=standardRentCode.substring(0,2);
					double ModifiedRentCode=((10*Double.parseDouble(standardRentCode.substring(2,standardRentCode.length())))+1)/10;
					String AddRentCode=frontRentCode+ModifiedRentCode;
					wishBookInsertList.setRent_code(AddRentCode);
					
					sql="select title, bar_code from book where title=? order by bar_code desc";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, wishBookBean.getTitle());
					ResultSet rs2=pstmt.executeQuery();
					rs2.next();
					String standardBarCode=rs2.getString(2);
					String AddBarCode=String.valueOf((Long.parseLong(standardBarCode)+1));
					wishBookInsertList.setBar_code(AddBarCode);
					
					sql="select distinct category from book where title=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, wishBookBean.getTitle());
					ResultSet rs3=pstmt.executeQuery();
					rs3.next();
					wishBookInsertList.setCategory(rs3.getString(1));	
				}
				
				else {
					sql="select max(rent_code)  from book Where category=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, wishBookInsertList.getCategory());
					ResultSet rs4=pstmt.executeQuery();
					rs4.next();
//					int test=(int)Double.parseDouble("59.1");
					String standardRentCode2=rs4.getString(1);
					String frontRentCode2=standardRentCode2.substring(0,2);
					int ModifiedRentCode2=1+(int)Double.parseDouble(standardRentCode2.substring(2,standardRentCode2.length()));
					String AddRentCode2=frontRentCode2+ModifiedRentCode2;
					
					wishBookInsertList.setRent_code(AddRentCode2);
					String AddBarCode2=String.valueOf((Long.parseLong(wishBookInsertList.getIsbn())+1));
					wishBookInsertList.setBar_code(AddBarCode2);			
				}
				
				sql="INSERT INTO book values(0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, wishBookInsertList.getTitle());
				pstmt.setString(2, wishBookInsertList.getAuthor());
				pstmt.setString(3, wishBookInsertList.getPublisher());
				pstmt.setDate(4, wishBookInsertList.getPublish_date());
				pstmt.setInt(5, wishBookInsertList.getPrice());
				pstmt.setString(6, wishBookInsertList.getIsbn());
				pstmt.setString(7, wishBookInsertList.getImage());
				pstmt.setString(8, wishBookInsertList.getStatus());
				pstmt.setString(9, wishBookInsertList.getKeyword1());
				pstmt.setString(10, wishBookInsertList.getKeyword2());
				pstmt.setString(11, wishBookInsertList.getKeyword3());
				pstmt.setString(12, wishBookInsertList.getCategory());
				pstmt.setString(13, wishBookInsertList.getRent_code());
				pstmt.setString(14, wishBookInsertList.getBar_code());
				
				pstmt.executeUpdate();
				
				isUpdateSuccess=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isUpdateSuccess;
		
	}//wishBookManageListUpdate_DAO

	public int wishBookManageListRefuse_DAO(String[] values) {
		int isRefuseSuccess=0;
		
		try {
			for(int i=0; i<values.length; i++) {
			String sql="UPDATE wish_book SET status=? WHERE title=? AND status='신청'";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "거절");
			pstmt.setString(2, values[i]);
			pstmt.executeUpdate();
			}
			isRefuseSuccess=1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		
		
		return isRefuseSuccess;
		
		
	}// wishBookManageListRefuse_DAO

	public ArrayList getWishBookDetail_DAO(String bookTitle,String bookStatus) {
		ArrayList wishBookDetailList=new ArrayList();
		String sql="select w.no, w.title, w.author, w.publisher, w.publish_date, w.price, w.isbn, w.image,w.status, w.reg_date, m.email from wish_book w join member m ON(w.member_no=m.no) where title=? And status=? order by reg_date asc;";

		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bookTitle);
			pstmt.setString(2, bookStatus);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				vo.admin.WishBookJoinBean wishBookJoinBean=new vo.admin.WishBookJoinBean();
				wishBookJoinBean.setNo(rs.getInt(1));
				wishBookJoinBean.setTitle(rs.getString(2));
				wishBookJoinBean.setAuthor(rs.getString(3));
				wishBookJoinBean.setPublisher(rs.getString(4));
				wishBookJoinBean.setPublish_date(rs.getDate(5));
				wishBookJoinBean.setPrice(rs.getInt(6));
				wishBookJoinBean.setIsbn(rs.getString(7));
				wishBookJoinBean.setImage(rs.getString(8));
				wishBookJoinBean.setStatus(rs.getString(9));
				wishBookJoinBean.setReg_date(rs.getDate(10));
				wishBookJoinBean.setEmail(rs.getString("email"));
				wishBookDetailList.add(wishBookJoinBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return wishBookDetailList;
	}//getWishBookDetail_DAO();

	public int getListCount_DAO(String Class) {
		int listCount=0;
		String sql="SELECT count(*) FROM wish_book where status=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, Class);
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
	}

	

	

	



	
	
}//Class BookManageDAO
