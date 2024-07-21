<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common/header.css">
<header>
  <h2>${member.nickname} 안녕</h2>
  <input type="hidden" data-user='${member.id}'>
</header>