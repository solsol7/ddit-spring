<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<table class="table table-bordered">
	<thead>
		<tr>
			<th>일련번호</th>
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
		<c:set var="prodList" value="${paging.dataList }" />
		<c:if test="${not empty prodList }">
			<c:forEach items="${prodList }" var="prod">
				<tr data-prod-id="${prod.prodId }">
					<td>${prod.rnum}</td>
					<td>
						<c:url value="/prod/prodView.do" var="prodViewURL">
							<c:param name="what" value="${prod.prodId }" />
						</c:url>
						<a href="${prodViewURL }">${prod.prodName }</a>
					</td>
					<td>${prod.lprod.lprodNm }</td>
					<td>${prod.buyer.buyerName }</td>
					<td>${prod.prodPrice }</td>
					<td>${prod.prodSale }</td>
					<td>${prod.prodMileage }</td>
					<td>${prod.memCount }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty prodList }">
			<tr>
				<td colspan="8">상품 없음.</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="8">
				${paging.pagingHTML }
				<div id="searchUI"  class="row g-3 d-flex justify-content-center">
					<div class="col-auto">
						<form:select path="detailCondition.prodLgu" id="prodLgu" class="form-select">
							<option value>상품분류</option>
							<c:forEach items="${lprodList }" var="lprod">
								<form:option label="${lprod.lprodNm }" value="${lprod.lprodGu }" />
							</c:forEach>
						</form:select>
					</div>
					<div class="col-auto">
						<form:select path="detailCondition.prodBuyer" class="form-select">
						<option value>제조사</option>
							<c:forEach items="${buyerList }" var="buyer">
								<form:option class="${buyer.buyerLgu}" value="${buyer.buyerId }" label="${buyer.buyerName }" />
							</c:forEach>
						</form:select>
					</div>
					<div class="col-auto">
						<input type="text" name="prodName" placeholder="상품명" class="form-control"/>
					</div>
					<div class="col-auto">
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
						<a href="<c:url value='/prod/prodInsert.do'/>" class="btn btn-success">신규상품 등록</a>
					</div>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form:form modelAttribute="detailCondition" id="searchForm" class="border" method="get">
	<h4>전송 UI</h4>
	<form:input path="prodLgu" readonly="readonly" placeholder="prodLgu"/>
	<form:input path="prodBuyer" readonly="readonly" placeholder="prodBuyer"/>
	<form:input path="prodName" readonly="readonly" placeholder="prodName"/>
	<input type="text" name="page" readonly="readonly" placeholder="page"/>
<!-- 	prodVO에는 page가 없기때문에 form 커스텀태그 쓸 수 없음 -->
</form:form>
<script>
$("select[name=prodLgu]").on("change", function(event){
	let lgu = $(this).val();
	let $options = $("select[name=prodBuyer]").find("option");
	$options.hide();
	$options.filter((i,e)=>i==0).show();
	if(lgu){
		$options.filter(`.\${lgu}`).show();
	}else{
		$options.show();
	}
}).trigger("change");
// $(":input[name=prodLgu]").val("${detailCondition.prodLgu}").trigger("change");
// $(":input[name=prodBuyer]").val("${detailCondition.prodBuyer}");
// $(":input[name=prodName]").val("${detailCondition.prodName}");
function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
}
$(searchUI).on("click", "#searchBtn", function(event){
	let inputs = $(this).parents("#searchUI").find(":input[name]");
	$.each(inputs, function(idx, ipt){
		let name = ipt.name;
		let value = $(ipt).val();
		$(searchForm).find(`:input[name=\${name}]`).val(value);
		$(searchForm).submit();
	});
});
</script>



