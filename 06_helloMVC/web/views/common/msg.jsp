<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	String local = (String)request.getAttribute("local");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 창</title>
</head>
<body>
	<script type="text/javascript">
	alert('<%=msg%>');
	location.href='<%=request.getContextPath()%><%=local%>';
</script>
</body>
</html>