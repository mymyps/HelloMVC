<%@page import="com.kh.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Board b = (Board)request.getAttribute("board");
	//System.out.println(b);
%>
<%@ include file="/views/common/header.jsp" %>
<style>
section#notice-container {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

section#notice-container h2 {
	margin: 10px 0;
}

table#tbl-notice {
	width: 500px;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

table#tbl-notice th {
	width: 125px;
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

table#tbl-notice td {
	border: 1px solid;
	padding: 5px 0 5px 10px;
	text-align: left;
}
</style>
    <section id='board-container'>
    	<table id="tbl-board">
			<tr>
				<th>제목</th>
				<td><%=b.getBoardTitile() %></td>
				<th>작성자</th>
				<td><%=b.getBoardWriter() %></td>
				<th>첨부파일</th>
				
					<%-- <td>
						<%if(b.getBoardOriginalFileName() != null){%> 
							<a href="javascript:fn_fileUp('<%=b.getBoardOriginalFileName()%>')">
								<img src="<%=request.getContextPath() %>/images/file.png" width="16px" alt="" />
							</a>
						<%}%>
					</td>
					
					<script>
						function fn_fileUp(filename) {
							var file = encodeURIComponent(fileName);
							location.href="<%=request.getContextPath()%>/notice/noticeFileDown?fileName=" + file;
						}
					</script> --%>
				<td><%=b.getBoardRenamedFileName() %></td>
				<th>조회수</th>
				<td><%=b.getBoardContent() %></td>
			</tr>
		</table>
    </section>
<%@ include file="/views/common/footer.jsp" %>


























