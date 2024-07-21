<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script defer type="module" src="${pageContext.request.contextPath}/resources/js/board/write.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/content.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<main>
  <form name="f" action="${request.getContext()}/board" method="post" enctype="multipart/form-data">
    <table border="1" width="300" height="200">
      <tr>
        <td width="30%" colspan="2" align="center"><h2>입력폼</h2></td>
      </tr>
      <tr>
        <th>작가</th>
        <td><input type="text" name="name"></td>
      </tr>
      <tr>
        <th width="30%">제목</th>
        <td><input name="title" size="20"></td>
      </tr>
      <tr>
        <th width="30%">내용</th>
        <td><textarea name="content" cols="30" rows="10"></textarea></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="file" name="files" multiple>
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <button>전송</button>
          <button type="reset">취소</button>
        </td>
      </tr>
    </table>
  </form>
  <div id="imgWrapper"></div>
</main>
</body>
</html>
