<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>成績参照</title>
</head>
<body>
<h1> 成績参照</h1>


<!-- 科目情報検索フォーム -->
<form action="TestListAction" method="get">
  <fieldset>
    <legend>科目情報</legend>

    <label>入学年度</label>
    <select name="year">
      <option value="">----</option>
      <c:forEach var="y" begin="2018" end="2025">
        <option value="${y}" <c:if test="${y == year}">selected</c:if>>${y}</option>
      </c:forEach>
    </select>

    <label>クラス</label>
    <select name="classId">
      <option value="">----</option>
      <c:forEach var="cls" items="${classNumList}">
        <option value="${cls}" <c:if test="${cls == classId}">selected</c:if>>${cls}</option>
      </c:forEach>
    </select>

    <label>科目</label>
    <select name="subjectId">
      <option value="">----</option>
      <c:forEach var="sub" items="${subjectList}">
        <option value="${sub.cd}" <c:if test="${sub.cd == subjectId}">selected</c:if>>${sub.name}</option>
      </c:forEach>
    </select>

    <button type="submit" name="searchType" value="subject">検索</button>
  </fieldset>
</form>



    <!-- 学生番号検索フォーム -->
<form action="TestListStudentExecute.action" method="get">
<fieldset>
<legend> 学生情報</legend>

            <label>学生番号</label>
<input type="text" name="studentNumber" value="${studentNumber}" placeholder="例：S12345678">

            <button type="submit" name="searchType" value="student"> 検索</button>
</fieldset>
</form>

    <!-- エラーメッセージ -->
<c:if test="${not empty error}">
<p style="color: red;"> ${error}</p>
</c:if>



    <!-- 注意文 -->
<p>必要に応じて条件を指定し、「検索」ボタンをクリックしてください。</p>
</body>
</html>