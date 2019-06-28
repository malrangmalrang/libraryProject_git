package svc.chart;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import dao.ChartDAO;

import static db.JdbcUtil.*;

public class BookChartService {

	public ArrayList<HashMap<String, Object>> categoryCount() {

		Connection con = getConnection();
		ChartDAO chartDAO = ChartDAO.getInstance();
		chartDAO.setConnection(con);
		
		ArrayList<HashMap<String, Object>> categoryCount = chartDAO.categoryCount();
		
		close(con);
		
		return categoryCount;
	}

}
