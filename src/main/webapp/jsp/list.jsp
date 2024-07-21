<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/list.css">
  <script defer type="module" src="${pageContext.request.contextPath}/resources/js/board/list.js"></script>
  <script defer type="module" src="${pageContext.request.contextPath}/resources/js/api/board-api.js"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<main>
  <div>
    <a href="/">돌아가기</a>
    <a href="write">글쓰러 가기</a>
  </div>

  <content>
    <table>
      <thead>
      <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>내용</th>
        <th>조회수</th>
        <th>좋아요</th>
        <th>작성일</th>
      </tr>
      </thead>
      <tbody>
      </tbody>
    </table>

    <div id="pagination">
      <select name="size">
        <option value="3">3</option>
        <option value="5">5</option>
        <option value="10">10</option>
      </select>
      <span></span>
    </div>

  </content>

</main>
</body>
</html>
