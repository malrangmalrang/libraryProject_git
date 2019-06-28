package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.AES256Cipher;

import static db.JdbcUtil.*;
import vo.BoardBean;
import vo.CommentBean;
import vo.MemberBean;

public class BoardDAO {
	private BoardDAO(){
	}
	
	private static BoardDAO instance;
	
	public static BoardDAO getInstance() {
		if(instance==null) {
			instance = new BoardDAO();
		}
		return instance;
	}

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<ArrayList> selectArticleList(int page, int limit) {
		ArrayList<BoardBean> articleList1 = new ArrayList<BoardBean>();
		ArrayList<MemberBean> articleList2 = new ArrayList<MemberBean>();
		ArrayList<ArrayList> articleListTotal = new ArrayList<ArrayList>();
		ArrayList beans = new ArrayList<>();
		BoardBean boardBean = null;
		MemberBean memberBean = null;
//		String sql = "select * from board where board_type = 'qna' order by reg_date desc limit ?, ?";
		//느리진 않지만 답변완료를 못달아줌.
//		String sql = "select b.no, b.title, b.content, b.readcount, b.file, b.board_type, b.reg_date, b.member_no, m.email, m.password, m.name, m.gender, m.birth, m.phone, m.image, m.address1, m.address2, m.postcode, m.type, m.reg_date from board b, member m where b.member_no = m.no and b.board_type = 'qna' order by b.reg_date desc limit ?,?";
		//엄청느려짐 답변완료 달수있게 boardbean에 코멘트넘버 갯수 넣어줌
		String sql = "select b.no, b.title, b.content, b.readcount, b.file, b.board_type, b.reg_date, b.member_no, m.email, m.password, m.name, m.gender, m.birth, m.phone, m.image, m.address1, m.address2, m.postcode, m.type, m.reg_date, count(c.no) comment from member m, board b left outer join board_comment c on b.no = c.board_no where b.member_no = m.no and b.board_type = 'qna' group by no order by b.reg_date desc limit ?,?";
//		String sql = "select * from board where board_type = 'qna' order by reg_date desc";
		int startRow = (page-1)*10;
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println("startRow : " + startRow);
			System.out.println("limit : "+limit);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("sql문 성공");
				boardBean= new BoardBean();
				
				boardBean.setNo(rs.getInt("b.no"));
				boardBean.setTitle(rs.getString("b.title"));
				boardBean.setContent(rs.getString("b.content"));
				boardBean.setBoard_type(rs.getString("b.board_type"));
				boardBean.setFile(rs.getString("b.file"));
				boardBean.setMember_no(rs.getInt("b.member_no"));
				boardBean.setReadcount(rs.getInt("b.readcount"));
				boardBean.setReg_date(rs.getDate("b.reg_date"));
				boardBean.setComment(rs.getInt("comment"));
				
				
				memberBean = new MemberBean();
				
				memberBean.setAddress1(rs.getString("m.address1"));
				memberBean.setAddress2(rs.getString("m.address2"));
				memberBean.setBirth(rs.getString("m.birth"));
				memberBean.setEmail(rs.getString("email"));
				memberBean.setGender(rs.getString("m.gender"));
				memberBean.setImage(rs.getString("m.image"));
				memberBean.setName(rs.getString("m.name"));
				memberBean.setNo(rs.getInt("b.member_no"));
				memberBean.setPassword(rs.getString("m.password"));
				memberBean.setPhone(rs.getString("m.phone"));
				memberBean.setPostcode(rs.getInt("m.postcode"));
				memberBean.setReg_date(rs.getDate("m.reg_date"));
				memberBean.setType(rs.getString("m.type"));
				
				articleList1.add(boardBean);
				articleList2.add(memberBean);
				
				
			}
			articleListTotal.add(articleList1);
			articleListTotal.add(articleList2);
			
		} catch (SQLException e) {
			System.out.println("selectArticleList() 실패"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleListTotal;
	}
	public ArrayList<ArrayList> selectArticleList(int page, int limit, String option, String keyword) {
		ArrayList<BoardBean> articleList1 = new ArrayList<BoardBean>();
		ArrayList<MemberBean> articleList2 = new ArrayList<MemberBean>();
		ArrayList<ArrayList> articleListTotal = new ArrayList<ArrayList>();
		ArrayList beans = new ArrayList<>();
		BoardBean boardBean = null;
		MemberBean memberBean = null;
		String sql = "select b.no, b.title, b.content, b.readcount, b.file, b.board_type, b.reg_date, b.member_no, m.email, m.password, m.name, m.gender, m.birth, m.phone, m.image, m.address1, m.address2, m.postcode, m.type, m.reg_date, count(c.no) comment from member m, board b left outer join board_comment c on b.no = c.board_no where b.member_no = m.no and b.board_type = 'qna' ";					
//		String sql = "select * from member m, board b left outer join board_comment c on b.no = c.board_no where b.member_no = m.no and b.board_type = 'qna' ";
		int startRow = (page-1)*10;
		try {
			String[] searches = keyword.split("\\s");
			System.out.println("length 값 : " +searches.length);
			int j=0;
			while(j<searches.length) {
				if(option.equals("name")) {
					sql+=" and m."+ option +" like ? ";
					j++;
					System.out.println("j값 : "+j);
				}else {
					sql+=" and b."+ option +" like ? ";
					j++;
					System.out.println("j값 : "+j);
				}
			}
//			sql+=" order by b.reg_date desc limit ?,?";
			sql+=" group by no order by b.reg_date desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			int i=1;
			for(;i<=searches.length;i++) {
				pstmt.setString(i, "%"+searches[i-1]+"%");
				System.out.println("searches값 : " + searches[i-1]);
				System.out.println("i값 : " + i);
			}
			
			System.out.println("pstmt.setInt i값: "+i);
			System.out.println("pstmt.setInt i+1값: "+(i+1));
			System.out.println("startRow : " + startRow);
			System.out.println("limit : "+limit);
			pstmt.setInt(i, startRow);
			pstmt.setInt(i+1, limit);
			System.out.println("pstmt sql : "+pstmt);
			System.out.println("for문 바깥 i값 : "+i);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("sql문 성공");
				boardBean= new BoardBean();
				
				boardBean.setNo(rs.getInt("b.no"));
				boardBean.setTitle(rs.getString("b.title"));
				boardBean.setContent(rs.getString("b.content"));
				boardBean.setBoard_type(rs.getString("b.board_type"));
//				boardBean.setFile(rs.getString("b.file"));
				boardBean.setMember_no(rs.getInt("b.member_no"));
				boardBean.setReadcount(rs.getInt("b.readcount"));
				boardBean.setReg_date(rs.getDate("b.reg_date"));
				
				memberBean = new MemberBean();
				
				memberBean.setAddress1(rs.getString("m.address1"));
				memberBean.setAddress2(rs.getString("m.address2"));
				memberBean.setBirth(rs.getString("m.birth"));
				memberBean.setEmail(rs.getString("email"));
				memberBean.setGender(rs.getString("m.gender"));
				memberBean.setImage(rs.getString("m.image"));
				memberBean.setName(rs.getString("m.name"));
				memberBean.setNo(rs.getInt("b.member_no"));
				memberBean.setPassword(rs.getString("m.password"));
				memberBean.setPhone(rs.getString("m.phone"));
				memberBean.setPostcode(rs.getInt("m.postcode"));
				memberBean.setReg_date(rs.getDate("m.reg_date"));
				memberBean.setType(rs.getString("m.type"));
				
				articleList1.add(boardBean);
				articleList2.add(memberBean);
				
				
			}
			articleListTotal.add(articleList1);
			articleListTotal.add(articleList2);
			
		} catch (SQLException e) {
			System.out.println("selectArticleList() 실패"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleListTotal;
	}

	public int selectListCount() {
		int listCount = 0;
		String sql = "select count(*) from board where board_type = 'qna'";
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("selectListCount():");
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("selectListCount() 실패"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	public int selectListCount(String option, String keyword) {
		int listCount = 0;
//		String sql = "select * from member m, board b left outer join board_comment c on b.no = c.board_no where b.member_no = m.no and b.board_type = 'qna' ";
//		String sql = "select count(*) from member m, board b left outer join board_comment c on b.no = c.board_no where b.member_no = m.no and b.board_type = 'qna' and "+ option +" like ?";
		String sql = "select count(*) from member m, board b where b.member_no = m.no and b.board_type = 'qna' and "+ option +" like ? ";
		try {			
			String[] searches = keyword.split("\\s");
			System.out.println("length 값 : " +searches.length);
			int j=1;
			while(j<searches.length) {
				if(option.equals("name")) {
					sql+=" and m."+ option +" like ? ";
					j++;
					System.out.println("j값 : "+j);
				}else {
					sql+=" and b."+ option +" like ? ";
					j++;
					System.out.println("j값 : "+j);
				}
			}
			pstmt = con.prepareStatement(sql);
			
			for(int i=1;i<=searches.length;i++) {
				pstmt.setString(i, "%"+searches[i-1]+"%");
				System.out.println("searches값 : " + searches[i-1]);
				System.out.println("i값 : " + i);
			}
			
			System.out.println("select list count sql : "+pstmt);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("selectListCount():");
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("selectListCount() 실패"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	

	public int insertArticle(BoardBean boardBean, MemberBean memberBean) {
		int insertCount = 0;
//		String sql = "insert into board(title,content,readcount,file,board_type,reg_date,member_no,email_reply)"+ 
//									"values(?,?,0,?,?,now(),?,?)";
		String sql = "insert into board(title,content,readcount,file,board_type,reg_date,member_no)"+ 
				"values(?,?,0,?,?,now(),?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardBean.getTitle());
			pstmt.setString(2, boardBean.getContent());
			pstmt.setString(3, boardBean.getFile());
			pstmt.setString(4, boardBean.getBoard_type());
			pstmt.setInt(5, memberBean.getNo());
//			pstmt.setInt(6, boardBean.getEmail_reply());
			System.out.println("memberBean getNo : "+memberBean.getNo());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertArticle() 실패"+e.getMessage());
		}finally {
			close(pstmt);
		}
		return insertCount;
	}

	public ArrayList selectArticle(int board_num) {
		ArrayList beans = new ArrayList<>();
		System.out.println("selectArticle 왔당");
		BoardBean boardBean = null;
		MemberBean memberBean = null;
		
//		String sql = "select b.no, b.title, b.content, b.readcount, b.file, b.board_type, b.reg_date, b.member_no, b.email_reply, m.email, m.password, m.name, m.gender, m.birth, m.phone, m.image, m.address1, m.address2, m.postcode, m.type, m.reg_date from board b, member m where b.member_no = m.no and b.no = ?";
		String sql = "select b.no, b.title, b.content, b.readcount, b.file, b.board_type, b.reg_date, b.member_no, m.no, m.email, m.password, m.name, m.gender, m.birth, m.phone, m.image, m.address1, m.address2, m.postcode, m.type, m.reg_date from board b, member m where b.member_no = m.no and b.no = ?";
//		String sql = "select b.no, b.title, b.content, b.readcount, b.file, b.board_type, b.reg_date, b.member_no, b.email_reply, m.email, m.password, m.name, m.gender, m.birth, m.phone, m.image, m.address1, m.address2, m.postcode, m.type, m.reg_date, c.no, c.content, c.reg_date, c.board_no, c.member_no from board b, member m, board_comment c where b.member_no = m.no and b.no = c.board_no and b.no=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardBean= new BoardBean();
				
				boardBean.setNo(rs.getInt("b.no"));
				boardBean.setTitle(rs.getString("b.title"));
				boardBean.setContent(rs.getString("b.content"));
				boardBean.setBoard_type(rs.getString("b.board_type"));
				boardBean.setFile(rs.getString("b.file"));
				boardBean.setMember_no(rs.getInt("b.member_no"));
				boardBean.setReadcount(rs.getInt("b.readcount"));
				boardBean.setReg_date(rs.getDate("b.reg_date"));
//				boardBean.setEmail_reply(rs.getInt("b.email_reply"));
				
				memberBean = new MemberBean();
				
				memberBean.setAddress1(rs.getString("m.address1"));
				memberBean.setAddress2(rs.getString("m.address2"));
				memberBean.setBirth(rs.getString("m.birth"));
				memberBean.setEmail(rs.getString("email"));
				memberBean.setGender(rs.getString("m.gender"));
				memberBean.setImage(rs.getString("m.image"));
				memberBean.setName(rs.getString("m.name"));
				memberBean.setNo(rs.getInt("m.no"));
				memberBean.setPassword(rs.getString("m.password"));
				memberBean.setPhone(rs.getString("m.phone"));
				memberBean.setPostcode(rs.getInt("m.postcode"));
				memberBean.setReg_date(rs.getDate("m.reg_date"));
				memberBean.setType(rs.getString("m.type"));
				
//				commentBean.setBoard_no(rs.getInt("c.board_no"));
//				commentBean.setContent(rs.getString("c.content"));
//				commentBean.setMember_no(rs.getInt("c.member_no"));
//				commentBean.setNo(rs.getInt("c.no"));
//				commentBean.setReg_date(rs.getDate("c.reg_date"));
				
				beans.add(boardBean);
				beans.add(memberBean);
//				beans.add(commentBean);
			}
			sql = "select c.no, c.content, c.reg_Date, c.board_no, c.member_no, m.no, m.email, m.password, m.name, m.gender, m.birth, m.phone, m.image, m.address1, m.address2, m.postcode, m.type, m.reg_date from board_comment c, member m where c.member_no = m.no and c.board_no = ?";
//			sql = "select * from board_comment where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			CommentBean commentBean = null;
			ArrayList commentList = new ArrayList<CommentBean>();
			ArrayList commentList1 = new ArrayList<CommentBean>();
			ArrayList commentList2 = new ArrayList<CommentBean>();
			while(rs.next()) {
				commentBean = new CommentBean();
				
				commentBean.setBoard_no(rs.getInt("c.board_no"));
				commentBean.setContent(rs.getString("c.content"));
				commentBean.setMember_no(rs.getInt("c.member_no"));
				commentBean.setNo(rs.getInt("c.no"));
				commentBean.setReg_date(rs.getDate("c.reg_date"));
				
				memberBean = new MemberBean();
				memberBean.setAddress1(rs.getString("m.address1"));
				memberBean.setAddress2(rs.getString("m.address2"));
				memberBean.setBirth(rs.getString("m.birth"));
				memberBean.setEmail(rs.getString("m.email"));
				memberBean.setGender(rs.getString("m.gender"));
				memberBean.setImage(rs.getString("m.image"));
				memberBean.setName(rs.getString("m.name"));
				memberBean.setNo(rs.getInt("m.no"));
				memberBean.setPassword(rs.getString("m.password"));
				memberBean.setPhone(rs.getString("m.phone"));
				memberBean.setPostcode(rs.getInt("m.postcode"));
				memberBean.setReg_date(rs.getDate("m.reg_date"));
				memberBean.setType(rs.getString("m.type"));
				commentList1.add(commentBean);
				commentList2.add(memberBean);
			}
			commentList.add(commentList1);
			commentList.add(commentList2);
			beans.add(commentList);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return beans;
	}

	public int updateReadCount(int board_num) {
		System.out.println("updateReadCount 왔당");
		int updateCount = 0;
		String sql = "update board set readcount = readcount+1 where no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			updateCount = pstmt.executeUpdate();
			System.out.println("updateReadCount 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public boolean isArticleBoardWriter(int board_num, String email) {
		boolean isWriter = false;
		String sql = "select * from board where no = ?";
		int WriterNo=-1;
		int sessionNo=-2;
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("isArticleBoardWriter() board select 성공");
				System.out.println("board_member no : "+rs.getInt("member_no"));
				WriterNo = rs.getInt("member_no");
			}
			sql = "select * from member where email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			System.out.println("email값 : "+email);
			rs = pstmt.executeQuery();
			System.out.println(pstmt.toString());
			if(rs.next()) {
				System.out.println("isArticleBoardWriter() member select 성공");
				System.out.println("member no : "+rs.getInt("no"));
				sessionNo = rs.getInt("no");
			}
			if(sessionNo == WriterNo) {
				isWriter = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return isWriter;
	}

	public int updateArticle(BoardBean article) {
		int updateCount = 0;
//		String sql = "update board set title = ?, content = ?, file = ?, email_reply =? where no = ?";
		String sql = "update board set title = ?, content = ?, file = ? where no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setString(3, article.getFile());
//			pstmt.setInt(4, article.getEmail_reply());
			pstmt.setInt(4, article.getNo());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateArticle() 실패 : "+e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public int deleteArticle(int board_num) {
		String sql = "delete from board where no = ?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public boolean isConfirmByPass(String email, String member_pass) {
		boolean isConfirmByPass = false;
		String sql = "select * from member where email = ? and password = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
//			pstmt.setString(2, member_pass);
			pstmt.setString(2, AES256Cipher.getInstance().encryption(member_pass));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isConfirmByPass = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return isConfirmByPass;
	}

	
	
}
