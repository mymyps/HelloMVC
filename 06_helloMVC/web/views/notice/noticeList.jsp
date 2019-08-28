ㅌ<%@page import="com.kh.notice.model.vo.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Notice> list = (List)request.getAttribute("notice");
	//System.out.println("//" + list);
	String pageBar = (String)request.getAttribute("pageBar");
	//System.out.println("//// " + pageBar);
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
	width: 100%;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
}

table#tbl-notice th, table#tbl-notice td {
	border: 1px solid black;
	padding: 5px 0;
	text-align: center;
}

div {
	border: 1px solid black;
}

input#btn-add {
	float: right;
	margin: 0 0 15px;
}
</style>

<section id="notice-container">
	<div class="container">
		<h2>공지사항</h2>
		<%if(memberLogin!=null&&memberLogin.getUserId().equals("admin")) {%>
			<input type="button" value="글쓰기" id="btn-add" 
			onclick="writeNotice();"/>
		<%} %>
		<table id="tbl-notice">
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>첨부파일</td>
				<td>작성일</td>
			</tr>
			<%for(Notice n : list){%>
			<tr>
				<td><%=n.getNoticeNo()%></td>
				<td><a
					href="<%=request.getContextPath()%>/notice/noticeView?no=<%=n.getNoticeNo()%>">
						<%=n.getNoticeTitile() %></a></td>
				<td><%=n.getNoticeWriter() %></td>
				<td>
					<%if(n.getFilePath() != null){%> <img
					src='<%=request.getContextPath()%>/images/file.png' width='16px'
					height='16px' /> <%}%>
				</td>
				<td><%=n.getNoticeDate() %></td>
			</tr>
			<%}%>
		</table>
		<div id="pageBar">
			<%=pageBar%>
		</div>
	</div>
	<script>

	function writeNotice(){
		//페이지이동!
		location.href='<%=request.getContextPath()%>/notice/noticeForm';
	}
	</script>
</section>

<%@ include file="/views/common/footer.jsp"%>