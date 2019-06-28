package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MainService;
import vo.ActionForward;
import vo.ReviewBean;
import vo.book.BookBean;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MainService mainFormService = new MainService();
		
        SimpleDateFormat mat=new SimpleDateFormat("HH:mm:ss");
        Date now=new Date();
        Date nowTime=null;
        Date startTime=null;
        Date endTime=null;
            nowTime = mat.parse(mat.format(now));
            startTime=mat.parse("00:00:00");
            endTime=mat.parse("00:01:00");

        
        System.out.println(nowTime);
        System.out.println(startTime);
        System.out.println(endTime);
        
        int compare1=nowTime.compareTo(startTime); 
        int compare2=nowTime.compareTo(endTime);
//      date1.compareTo(date2) => date1이 date2보다 이후날짜이면 1을 반환, 
//      그 반대의 경우 -1을 반환(date1, date2는 형      식(타입)이 같아야 한다.)
        
        if(compare1==1 && compare2==-1) {
        	System.out.println("00:00:00과 00:00:01시 사이임 함수작동");
            mainFormService.updateOverdue();
        }
        
        else {
        	System.out.println("00:00:00과 00:00:01시 사이가 아님 함수작동안함");
        }
		
		

		ArrayList<BookBean> bestBookList = mainFormService.getBestBookList();
		ArrayList<ReviewBean> recentReviewList = mainFormService.getRecentReviewList();
		
		request.setAttribute("bestBookList", bestBookList);
		request.setAttribute("recentReviewList", recentReviewList);
		
		ActionForward  forward = new ActionForward();
		forward.setPath("main.ma");
		
		return forward;
	}

}
