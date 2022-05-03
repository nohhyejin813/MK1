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
	
	request.setCharacterEncoding("utf-8");
	
	// 게시판 인덱스 번호
	String str1 = request.getParameter("board_idx");
	int board_idx = Integer.parseInt(str1);
	
	// 페이지 번호
	String str2 = request.getParameter("page");
	int now_page = Integer.parseInt(str2);
	
	// 모든 데이터를 담아놓을 JSON Object
	JSONObject root = new JSONObject();
	
	// 게시판 정보를 가져오는 부분
	String sql = "select board_info_name from board_info_table where board_info_idx = ?";
	PreparedStatement pstmt = db.prepareStatement(sql);
	pstmt.setInt(1, board_idx);
	
	ResultSet rs = pstmt.executeQuery();
	rs.next();
	
	
	String board_info_name = rs.getString("board_info_name");
	
	root.put("board_info_name", board_info_name);
	
	// 게시글 목록 가져오는 부분
	String sql2 = "select p2.* from "
				+ "(select rownum as num, p1.* from "
				+ "(select a1.content_idx, a1.content_subject, "
	        	+ "to_char(a1.content_date, 'YYYY-MM-DD') as content_date, "
	        	+ "a2.user_name "
	        	+ "from content_table a1 , user_table a2 "
	        	+ "where a1.content_writer_idx = a2.user_idx "
	        	+ "and a1.content_board_idx=? "
	        	+ "order by content_idx desc"
	        	+") p1 ) p2 " 
	        	+"where p2.num >= ? and p2.num <=?";
	        	
	        	
	int idx_min = (now_page -1)	* 10 + 1;
	int idx_max = idx_min + 9;

	PreparedStatement pstmt2 = db.prepareStatement(sql2);
	pstmt2.setInt(1, board_idx);
	pstmt2.setInt(2, idx_min);
	pstmt2.setInt(3, idx_max);

	ResultSet rs2 = pstmt2.executeQuery();
	
	JSONArray board_list = new JSONArray();
	while(rs2.next()){
		JSONObject obj = new JSONObject();
		obj.put("content_idx", rs2.getInt("content_idx"));
		obj.put("content_subject", rs2.getString("content_subject"));
		obj.put("content_writer_name", rs2.getString("user_name"));
		obj.put("content_date", rs2.getString("content_date"));
		
		board_list.add(obj);
	}
	
	root.put("board_list", board_list);
	
	// 하단 페이지 번호들을 위한 데이터 셋팅
	String sql3 = "select count(*) as cnt from content_table where content_board_idx = ?";
	PreparedStatement pstmt3 = db.prepareStatement(sql3);
	pstmt3.setInt(1, board_idx);
	
	ResultSet rs3 = pstmt3.executeQuery();
	rs3.next();
	int cnt = rs3.getInt("cnt");
	
	int page_cnt = cnt/10;
	if(page_cnt % 10 > 0) {
		page_cnt++;
	}
	
	if (page_cnt == 0) {
		page_cnt++;
	}
	
	int page_min = ((now_page -1) / 10) * 10 + 1;
	int page_max = page_min + 9;
	
	if(page_max > page_cnt){
		page_max = page_cnt;
	}
	
	JSONArray page_array = new JSONArray();
	for(int i = page_min ; i<=page_max ; i++){
		page_array.add(i);
	}

	root.put("page_array", page_array);
	
	// 이전 버튼
	int pre = page_min -1;
	if(pre < 1) {
		pre = 1;
	}
	
	// 다음버튼
	int next = page_max + 1;
	if (next > page_cnt) {
		next = page_cnt;
	}
	
	root.put("pre", pre);
	root.put("next", next);
	
	db.close();
%>
<%= root.toJSONString() %>

	