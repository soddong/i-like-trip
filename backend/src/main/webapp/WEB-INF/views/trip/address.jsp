<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<select id="search-area" class="form-select me-2" name="sido">
	<option value="0" <c:if test="${empty param.sido}">selected</c:if> >검색 할 지역 선택</option>
	<c:if test="${not empty sidoList}">
		<c:forEach var="sido" items="${sidoList}">
		<option value=${sido.sidoCode} <c:if test="${param.sido eq sido.sidoCode}">selected</c:if> >
			${sido.sidoName}
		</option>
		</c:forEach>
	</c:if>
</select>
<select id="search-area-level2" class="form-select me-2" name="gugun">
	<option value="0" selected>검색 할 지역 선택</option>
		<c:if test="${not empty gugunList}">
		<c:forEach var="gugun" items="${gugunList}">
		<option value=${gugun.gugunCode}>
			${gugun.gugunName}
		</option>
		</c:forEach>
	</c:if>
</select>
<script>
	document.getElementById('search-area').addEventListener('change',(e)=>{
		location.href='${root}/trip?sido='+e.target.value
	})
</script>