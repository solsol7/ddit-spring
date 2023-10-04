<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered">
	<tr>
		<th>상품명</th>
		<td>${prod.prodName }</td>
	</tr>
	<tr>
		<th>상품분류명</th>
		<td>${prod.lprod.lprodNm }</td>
	</tr>
	<tr>
		<th>제조사</th>
		<td>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>제조사명</th>
						<th>소재지</th>
						<th>담당자명</th>
						<th>거래은행명</th>
					</tr>
				</thead>
				<tbody>
	   				<tr>
	   					<td>${prod.buyer.buyerName }</td>
	   					<td>${prod.buyer.buyerAdd1 }</td>
	   					<td>${prod.buyer.buyerCharger }</td>
	   					<td>${prod.buyer.buyerBank }</td>
	   				</tr>
				</tbody>
			</table>
		</td>
	</tr>
</table>
<h4>구매자 정보</h4>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>구매자이름</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>거주지</th>
			<th>마일리지</th>
			<th>구매일자</th>
		</tr>
	</thead>
	<tbody>
	     <c:set var="cartList" value="${prod.cartList }"/>
			<c:if test="${not empty cartList}">
		   			<c:forEach items="${cartList }" var="cart">
		   				<c:set var="member" value="${cart.member }"></c:set>
		   				<tr>
		   					<td>${member.memName }</td>
		   					<td>${member.memHp }</td>
		   					<td>${member.memMail }</td>
		   					<td>${member.memAdd1 }</td>
		   					<td>${member.memMileage }</td>
		   					<td>${cart.cartDate }</td>		   					
		   				</tr>
		   			</c:forEach>
		   		</c:if>
		   		<c:if test="${empty cartList}">
		   			<tr>
		   				<td colspan="5">구매 정보 없음</td>
		   			</tr>
		   		</c:if>
	</tbody>
</table>
