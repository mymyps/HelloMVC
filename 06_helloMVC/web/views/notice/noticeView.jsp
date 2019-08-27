<%@page import="com.kh.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Notice n = (Notice)request.getAttribute("notice");
	
%>
<%@ include file="/views/common/header.jsp"%>
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
<section id='notice-container'>
	<table id="tbl-notice">
		<tr>
			<th>제목</th>
			<td><%=n.getNoticeTitile() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=n.getNoticeWriter() %></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<%if(n.getFilePath() != null){%> <img
				src='<%=request.getContextPath()%>/images/file.png' width='16px'
				height='16px' /> <%}%>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=n.getNoticeContent()%></td>
		</tr>
		<tr>
			<td colspan='2' style="text-align: center;"><input type="button"
				value="수정하기" onclick="" /> <input type="button" value="삭제하기"
				onclick="" /></td>
		</tr>
	</table>

</section>
<%@ include file="/views/common/footer.jsp"%>