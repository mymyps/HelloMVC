<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<!-- Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

	<style>
		div#updatePassword-container{
			background-color: red;
		}
		div#updatePassword-container table{
			margin : 0  auto;
			border-spacing: 20px;
		}
		div#updatePassword-container table tr:last-of-type td{
			text-align: center;
		}


	</style>

    <title>Document Title</title>
</head>
<body>
	<div id="updatePassword-container">
		<form action="<%=request.getContextPath() %>/member/updatePasswordEnd" name="updatePwdFrm" method="post">
			<table>
				<tr>
					<th>현재비밀번호</th>
					<td>
						<input type="password" name="password" id="password" required>
					</td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="passwordNew" id="passwordNew" required>
					</td>
					
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" name="passwordCk" id="passwordCk" required>
					</td>
				</tr>
				<tr colsapn='2'>
					<td>
						<input type="submit" value="변경" onclick="return password_valitate()">
						<input type="button" value="닫기" onclick="self.close()">
					</td>
				</tr>
			</table>
			<input type="hidden" name="userId" value='<%=(String)request.getAttribute("userId")%>'>
		</form>
	</div>

	<script>
		function password_valitate() {
		return true;
	}
	</script>

</body>
</html>