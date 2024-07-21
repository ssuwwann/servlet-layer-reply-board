<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script type="module" src="${pageContext.request.contextPath}/resources/js/board/content.js"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<main>
  <form name="f" method="post">
    <table border="1" width="300" height="200"></table>
  </form>
</main>
</body>
</html>
