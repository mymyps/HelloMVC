<%@page import="com.kh.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Member m = (Member)request.getAttribute("memberInfo");

	String[] hobbyCk = new String[5];
	if (m.getHobby() != null) {
		String[] hobbys = m.getHobby().split(",");

		for (String s : hobbys) {
			switch (s) {
			case "운동":
				hobbyCk[0] = "checked";
				break;
			case "등산":
				hobbyCk[1] = "checked";
				break;
			case "독서":
				hobbyCk[2] = "checked";
				break;
			case "게임":
				hobbyCk[3] = "checked";
				break;
			case "여행":
				hobbyCk[4] = "checked";
				break;
			}
		}
	}
	
	
%>

<%@ include file="/views/common/header.jsp"%>
<section id="enroll-container">
	<div>
		<h2>회원 정보수정</h2>
		<form action="" id="memberFrm" method="post"
			onsubmit="return update_validate()">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="userId"
						value="<%=m.getUserId()%>" id="userId_" readonly></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" id="userName" name="userName"
						value="<%=m.getUsreName() %>" required></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="number" name="age" id="age"
						value="<%=m.getAge()%>"><br></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" id="email"
						value="<%=m.getEmail()%>"><br /></td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td><input type="tel" value="<%=m.getPhone()%>" name="phone"
						id="phone"><br /></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="address" id="address"
						value="<%=m.getAddress()%>"><br></td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<% if(m.getGender() == 'M'){ %> 
							<input type="radio" name="gender" id="gender0" value="M" checked>
							<label for="gender0"> 남 </label>
							<input type="radio" name="gender" id="gender1" value="F">
							<label for="gender1"> 여 </label> 
						<% }else{%> 
							<input type="radio" name="gender" id="gender0" value="M"> 
							<label for="gender0"> 남 </label>
							<input type="radio" name="gender" id="gender1" value="F" checked>
							<label for="gender1"> 여 </label> 
						<%} %>
					</td>
				</tr>
				<tr>
					<th>취미</th>
					<td>
						<input type="checkbox" name="hobby" id="hobby0" value="운동" <%=hobbyCk[0] %>>
						<label for="hobby0">운동</label>
						<input type="checkbox" name="hobby" id="hobby1" value="등산" <%=hobbyCk[1] %>> 
						<label for="hobby1">등산</label>
						<input type="checkbox" name="hobby" id="hobby2" value="독서" <%=hobbyCk[2] %>> 
						<label for="hobby2">독서</label> 
						<input type="checkbox" name="hobby" id="hobby3" value="게임" <%=hobbyCk[3] %>>
						<label for="hobby3">게임</label> 
						<input type="checkbox" name="hobby" id="hobby4" value="여행" <%=hobbyCk[4] %>> 
						<label for="hobby4">여행</label>
					</td>
				</tr>
			</table>
			<input type="button" onclick="updatePwd()" value="비밀번호 변경" />
			<input type="button" onclick="updateMember()" value="회원 정보수정"/>
			<input type="button" onclick="deleteMember()" value="탈퇴"/>
			
		</form>
		<!-- <form name="checkIdDuplicateFrm" method="post" target="" action="">
			<input type="hidden" name="userId" />
		</form> -->
	</div>
</section>

<script type="text/javascript">

	function updatePwd() {
		
		var url = "<%=request.getContextPath()%>/member/updatePassword?userId=<%=memberLogin.getUserId()%>";
		var title ="updatePassword";
		var status = "left=500px, top=200px width=400px, height=220px";
		open(url, title, status);
		
	}

	function updateMember() {
		var frm = $('#memberFrm');
		var url = '<%=request.getContextPath()%>/member/memberUpdate';
		
		frm.attr('action', url);
		frm.submit();
		
	}
	
	function deleteMember() {
		var frm = $('#memberFrm');
		var url = '<%=request.getContextPath()%>/member/memberDelete';
	
		var memDel = confirm( '정말 탈퇴할래?' );
		// console.log(memDel);
		
		if(memDel == true){
			frm.attr('action', url);
			frm.submit();
		}
		// var url = '<%=request.getContextPath()%>/member/memberDelete?userId=' + $('#userId_').val();
		// location.href = url;

	}
	
	function update_validate() {
		return true;
	}
</script>
<%@ include file="/views/common/footer.jsp"%>
