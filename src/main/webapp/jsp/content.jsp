<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script type="module" src="${pageContext.request.contextPath}/resources/js/board/content.js"></script>
  <script type="module" src="${pageContext.request.contextPath}/resources/js/reply/reply.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/content.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<main>
  <form name="f" method="post">
    <table border="1" width="300" height="200"></table>
  </form>
  <div id="imgWrapper"></div>
  <div id="replyWrapper">
    <div id="replyWrite">
      <div contenteditable="true"></div>
      <button>댓글쓰기</button>
    </div>
    <div id="replyArea"></div>
  </div>
</main>
</body>
</html>