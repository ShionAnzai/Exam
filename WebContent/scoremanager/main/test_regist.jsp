<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:param name="title">得点管理システム - 成績一覧</c:param>
		<form action="TestRegist.action" method="post">
			<h2>成績一覧</h2>

			<div>
				<label>入学年度</label>
				<select name="f1">
					<option value="0">-------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>
			</div>

			<div>
				<label>クラス</label>
				<select name="f2">
					<option value="0">--------</option>
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
			</div>

			<div>
				<label>科目</label>
				<select name="f3">
					<option value="0">--------</option>
					<c:forEach var="subject" items="${subject_set}">
						<option value="${subject.name}" <c:if test="${subject.name == f3}">selected</c:if>>${subject.name}</option>
					</c:forEach>
				</select>
			</div>

			<div>
				<label>回数</label>
				<select name="f4">
					<option value="0">--------</option>
					<c:forEach var="no" items="${no_set}">
						<option value="${no}" <c:if test="${no == f4}">selected</c:if>>${no}</option>
					</c:forEach>
				</select>
			</div>

			<div>
				<input type="submit" value="検索" />
			</div>
		</form>
