<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>得点管理システム</title>
	</head>
	<body>
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

		<c:choose>
			<c:when test="${testList.size()>0 }">
				<div>検索結果：${testList.size()}件</div>
				<div>氏名：${student.name}（${test.student.no}）</div>
					<table>
						<tr>
							<th>科目名</th>
							<th>科目コード</th>
							<th>回数</th>
							<th>点数</th>
						</tr>
						<c:forEach var="test" items="${testList }">
							<tr>
								<td>${test.subjectName }</td>
								<td>${test.subjectCd }</td>
								<td>${test.num }</td>
								<td>${test.point }</td>
							</tr>
						</c:forEach>
					</table>
			</c:when>
		</c:choose>
	</body>
</html>
