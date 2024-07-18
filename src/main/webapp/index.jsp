<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>웰컴!</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<main>
  <h2>웰컴페이지</h2>
  <a href="${pageContext.request.contextPath}/member/join">회원가입</a>
  <a href="${pageContext.request.contextPath}/board">게시글 목록</a>
</main>
</body>
</html>
