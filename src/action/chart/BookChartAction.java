package action.chart;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.chart.BookChartService;
import vo.ActionForward;

public class BookChartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		BookChartService bookChartService = new BookChartService();
		ArrayList<HashMap<String, Object>> categoryCount = bookChartService.categoryCount();
		request.setAttribute("categoryCount", categoryCount);
		
		forward = new ActionForward();
		forward.setPath("guideChartForm.gd");
		
		return forward;
	}

}
