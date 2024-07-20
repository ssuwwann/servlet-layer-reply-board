<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>웰컴!</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<main>
  <h2>웰컴페이지</h2>
  <c:choose>
    <c:when test="${empty member}">
      <a href="${pageContext.request.contextPath}/member/join">회원가입</a>
      <a href="${pageContext.request.contextPath}/member/login">로그인</a>
    </c:when>
    <c:otherwise>
      <a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
    </c:otherwise>
  </c:choose>
  <a href="${pageContext.request.contextPath}/board/list?size=10&page=1">게시글 목록</a>
</main>
</body>
</html>
