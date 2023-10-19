<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<table class="table table-border">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>제조사아이디</th>
			<th>제조사명</th>
			<th>분류</th>
			<th>분류명</th>
			<th>은행</th>
			<th>계좌</th>
			<th>전번</th>
			<th>담당자</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty paging}">
			<c:forEach items="${paging.dataList }" var="dataList">
				<tr>
					<td>${dataList.rnum }</td>
					<td>${dataList.buyerId }</td>
					<td>${dataList.buyerName }</td>
					<td>${dataList.buyerLgu }</td>
					<td>${dataList.lprod.lprodNm }</td>
					<td>${dataList.buyerBank }</td>
					<td>${dataList.buyerBankno }</td>
					<td>${dataList.buyerComtel }</td>
					<td>${dataList.buyerCharger }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="8">
				${paging.pagingHTML }
				<div id="searchUI"  class="row g-3 d-flex justify-content-center">
					<div class="col-auto">
						<form:select path="detailCondition.buyerLgu">
							<option value>분류</option>
							<c:forEach items="${lprodList }" var="lprod">
								<option label="${lprod.lprodNm }" value="${lprod.lprodGu }"/>
							</c:forEach>
						</form:select>
					</div>
					<div class="col-auto">
						<form:input path="detailCondition.buyerName" placeholder="제조사명"/>
						<button type="button" id="searchBtn">검색</button>
					</div>
					<div class="col-auto">
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
						<a href="<c:url value='/prod/prodInsert.do'/>" class="btn btn-success">신규상품 등록</a>
					</div>
				</div>
			</td>
		<tr>
		
	</tfoot>
</table>

	<div class="col-auto">
		<a href="<c:url value='/buyer/form'/>" class="btn btn-success">신규상품 등록</a>
	</div>
	<form:form id="searchForm" modelAttribute="detailCondition">
		<form:input path="buyerLgu"/>
		<form:input path="buyerName"/>
		<input type="text" name="page"/>
	</form:form>
<script>
function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
}
$('#searchBtn').on("click",function(){
	let inputs = $(this).parents("#searchUI").find(":input[name]");
	$.each(inputs, function(i,v){
		let name = v.name;
		let value = v.value;
		$(searchForm).find(`:input[name=\${name}]`).val(value);
		$(searchForm).submit();
	});
})
</script>