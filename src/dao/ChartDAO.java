package dao;

import static db.JdbcUtil.*;
import static vo.book.DbCode.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ChartDAO {
	
	private static ChartDAO instance;
	
	private ChartDAO() {}
	
	public static ChartDAO getInstance() {
		if(instance == null) {
			instance = new ChartDAO();
		}
		
		return instance;
	}
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<HashMap<String, Object>> categoryCount() {
		ArrayList<HashMap<String, Object>> categoryCount = null;
		String sql = "SELECT COUNT(*) FROM book"; // 전체 책 갯수
		String sql2 = "SELECT category, COUNT(*) FROM book GROUP BY category"; // 카테고리별 책 갯수
		
		int bookTotalCount = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bookTotalCount = rs.getInt(1);
			}
			
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			
			categoryCount = new ArrayList<HashMap<String, Object>>();
			while(rs.next()) {
				HashMap<String,Object> categoryValue = new HashMap<String, Object>();
				String category = "";
				
				switch(rs.getString(1)) {
					case "A" : category = BOOK_CATEGORY_A; break;	
					case "B" : category = BOOK_CATEGORY_B; break;
					case "C" : category = BOOK_CATEGORY_C; break;
					case "D" : category = BOOK_CATEGORY_D; break;
					case "E" : category = BOOK_CATEGORY_E; break;
					case "F" : category = BOOK_CATEGORY_F; break;
					case "G" : category = BOOK_CATEGORY_G; break;
					case "H" : category = BOOK_CATEGORY_H; break;
				}
				
				categoryValue.put("category", rs.getString(1));
				categoryValue.put("category_name", category);
				categoryValue.put("count", rs.getInt(2));
				categoryValue.put("per", (int)(rs.getInt(2) * 1.0 / bookTotalCount * 1000 + 0.5) / 10.0);
				
				categoryCount.add(categoryValue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(ArithmeticException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return categoryCount;
	}
	
	
}
