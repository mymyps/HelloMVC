<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.member.model.vo.Member"%>
 
<%
	List<Member> members = (List<Member>)request.getAttribute("members");
	/* System.out.println(members); */
	int cPage = (int)request.getAttribute("cPage");
	String searchType = (String)request.getAttribute("searchType");
	String searchKey = (String)request.getAttribute("searchKeyword");
%>
<style>
section#memberList-container {
	text-align: center;
}

section#memberList-container table#tbl-member {
	width: 100%;
	border: 1px solid gray;
	border-collapse: collapse;
}

section#memberList-container table#tbl-member th, table#tbl-member td {
	border: 1px solid gray;
	padding: 10px;
}

div#search-container {
	margin: 0 0 10px 0;
	padding: 3px;
	background-color: rgba(0, 188, 212, 0.3);
}

div#search-userName {
	display: none;
}

div#search-gender {
	display: none;
}

div#search-userId {
	display: inline-block;
}

section#memberList-container div#neck-container {
	padding: 0px;
	height: 50px;
	background-color: rgba(0, 188, 212, 0.3);
}

section#memberList-container div#search-container {
	margin: 0 0 10px 0;
	padding: 3px;
	float: left;
}

section#memberList-container div#numPerPage-container {
	float: right;
}

section#memberList-container form#numPerPageFrm {
	display: inline;
}
</style>
<%@ include file="/views/common/header.jsp" %>

	<section id="memberList-container">
		<h2>회원관리</h2>
		<div id="neck-container">
			<div id="search-container">
				검색타입 : 
				<select id="searchType">
					<option value="userId" <%="userId".equals(searchType)?"selected":"" %> >아이디</option>
					<option value="userName" <%="userName".equals(searchType)?"selected":"" %> >회원명</option>
					<option value="gender" <%="gender".equals(searchType)?"selected":"" %>>성별</option>
				</select>
				<div id="search-userId">
					<form action="<%=request.getContextPath() %>/admin/memberFinder">
						<input type="hidden" name="searchType" value="userId"/>
						<input type="hidden" name="cPage" value="<%=cPage%>"/>
						<input type="text" placeholder="아이디조회" 
						name="searchKeyword" size="25" <%="userId".equals(searchType)?"selected":searchKey %> />
						<button type="submit">검색</button>
					</form>
				</div>
				<div id="search-userName">
					<form action="<%=request.getContextPath() %>/admin/memberFinder">
						<input type="hidden" name="searchType" value=""/>
						<input type="hidden" name="cPage" value="<%=cPage%>"/>
						<input type="text" placeholder="회원명으로 조회" 
						name="searchKeyword" size="25" <%="userName".equals(searchType)?"selected":searchKey %> />
						<button type="submit">검색</button>
					</form>
				</div>
				<div id="search-gender">
					<form action="<%=request.getContextPath() %>/admin/memberFinder">
						<input type="hidden" name="searchType" value="gender"/>
						<input type="hidden" name="cPage" value="<%=cPage%>"/>
						<label><input type="radio" name="searchKeyword"
						value="M" <%="M".equals(searchKey)?"checked":"" %>>남</label>
						<label><input type="radio" name="searchKeyword"
						value="F" <%="F".equals(searchKey)?"checked":"" %>>여</label>
						<button type="submit">검색</button>
					</form>
				</div>				
			</div>
		</div>
		
		<table id="tbl-member">
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>성별</th>
					<th>나이</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>취미</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<%if(members!=null&&!members.isEmpty()){
					for(Member m: members){ %>
					<tr>
						<td>
							<a href="<%=request.getContextPath()%>/mypage.do?userId=<%=m.getUserId()%>"><%=m.getUserId()%></a>
						</td>
						<td><%=m.getUsreName()%></td>
						<td><%=m.getGender()=='M'?"남":"여"%></td>
						<td><%=m.getAge()%></td>
						<td><%=m.getEmail()%></td>
						<td><%=m.getPhone()%></td>
						<td><%=m.getAddress()%></td>
						<td><%=m.getHobby()%></td>
						<td><%=m.getEnrollDate()%></td>
					</tr>
				<% }//for
				}//if %>
			</tbody>
		</table>
		<div id="pageBar">
			<%=request.getAttribute("pageBar") %>
		</div>
	</section>

<script type="text/javascript">


	$(function() {
		var sid = $("#search-userId");
		var sname = $("#search-userName");
		var sgender = $("#search-gender");
		var searchType = $("#searchType");


		searchType.change(function() {
			sid.hide();
			sname.hide();
			sgender.hide();
			$("#search-" + this.value).css("display", "inline-block");
		});
		$('#searchType').trigger('change');


	});
</script>
	
<%@ include file="/views/common/footer.jsp" %>