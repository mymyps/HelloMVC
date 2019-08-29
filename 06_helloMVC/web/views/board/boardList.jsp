<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.board.model.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Board> list = (List)request.getAttribute("board");
	String pageBar = (String)request.getAttribute("pageBar");
	int cpage = (int)request.getAttribute("cPage");
%>
<%@ include file="/views/common/header.jsp"%>
<style>
section#board-container {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

section#board-container h2 {
	margin: 10px 0;
}

table#tbl-board {
	width: 100%;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

table#tbl-board th, table#tbl-board td {
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}
/*글쓰기버튼*/
input#btn-add {
	float: right;
	margin: 0 0 15px;
}
/*페이지바*/
div#pageBar {
	margin-top: 10px;
	text-align: center;
	background-color: rgba(0, 188, 212, 0.3);
}

div#pageBar span.cPage {
	color: #0066ff;
}
</style>
<!-- 보드 -->
<section id="board-container">
	<h2>게시판</h2>
	<%if(memberLogin != null){ %>
	<input type="button" value="글쓰기" id="btn-add" onclick="writeNotice()"/>
	<%} %>
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>첨부파일</th>
			<th>조회수</th>
		</tr>
		<%for(Board b : list){ %>
		<tr>
			<td><%=b.getBoardNo() %></td>
			<!-- 게시판 제목 클릭 -->
			<td>
				<a href="<%=request.getContextPath()%>/board/boardView?no=<%=b.getBoardNo()%>">
					<%=b.getBoardTitile() %>
				</a>
			</td>
			
			
			<td><%=b.getBoardWriter() %></td>
			<!-- 파일 패스 -->
			<td>
				<%if(b.getBoardRenamedFileName() != null){ %>
					<img src='<%=request.getContextPath()%>/images/file.png' width="16px" alt="not null" />	
				<%}else{ %>
					<%=b.getBoardRenamedFileName() %> / 꽝
				<%} %>
			</td>
			<td><%=b.getBoardReadCount() %></td>
		</tr>
		<%} %>
	</table>
	<!-- 페이징 처리 -->
	<div id="pageBar">
		<%=pageBar%>
	</div>
	<script>
	function writeNotice(){
		//페이지이동!
		location.href='<%=request.getContextPath()%>/views/board/boardForm.jsp';
		
	}
	</script>
</section>
<%@ include file="/views/common/footer.jsp"%>