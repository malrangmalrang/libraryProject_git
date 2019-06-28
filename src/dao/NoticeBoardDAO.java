package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static db.JdbcUtil.*;
import vo.BoardBean;

public class NoticeBoardDAO {
	// -------------------------------------------------------------------
	private NoticeBoardDAO() {}
	private static NoticeBoardDAO instance;
	public static NoticeBoardDAO getInstance() {
		if(instance == null) {
			instance = new NoticeBoardDAO();
		}
		
		return instance;
	}
	// -------------------------------------------------------------------
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void setConnection(Connection con) {
		this.con = con; 
	}

	public int writeNotice_DAO(BoardBean boardBean) {
		
		int no = 0;
		int writeSuccess=0;
		String sql="SELECT MAX(no) FROM board";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				no=rs.getInt(1)+1;
			}
			
			sql="INSERT INTO board values(?,?,?,?,?,?,now(),?)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);;
			pstmt.setString(2, boardBean.getTitle());
			pstmt.setString(3,boardBean.getContent());
			pstmt.setInt(4,0);
			pstmt.setString(5, boardBean.getFile());
			pstmt.setString(6, "notice");
			pstmt.setInt(7,boardBean.getMember_no());
			writeSuccess=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("INSERT에러: " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return writeSuccess;
	}

	public int selectListCount() {

       int listCount =0;
       
       String sql="SELECT count(*) FROM board where board_type='notice'";
       
       
        try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
		if(rs.next()) {
			listCount=rs.getInt(1);
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectListCount() 실패! : "+e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
			
		}
		return listCount;
	}

	public ArrayList<BoardBean> selectArticleList(int page, int limit) {
		
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean boardBean = null;
		
		int startRow = (page - 1) *10; 
		
		String sql ="SELECT * FROM board where board_type='notice' ORDER BY reg_date desc";
		
		
		try {
			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, startRow);필요없음                                                    
			rs=pstmt.executeQuery();
	   
	    while(rs.next()) {
	      boardBean = new BoardBean();
	    	
	      boardBean.setNo(rs.getInt("no"));
	      boardBean.setTitle(rs.getString("title"));
	      boardBean.setContent(rs.getString("content"));
	      boardBean.setReg_date(rs.getDate("reg_date"));
		  boardBean.setReadcount(rs.getInt("Readcount"));
		  boardBean.setFile(rs.getString("file"));
		  boardBean.setBoard_type(rs.getString("board_type"));
		  boardBean.setMember_no(rs.getInt("member_no"));
	      
	      articleList.add(boardBean);
	      System.out.println(rs.getInt("no"));
	      
	    }
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectArticleList() 실패!: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
			
		}
		return articleList;
	}

	public int selectListCount(String search) {

	       int listCount =0;
	       
	       String sql="SELECT count(*) FROM board where board_type='notice' and title like ?";
	       
	        try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				rs=pstmt.executeQuery();
				System.out.println("search pstmt : "+pstmt);
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("selectListCount() 실패! : "+e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
				
			}
			return listCount;
		}

		public ArrayList<BoardBean> selectArticleList(int page, int limit,String search) {
			
			ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
			BoardBean boardBean = null;
			
			int startRow = (page - 1) *10; 
			
		String sql="SELECT * FROM board where board_type='notice' and title like ? order by no desc limit ?,?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2,startRow);
				pstmt.setInt(3, limit);
				rs=pstmt.executeQuery();
		   
		    while(rs.next()) {
		      boardBean = new BoardBean();
		      boardBean.setNo(rs.getInt("no"));
		      boardBean.setTitle(rs.getString("title"));
		      boardBean.setContent(rs.getString("content"));
		      boardBean.setReg_date(rs.getDate("reg_date"));
			  boardBean.setReadcount(rs.getInt("Readcount"));
			  boardBean.setFile(rs.getString("file"));
			  boardBean.setBoard_type(rs.getString("board_type"));
			  
		      
		      articleList.add(boardBean);
		      System.out.println(rs.getInt("no"));
		      
		    }
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("selectArticleList() 실패!: " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
				
			}
			return articleList;
		}
	
	
	
	public BoardBean selectArticle(int no) {
		
		BoardBean boardBean =null;
		
		String sql="SELECT * FROM board WHERE no=?"; 
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				boardBean = new BoardBean();
				
				boardBean.setNo(rs.getInt("no"));
				boardBean.setTitle(rs.getString("title"));
				boardBean.setContent(rs.getString("content"));
				boardBean.setReg_date(rs.getDate("reg_date"));
				boardBean.setReadcount(rs.getInt("Readcount"));
				boardBean.setFile(rs.getString("file"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectArticle() 실패!: " + e.getMessage());
		}finally{
			close(rs);
			close(pstmt);
		}
		return boardBean;
	}

	public int updateReadcount(int no) {
		System.out.println("updateReadcount"+no);
		int updateCount=0;
		
		String sql="UPDATE board SET readcount=readcount+1 WHERE no=?";
				
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			updateCount=pstmt.executeUpdate();
			System.out.println(updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectArticle()실패!: "+e.getMessage());
			
		}finally {
			close(pstmt);
		}
		return updateCount;
	}

	public int deleteArticle(int no) {
		int deleteCount=0;
		
		String sql="DELETE FROM board WHERE no=?"; 
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			deleteCount=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("deleteArtcle()실패!: "+e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}

	public boolean isArticleNoticeWriter(int member_no) {
		
	 boolean isWriter = false;
	 
	 String sql="SELECT * FROM board WHERE member_no=?";
	 
	try {
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, member_no);
		rs=pstmt.executeQuery();
		
		   if(rs.next()) {
					isWriter=true;
			
		   }
		
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("isArticleNotcieWriter()실패! : " +e.getMessage());
	}finally {
		close(rs);
		close(pstmt);
	}
		return isWriter;
	}

	public int updateArticle(BoardBean article) {
		
	 int updateCount=0;
	 
	 String sql="UPDATE board SET title=?, content=?,file=? WHERE no=?";
	 
	 try {
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, article.getTitle());
		pstmt.setString(2, article.getContent());
		pstmt.setString(3, article.getFile());
		pstmt.setInt(4, article.getNo());
		updateCount=pstmt.executeUpdate();
	} catch (SQLException e) {
		System.out.println("updateArticle()실패!: " + e.getMessage());
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
		return updateCount;
	}

}




















