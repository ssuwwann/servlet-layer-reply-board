<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member-join.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<main>
  <h2>회원가입</h2>
  <form action="/member" method="POST">
    <label for="loginid">이메일:
      <input type="text" name="loginid" id="loginid">
    </label>
    <a href="#">이메일 체크 - 나중에</a>
    <label for="password">비밀번호:
      <input type="text" name="password" id="password">
    </label>
    <label for="nickname">닉네임:
      <input type="text" name="nickname" id="nickname">
    </label>
    <div>
      <button>회원가입</button>
    </div>
  </form>
</main>
</body>
</html>
