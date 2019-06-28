package dao.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.ClassObserver;


public class RentalManageDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private RentalManageDAO bookManageDAO;
	
	
	private RentalManageDAO() {}
	
	public static RentalManageDAO getinstance() {
		if(bookManageDAO==null) {
			bookManageDAO=new RentalManageDAO();
		}
		return bookManageDAO;
	}
	
	public void setConnection(Connection con) {
		this.con=con;
	}

	public ArrayList RentalManageList_DAO(int page, int limit, String rmclass) {
		int startRow = (page - 1) * 10;
		ArrayList rentalList=new ArrayList();
		
		if(rmclass.equals("전체")) {
//			String sql="SELECT * from rental2";
			String sql="select r.no, r.rent_date, r.due_date, r.return_date, r.status, m.email, b.title from rental r join member m join book b ON (r.member_no=m.no) AND (r.book_no=b.no) LIMIT ?,10";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.RentalJoinBean rentalJoinBean=new vo.admin.RentalJoinBean();
					rentalJoinBean.setNo(rs.getInt(1));
					rentalJoinBean.setRent_date(rs.getDate(2));
					rentalJoinBean.setDue_date(rs.getDate(3));
					rentalJoinBean.setReturn_date(rs.getDate(4));
					rentalJoinBean.setStatus(rs.getString(5));
					
					rentalJoinBean.setEmail(rs.getString("email"));
					rentalJoinBean.setTitle(rs.getString("title"));
					rentalList.add(rentalJoinBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			}
		
		else if(rmclass.equals("신청")||rmclass.equals("대출중")||rmclass.equals("신청")||rmclass.equals("반납")||rmclass.equals("분실/파손")||rmclass.equals("연체")) {
		String sql="select r.no, r.rent_date, r.due_date, r.return_date, r.status, m.email, b.title from rental r join member m join book b ON (r.member_no=m.no) AND (r.book_no=b.no) WHERE r.status=? LIMIT ?,10";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, rmclass);
			pstmt.setInt(2, startRow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				vo.admin.RentalJoinBean rentalJoinBean=new vo.admin.RentalJoinBean();
				rentalJoinBean.setNo(rs.getInt(1));
				rentalJoinBean.setRent_date(rs.getDate(2));
				rentalJoinBean.setDue_date(rs.getDate(3));
				rentalJoinBean.setReturn_date(rs.getDate(4));
				rentalJoinBean.setStatus(rs.getString(5));
				
				rentalJoinBean.setEmail(rs.getString("email"));
				rentalJoinBean.setTitle(rs.getString("title"));
				rentalList.add(rentalJoinBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		}
		
		else if(rmclass.equals("대여일자")) {
			String sql="select r.no, r.rent_date, r.due_date, r.return_date, r.status, m.email, b.title from rental r join member m join book b ON (r.member_no=m.no) AND (r.book_no=b.no) order by r.rent_date desc LIMIT ?,10";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.RentalJoinBean rentalJoinBean=new vo.admin.RentalJoinBean();
					rentalJoinBean.setNo(rs.getInt(1));
					rentalJoinBean.setRent_date(rs.getDate(2));
					rentalJoinBean.setDue_date(rs.getDate(3));
					rentalJoinBean.setReturn_date(rs.getDate(4));
					rentalJoinBean.setStatus(rs.getString(5));
					
					rentalJoinBean.setEmail(rs.getString("email"));
					rentalJoinBean.setTitle(rs.getString("title"));
					rentalList.add(rentalJoinBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			}
		
		else if(rmclass.equals("반납일자")) {
			String sql="select r.no, r.rent_date, r.due_date, r.return_date, r.status, m.email, b.title from rental r join member m join book b ON (r.member_no=m.no) AND (r.book_no=b.no) order by r.return_date desc LIMIT ?,10";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.RentalJoinBean rentalJoinBean=new vo.admin.RentalJoinBean();
					rentalJoinBean.setNo(rs.getInt(1));
					rentalJoinBean.setRent_date(rs.getDate(2));
					rentalJoinBean.setDue_date(rs.getDate(3));
					rentalJoinBean.setReturn_date(rs.getDate(4));
					rentalJoinBean.setStatus(rs.getString(5));
					
					rentalJoinBean.setEmail(rs.getString("email"));
					rentalJoinBean.setTitle(rs.getString("title"));
					rentalList.add(rentalJoinBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			}
		
		else if(rmclass.equals("대여자")) {
			String sql="select r.no, r.rent_date, r.due_date, r.return_date, r.status, m.email, b.title from rental r join member m join book b ON (r.member_no=m.no) AND (r.book_no=b.no) order by r.member_no desc LIMIT ?,10";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.RentalJoinBean rentalJoinBean=new vo.admin.RentalJoinBean();
					rentalJoinBean.setNo(rs.getInt(1));
					rentalJoinBean.setRent_date(rs.getDate(2));
					rentalJoinBean.setDue_date(rs.getDate(3));
					rentalJoinBean.setReturn_date(rs.getDate(4));
					rentalJoinBean.setStatus(rs.getString(5));
					
					rentalJoinBean.setEmail(rs.getString("email"));
					rentalJoinBean.setTitle(rs.getString("title"));
					rentalList.add(rentalJoinBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			}
		
		else if(rmclass.equals("도서명")) {
			String sql="select r.no, r.rent_date, r.due_date, r.return_date, r.status, m.email, b.title from rental r join member m join book b ON (r.member_no=m.no) AND (r.book_no=b.no) order by r.book_no desc LIMIT ?,10";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.RentalJoinBean rentalJoinBean=new vo.admin.RentalJoinBean();
					rentalJoinBean.setNo(rs.getInt(1));
					rentalJoinBean.setRent_date(rs.getDate(2));
					rentalJoinBean.setDue_date(rs.getDate(3));
					rentalJoinBean.setReturn_date(rs.getDate(4));
					rentalJoinBean.setStatus(rs.getString(5));
					
					rentalJoinBean.setEmail(rs.getString("email"));
					rentalJoinBean.setTitle(rs.getString("title"));
					rentalList.add(rentalJoinBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			}
		
		else if(rmclass.equals("반납기한")) {
			String sql="select r.no, r.rent_date, r.due_date, r.return_date, r.status, m.email, b.title from rental r join member m join book b ON (r.member_no=m.no) AND (r.book_no=b.no) order by r.due_date asc LIMIT ?,10";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.RentalJoinBean rentalJoinBean=new vo.admin.RentalJoinBean();
					rentalJoinBean.setNo(rs.getInt(1));
					rentalJoinBean.setRent_date(rs.getDate(2));
					rentalJoinBean.setDue_date(rs.getDate(3));
					rentalJoinBean.setReturn_date(rs.getDate(4));
					rentalJoinBean.setStatus(rs.getString(5));
					
					rentalJoinBean.setEmail(rs.getString("email"));
					rentalJoinBean.setTitle(rs.getString("title"));
					rentalList.add(rentalJoinBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			}
		
		return rentalList;
	}// RentalManageList_DAO()

	public int RentalManageModify_DAO(String modifyClass, String[] values) {
		System.out.println("DAO에서 받아온 변경타입 : "+modifyClass);

		int isModifySuccess=0;
		
		if(modifyClass.equals("반납")) {
			ArrayList valuesForBook=new ArrayList();
			try {
				for(int i=0; i<values.length; i++) {
				String sql="update rental set return_date=now(),status=? where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, modifyClass);
				pstmt.setInt(2, Integer.parseInt(values[i]));
				pstmt.executeUpdate();
				
				sql="SELECT * FROM rental where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(values[i]));
				rs=pstmt.executeQuery();

				
				while(rs.next()) {
					valuesForBook.add(rs.getInt("book_no"));
				}
				}//for문
				
				for(int i=0; i<valuesForBook.size(); i++) {
				String sql="UPDATE book SET status=? where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "대출가능");
				pstmt.setInt(2, (int) valuesForBook.get(i));
				pstmt.executeUpdate();
				}//for문
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			isModifySuccess=1;
		}//if("반납")
		
		else if(modifyClass.equals("분실/파손")) {
			ArrayList valuesForBook=new ArrayList();
			try {
				for(int i=0; i<values.length; i++) {
				String sql="UPDATE rental SET status=? where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, modifyClass);
				pstmt.setInt(2, Integer.parseInt(values[i]));
				pstmt.executeUpdate();
				
				sql="SELECT * FROM rental where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(values[i]));
				rs=pstmt.executeQuery();

				
				while(rs.next()) {
					valuesForBook.add(rs.getInt("book_no"));
				}
				}//for문
				
				for(int i=0; i<valuesForBook.size(); i++) {
				String sql="UPDATE book SET status=? where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, modifyClass);
				pstmt.setInt(2, (int) valuesForBook.get(i));
				pstmt.executeUpdate();
				}//for문
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			isModifySuccess=1;
		}//if("분실/파손")
		
		else if(modifyClass.equals("대출중")) {
			ArrayList valuesForBook=new ArrayList();
			try {
				for(int i=0; i<values.length; i++) {
				String sql="UPDATE rental SET status=?, rent_date=now(), due_date=DATE_ADD(now(), INTERVAL 14 DAY) where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, modifyClass);
				pstmt.setInt(2, Integer.parseInt(values[i]));
				pstmt.executeUpdate();
				
				sql="SELECT * FROM rental where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(values[i]));
				rs=pstmt.executeQuery();

				
				while(rs.next()) {
					valuesForBook.add(rs.getInt("book_no"));
				}
				}//for문
				
				for(int i=0; i<valuesForBook.size(); i++) {
				String sql="UPDATE book SET status=? where no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, modifyClass);
				pstmt.setInt(2, (int) valuesForBook.get(i));
				pstmt.executeUpdate();
				}//for문
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			isModifySuccess=1;
		}//if("대출중")
		
		
		
		return isModifySuccess;
	}//RentalManageModify_DAO

	public ArrayList rentalSearchList_DAO(String rentalSearchSelectValue, String rentalSearchText) {
		ArrayList rentalList=new ArrayList();
		
		if(rentalSearchSelectValue.equals("대여자")) {
			String sql="select r.no, r.rent_date, r.due_date, r.return_date, r.status, m.email, b.title from rental r join member m join book b ON (r.member_no=m.no) AND (r.book_no=b.no) WHERE m.email=?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, rentalSearchText);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.RentalJoinBean rentalJoinBean=new vo.admin.RentalJoinBean();
					rentalJoinBean.setNo(rs.getInt(1));
					rentalJoinBean.setRent_date(rs.getDate(2));
					rentalJoinBean.setDue_date(rs.getDate(3));
					rentalJoinBean.setReturn_date(rs.getDate(4));
					rentalJoinBean.setStatus(rs.getString(5));
					
					rentalJoinBean.setEmail(rs.getString("email"));
					rentalJoinBean.setTitle(rs.getString("title"));
					rentalList.add(rentalJoinBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
		}
		
		else if(rentalSearchSelectValue.equals("도서명")) {
			String sql="select r.no, r.rent_date, r.due_date, r.return_date, r.status, m.email, b.title from rental r join member m join book b ON (r.member_no=m.no) AND (r.book_no=b.no) WHERE b.title=?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, rentalSearchText);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					vo.admin.RentalJoinBean rentalJoinBean=new vo.admin.RentalJoinBean();
					rentalJoinBean.setNo(rs.getInt(1));
					rentalJoinBean.setRent_date(rs.getDate(2));
					rentalJoinBean.setDue_date(rs.getDate(3));
					rentalJoinBean.setReturn_date(rs.getDate(4));
					rentalJoinBean.setStatus(rs.getString(5));
					
					rentalJoinBean.setEmail(rs.getString("email"));
					rentalJoinBean.setTitle(rs.getString("title"));
					rentalList.add(rentalJoinBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
		}
		
		return rentalList;
	}//rentalSearchList_DAO()

	public int getListCount_DAO(String rmclass) {
		int listCount=0;
		if(rmclass.equals("전체")) {
			String sql="SELECT count(*) FROM rental";
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
		
		else if(rmclass.equals("신청")||rmclass.equals("대출중")||rmclass.equals("신청")||rmclass.equals("반납")||rmclass.equals("분실/파손")) {
			String sql="SELECT count(*) FROM rental where status=?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, rmclass);
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
		
		else if(rmclass.equals("대여일자")) {
			String sql="SELECT count(*) FROM rental";
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
		
		else if(rmclass.equals("반납일자")) {
			String sql="SELECT count(*) FROM rental";
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
		
		else if(rmclass.equals("대여자")) {
			String sql="SELECT count(*) FROM rental";
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
		
		else if(rmclass.equals("도서명")) {
			String sql="SELECT count(*) FROM rental";
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
		
		else if(rmclass.equals("반납기한")) {
			String sql="SELECT count(*) FROM rental";
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
		

		
		return listCount;
	}

	



	
	
}//Class BookManageDAO
