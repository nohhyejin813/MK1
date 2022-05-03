<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="org.json.simple.*" %>
<%
	// 데이터 베이스 접속 정보
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "scott";
	String pw = "1234";
	
	// 접속
	Class.forName(driver);
	Connection db = DriverManager.getConnection(url, id, pw);
	
	String str1 = request.getParameter("content_idx");
	int content_idx = Integer.parseInt(str1);
	
	String sql = "delete from content_table where content_idx = ?";
	PreparedStatement pstmt = db.prepareStatement(sql);
	pstmt.setInt(1, content_idx);
	
	pstmt.execute();
	db.close();
	
%>	
{
	"result" : true
}