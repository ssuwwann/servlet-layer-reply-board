<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/list.css">
  <script type="module" src="${pageContext.request.contextPath}/resources/js/api/board-api.js"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<main>
  <a href="/">돌아가기</a>
  <a href="write">글쓰러 가기</a>
</main>
</body>
</html>
