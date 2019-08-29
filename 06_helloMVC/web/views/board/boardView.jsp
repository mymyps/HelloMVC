<%@page import="com.kh.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Board b = (Board)request.getAttribute("board");
	//System.out.println(b);
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
	width: 500px;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

table#tbl-board th {
	width: 125px;
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

table#tbl-board td {
	border: 1px solid;
	padding: 5px 0 5px 10px;
	text-align: left;
}

div#comment-container button#btn-insert {
	width: 60px;
	height: 50px;
	color: white;
	background: #3300ff;
	position: relative;
	top: -20px;
}
</style>
<section id='board-container'>
	<h2>게시판 내용</h2>
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th>
			<th>조회수</th>
		</tr>
		<tr>
			<td><%=b.getBoardNo()%></td>
			<td><%=b.getBoardTitile() %></td>
			<td><%=b.getBoardWriter() %></td>
			<td><%=b.getBoardDate()%></td>
			<td>
				<% if (b.getBoardRenamedFileName() != null) {%>
					<a href="javascript:fn_fileDown('<%=b.getBoardOriginalFileName()%>','<%=b.getBoardRenamedFileName()%>')">
						<img src="<%=request.getContextPath()%>/images/file.png" width="16px"/><%=b.getBoardOriginalFileName()%>
					</a> 
				<%}%>
			</td>
			<%-- <td><%=b.getBoardRenamedFileName()%></td> --%>
			<td><%=b.getBoardReadCount() %></td>
		</tr>
		
		
		<tr>
			<th colspan="6" style="text-align:center;">내 용</th>
		</tr>
		<tr>
			<td colspan="6" style="text-align:center;"><%=b.getBoardContent() %></td>
		</tr>
		<%
		if ( memberLogin != null && ( memberLogin.getUserId().equals(b.getBoardWriter()) || memberLogin.getUserId().equals("admin") ) ){
			//System.out.println( memberLogin.getUserId());
			//System.out.println( memberLogin.getUserId().equals("admin") );
		%>
		  
		<tr>
			<td colspan="6" style="text-align:center;">
				<input type="submit" value="수정하기">
				<input type="reset" value="취 소">
			</td>
		</tr>
		<%}%>
	</table>
	<!-- 댓글화면 구현 -->
	<div class="comment-container">
		<div class="comment-editor">
			<form action="<%=request.getContextPath() %>/boardComment/commentInsert" method="post">
				<input type="hidden" name="boardRef" value="<%=b.getBoardNo()%>">
				<input type="hidden" name="boardCommentWriter" value='<%=memberLogin!=null?memberLogin.getUserId():""%>'>
				<input type="hidden" name="boardCommentLevel" value="1">
				<input type="hidden" name="boardCommentRef" value="0">
				<textarea name="boardCommentContent" id="" cols="60" rows="3"></textarea>
				<button type="submit" id="btn-insert">등록</button>
			</form>

		</div>
	</div>
	<script>
		function fn_fileDown(oriN, reN) {
			var file = encodeURIComponent(oriN);
			location.href="<%=request.getContextPath()%>/board/boardFileDown?orifileName="+file+"&refileName="+reN;
		};
		$(function () {
			$('textarea[name=boardCommentContent]').focus(function () {
				if(<%=memberLogin == null%>){
					alert('로그인 후 등록 할수 있습니다');
					$('#userId').focus();
				}
			})
		})
	</script>


</section>
<%@ include file="/views/common/footer.jsp"%>


























