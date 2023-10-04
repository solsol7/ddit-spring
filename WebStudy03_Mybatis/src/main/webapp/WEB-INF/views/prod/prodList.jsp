<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>일련번호</th> 
<!-- 				rownum -->
			<th>상품명</th>
			<th>상품분류명</th>
			<th>제조사명</th>
			<th>판매가</th>
			<th>세일가</th>
			<th>마일리지</th>
			<th>구매자수</th>
		</tr>
	</thead>
	<tbody>
		<c:set value="${paging.dataList }" var="prodList"></c:set>
		<c:if test="${not empty prodList }">
			<c:forEach var="prod" items="${prodList}">
				<tr>
					<td>${prod.rnum }</td>
					<td>${prod.prodName }</td>
					<td>${prod.lprod.lprodNm }</td>
					<td>${prod.buyer.buyerName }</td>
					<td>${prod.prodCost }</td>
					<td>${prod.prodSale }</td>
					<td>${prod.prodMileage }</td>
					<td>${prod.memCount }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty prodList }">
			<td colspan="8">검색된 상품 없음</td>
		</c:if>
	</tbody>
	<tfoot>
	<tr>
		<td colspan="7">
			${paging.pagingHTML }
		</td>
	</tr>
	</tfoot>
</table>
<script>
	function fn_paging(page){
		location.href="?page="+page;
	}
</script>