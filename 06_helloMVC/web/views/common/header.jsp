<%@page import="com.kh.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/* Member memberLogin = (Member)request.getAttribute("loginMember"); */
	Member memberLogin = (Member)session.getAttribute("loginMember");
	String saveId = null;
	/*
	쿠키값을 확인해서 페이지에 반영 
	쿠기는 key:value 형식으로 여러개 저장이 가능하기 때문에 쿠기객체가 배열로 저장됨.
	*/
	Cookie[] cookies = request.getCookies();
	
	if (cookies != null) {
		for (Cookie c : cookies) {
			// key, value 메소드를 통해 가져올 수 있음
			String key = c.getName();
			String value = c.getValue();
			//System.out.println("key : " + key + " / value : " + value);
			if (key.equals("saveId")) {
				saveId = value;
				//System.out.println(saveId);
			}
		}//for
	}//if
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>

<title>Document</title>
</head>

<body>
    <header>
        <h1>HelloMVC</h1>
        <!--로그인 메뉴-->
        <div class="login-container">
        <%
        	if(memberLogin == null){
        %>
            <form id='loginFrm' 
            action="<%=request.getContextPath()%>/login.do"
            method="post" onsubmit="return validate()"> <!-- validate() 유효성 검사 입력확인 -->
                <table>
                    <tr>
                        <td>
                            <input type="text" name="userId" id="userId"
                            placeholder="비밀번호" value="<%=saveId!=null?saveId:""%>"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" name="password" id="password"
                            placeholder="비밀번호"/>
                        </td>
                        <td>
                            <input type="submit" value="로그인"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan='2'>
                            <input type="checkbox" name="saveId"
                            id="saveId" <%=saveId!=null?"checked":""%> />
                            <label for="saveId">아이디저장</label>
                            <input type="button" value="회원가입" onclick="location.href='<%=request.getContextPath()%>/memberEnroll'">
                        </td> 
                    </tr>
                </table>
            </form>
            <% }else{ %>
            	<table id="loged-in">
            		<tr>
            			<td>
            				<%=memberLogin.getUserId()%>님 환영합니다.
            			</td>
            		</tr>
            		<tr>
            			<td>
            				<% %>
            				<input type="button" value="내정보 보기" onclick="location.href='<%=request.getContextPath()%>/mypage.do?userId=<%=memberLogin.getUserId()%>'" />
            				<!-- <input type="button" value="로그아웃" onclick="logout()" /> -->
            				<input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath()%>/logout.do'" />
            			</td>
            		</tr>
            	</table>
            <% } %>
		</div>
		
        <nav>
            <ul class="main-nav">
                <li class="home">
                    <a href="<%=request.getContextPath()%>">Home</a>
                </li>
                <li id="notice">
                    <a href="<%=request.getContextPath()%>/notice/noticeList">공지사항</a>
                </li>
                <li>
                    <a href="#">게시판</a>
                </li>
                <li>
                    <a href="#">사진게시판</a>
                </li>
				<!-- 관리자 페이지 -->
				<% if((memberLogin != null) && memberLogin.getUserId().equals("admin")){ %>
	                <li id="admin-member">
	                	<a href="<%=request.getContextPath()%>/admin/memberList">
	                	<span class='animate_line'>회원관리</span></a>
	                </li>
                <%} %>
            </ul>
        </nav>
	</header>
	
	<script>
		function validate() {
			// console.log( "aaa  "+ $('#userId').val().length);
			// console.log($('#userId').val.length);
			
			if($('#userId').val().length == 0){
				alert('id 입력하세요');
				$('#userId').focus();
				return false;
			}
			if( $('#password').val().length == 0 ){
				alert('password 입력하세요');
				$('#password').focus();
				return false;
			}
			return true;
		}
		
		<%-- function logout() {
			<% session.invalidate(); %>
			<%
				memberLogin = null;
				session.invalidate();
				
			%>
			console.log("로그아웃")
		} --%>
		
	</script>
    
    