<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<main>
  <h2>로그인</h2>
  <form action="/member?m=login" method="post">
    <label for="loginid">아이디
      <input type="text" name="loginid" id="loginid">
    </label>
    <label for="password">비밀번호:
      <input type="text" name="password" id="password">
    </label>
    <div>
      <button>전송</button>
    </div>
  </form>
</main>
</body>
</html>
