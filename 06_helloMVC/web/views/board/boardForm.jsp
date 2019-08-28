<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
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
<section id="notice-container">
	<div>
		<h2>게시글 작성</h2>
		<form action="<%=request.getContextPath() %>/board/boardFormEnd" method="post" enctype="multipart/form-data">
			<table id="tbl-notice">
				<tr>
					<th>제 목</th>
					<td><input type="text" name="title" required="required" /></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer"
						value="" readonly /></td>
						<%-- <%=memberLogin.getUserId()%> --%>
				</tr>
				<tr>
					<th>내 용</th>
					<td><textarea rows="5" cols="20" name="content"></textarea></td>
				</tr>
				<tr>
					<th>첨부 파일</th>
					<td><input type="file" name="up_file" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;"><input type="submit" value="등록하기"> <input
						type="reset" value="취 소"></td>
				</tr>
			</table>
		</form>
	</div>
</section>
<%@ include file="/views/common/footer.jsp" %>