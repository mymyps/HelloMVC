<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    boolean isUseable = (boolean)request.getAttribute("isUserId");
	String userId = (String)request.getAttribute("userId");
	//System.out.println(isUseable + " / " + userId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    div#checkid-container{
        text-align: center;
        padding-top: 50xp;
    }
    span#duplicate{
        color: red;
        font-weight: bold;
    }
</style>

<title>ID 중복검사</title>
</head>
<body>
    <div id="checkid-container">
        <%if(isUseable){%>
            [<span><%=userId%></span>]는 사용이 가능합니다.
            <br><br>
            <button type="button" onclick="selfClose()">닫기</button>
            
        <%} else{%>
        	[<span id='duplicate'><%=userId%></span>]는 이미 사용중입니다.
            <br /><br />
            <form action="<%=request.getContextPath()%>/checkIdDuplicate" method="POST" name="checkId">
                <input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요">&nbsp;&nbsp;
                <button type="button" onclick="chekckIdDuplicate()">중복검사</button>

            </form>
        <%}%>

    </div>
    
    <script type="text/javascript">

        function selfClose() {
            opener.document.getElementById("userId_").value = '<%=userId%>';
            //opener.document.getElementById("userId_").setAttribute('disabled', 'true');
            opener.document.getElementById('password_').focus();
            self.close();
        }
        
        function chekckIdDuplicate() {
            var userId = document.getElementById('userId').value;
            if(!userId || userId.trim().length <4 ){
                alert('아이디는 4글자 이상!')
                return;
            }

            checkId.userId.value = userId.trim();
            checkId.submit(); // form tag(name=checkId)
        }
	</script>
    

</body>
</html>