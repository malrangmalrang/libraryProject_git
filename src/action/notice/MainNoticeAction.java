package action.notice;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import action.Action;
import svc.MainNoticeService;
import vo.ActionForward;
import vo.BoardBean;

public class MainNoticeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MainNoticeService mainNoticeService = new MainNoticeService();
		JSONArray recentNoticeList = mainNoticeService.getRecentNoticeList();
		
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(recentNoticeList);
		out.flush();
		out.close();
		
		return null;
	}

}
