<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Connection con = null;
	try {
		Context initCtx = new InitialContext(); // 톰캣의 컨텍스트 가져오기
		Context envCtx = (Context) initCtx.lookup("java:comp/env"); // context.xml 에 정의된 Resource 컨텍스트 가져오기
		DataSource ds = (DataSource) envCtx.lookup("jdbc/teamproject"); // Resource 컨텍스트로부터 DataSource 객체 가져오기
		con = ds.getConnection(); // CP(커넥션풀)에서 Connection 객체 가져오기

		out.println("<h1>DB 연결 성공!</h1>");
	}catch (Exception e) {
		out.println("<h1>연결 실패!</h1>");
		e.printStackTrace();
	}
%>
