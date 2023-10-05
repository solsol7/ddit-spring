<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form method="post" action="<%=request.getContextPath()%>/prod/prodInsert.do">
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
</c:if>
<table class="table table-bordered">
	<tr>
		<td>상품분류</td>
		<td>
			<select name="prodLgu" required>
				<option value>상품분류</option>
				<c:forEach items="${lprodList }" var="lprod">
					<option label="${lprod.lprodNm}" value="${lprod.lprodGu}"/>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>제조사</td>
		<td>
			<select name="prodBuyer" required>
				<option value>제조사</option>
				<c:forEach items="${buyerList }" var="buyer">
					<option class="${buyer.buyerLgu }" value="${buyer.buyerId }"  label="${buyer.buyerName }"/>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>상품명</td>
		<td>
			<input type="text" name="prodName" placeholder="prodName" required>
		</td>
	</tr>
	<tr>
		<td>구매가</td>
		<td>
			<input type="text" name="prodCost" placeholder="prodCost" required>
		</td>
	</tr>
	<tr>
		<td>판매가</td>
		<td>
			<input type="text" name="prodPrice" placeholder="prodPrice" required>
		</td>
	</tr>
	<tr>
		<td>세일가</td>
		<td>
			<input type="text" name="prodSale" placeholder="prodSale" required>
		</td>
	</tr>
	<tr>
		<td>요약정보</td>
		<td>
			<input type="text" name="prodOutline" placeholder="prodOutline" required>
		</td>
	</tr>
	<tr>
		<td>이미지</td>
		<td>
			<input type="text" name="prodImg" placeholder="prodImg" required>
		</td>
	</tr>
	<tr>
		<td>총재고</td>
		<td>
			<input type="text" name="prodTotalstock" placeholder="prodTotalstock" required>
		</td>
	</tr>
	<tr>
		<td>적정재고</td>
		<td>
			<input type="text" name="prodProperstock" placeholder="prodProperstock" required>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="저장"/>
		</td>
	</tr>
</table>
</form>

<script>

	let options = $("select[name=prodBuyer]").find("option");
	
	$(function(){
		$(options).hide();	
	})
	
	$("select[name=prodLgu]").on("change",function(){
		let lgu = $(this).val()
		
		$(options).hide();	
		$(options).filter(":first").show();
		$(options).filter(`.\${lgu}`).show();
			
	})
</script>