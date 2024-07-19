<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>

  <c:choose>
  <c:when test="${pageContext.exception.message == 'MEMBER_NOT_FOUND'}">
  alert("회원을 찾을 수 없습니다.")
  location.href = '/member/login'
  </c:when>
  <c:when test="${pageContext.exception.message == 'WRONG_PASSWORD'}">
  alert("비밀번호가 틀렸습니다.")
  location.href = '/member/login'
  </c:when>
  <c:otherwise>
  alert("다시 로그인 해주세요.")
  location.href = '/member/login'
  </c:otherwise>
  </c:choose>
</script>
