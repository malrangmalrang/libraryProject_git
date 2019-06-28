package svc;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;
import static db.JdbcUtil.*;
public class MemberInfoService {
	public MemberBean getMemberInfo(MemberBean memberBean) {
		System.out.println("getMemberInfo()");
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		System.out.println("MemberInfoService : 1");
		System.out.println("MemberInfoService email : "+memberBean.getEmail());
		memberBean = dao.selectMemberBean(memberBean.getEmail());
		System.out.println("MemberInfoService : 2");
		System.out.println("getMemberInfo memberBean Name : "+memberBean.getName());
		close(con);
		
		return memberBean;
	}
}
