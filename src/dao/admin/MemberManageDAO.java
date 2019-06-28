package dao.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberBean;

public class MemberManageDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private MemberManageDAO bookManageDAO;
	
	
	private MemberManageDAO() {}
	
	public static MemberManageDAO getinstance() {
		if(bookManageDAO==null) {
			bookManageDAO=new MemberManageDAO();
		}
		return bookManageDAO;
	}
	
	public void setConnection(Connection con) {
		this.con=con;
	}

	public ArrayList getMemberList_DAO(int page, int limit, String str) {
		int startRow = (page - 1) * 10;
		ArrayList memberList=new ArrayList();
		if(str.equals("전체")) {
			String sql="select * from member order by reg_date desc LIMIT ?,10";
			try {
				System.out.println("테스트, startrow :"+startRow);
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					MemberBean memberBean=new MemberBean();
					memberBean.setNo(rs.getInt(1));
					memberBean.setEmail(rs.getString(2));
					memberBean.setPassword(rs.getString(3));
					memberBean.setName(rs.getString(4));
					memberBean.setGender(rs.getString(5));
					memberBean.setBirth(rs.getString(6));
					memberBean.setPhone(rs.getString(7));
					memberBean.setImage(rs.getString(8));
					memberBean.setAddress1(rs.getString(9));
					memberBean.setAddress2(rs.getString(10));
					memberBean.setPostcode(rs.getInt(11));
					memberBean.setType(rs.getString(12));
					memberBean.setReg_date(rs.getDate(13));
					
					 memberList.add(memberBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
		}
		
		else {
		String sql="select * from member where type=? order by reg_date desc LIMIT ?,10";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(2, startRow);
			pstmt.setString(1, str);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MemberBean memberBean=new MemberBean();
				memberBean.setNo(rs.getInt(1));
				memberBean.setEmail(rs.getString(2));
				memberBean.setPassword(rs.getString(3));
				memberBean.setName(rs.getString(4));
				memberBean.setGender(rs.getString(5));
				memberBean.setBirth(rs.getString(6));
				memberBean.setPhone(rs.getString(7));
				memberBean.setImage(rs.getString(8));
				memberBean.setAddress1(rs.getString(9));
				memberBean.setAddress2(rs.getString(10));
				memberBean.setPostcode(rs.getInt(11));
				memberBean.setType(rs.getString(12));
				memberBean.setReg_date(rs.getDate(13));
				
				 memberList.add(memberBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		}
		
		return  memberList;
	}//getMemberList_DAO()

	public int memberManageModifyMember_DAO(String[] values, String update_type) {
		int isModifySuccess=0;
		System.out.println("변경DAO");
		try {
			for(int i=0; i<values.length; i++) {
				String sql="UPDATE member SET type=? WHERE no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, update_type);
				pstmt.setInt(2, Integer.parseInt(values[i]));
				isModifySuccess=pstmt.executeUpdate();
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return isModifySuccess;
	}//memberManageModifyMember_DAO()

	public int getListCount_DAO(String Class) {
		int listCount=0;
		
		if(Class.equals("전체")) {
			String sql="SELECT count(*) FROM member";
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
			String sql="SELECT count(*) FROM member where type=?";
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
		}
		
		return listCount;
	}



	
	

	

	
	
}//Class BookManageDAO
