<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	String local = (String)request.getAttribute("local");
	String msgClose = (String)request.getAttribute("msgClose");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 창</title>
</head>
<body>
	<script>
		alert('<%=msg%>');
		//script가 있으면 페이지 닫기
		<%=msgClose!=null?msgClose:""%>
		//메인화면으로 이동!!
		location.href='<%=request.getContextPath()%><%=local%>';
	</script>
</body>
</html>