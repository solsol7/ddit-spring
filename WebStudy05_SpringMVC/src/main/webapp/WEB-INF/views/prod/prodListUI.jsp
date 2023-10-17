<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
	<tbody id="dataList">
		
	</tbody>
	<tfoot>
		<tr>
			<td colspan="8">
				<div id="paging"></div>
				<div id="searchUI"  class="row g-3 d-flex justify-content-center">
					<div class="col-auto">
						<select name="prodLgu" class="form-select">
							<option value>상품분류</option>
							<c:forEach items="${lprodList }" var="lprod">
								<option label="${lprod.lprodNm }" value="${lprod.lprodGu }" />
							</c:forEach>
						</select>
					</div>
					<div class="col-auto">
						<select name="prodBuyer" class="form-select">
							<option value>제조사</option>
							<c:forEach items="${buyerList }" var="buyer">
								<option class="${buyer.buyerLgu	 }" label="${buyer.buyerName }" value="${buyer.buyerId }" />
							</c:forEach>
						</select>
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
<form action="<c:url value='/prod/ajax/prodListData.do'/>" id="searchForm" class="border">
	<h4>전송 UI</h4>
	<input type="text" name="prodLgu" readonly="readonly" placeholder="prodLgu"/>
	<input type="text" name="prodBuyer" readonly="readonly" placeholder="prodBuyer"/>
	<input type="text" name="prodName" readonly="readonly" placeholder="prodName"/>
	<input type="text" name="page" readonly="readonly" placeholder="page"/>
</form>
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
});

$(searchForm).on("submit", function(event){
	event.preventDefault();
	let url = this.action;
	let data = $(this).serialize();
// 	$.getJSON(url, function(){
// 		function ->success function
// 	});
	$.getJSON(`\${url}?\${data}`)
		.done(function(resp){
			dataList = resp.paging.dataList;
			result = "";
			let cpath = $('body').data("contextPath")
			if(dataList){
				$.each(dataList, function(i,v){
					result += `<tr data-prod-id="\${v.prodId }">
								<td>\${v.rnum}</td>
								<td>
									<a href="\${cpath}/prod/prodView.do?what=\${v.prodId}">\${v.prodName }</a>
								</td>
								<td>\${v.lprod.lprodNm }</td>
								<td>\${v.buyer.buyerName }</td>
								<td>\${v.prodPrice }</td>
								<td>\${v.prodSale }</td>
								<td>\${v.prodMileage }</td>
								<td>\${v.memCount }</td>
							</tr>`;
				});
			}else{
				result += `<tr><td colspan="8">상품 없음</td></tr>`;
			}
			let paging = resp.paging.pagingHTML;
			$('#dataList').html(result);
			$('#paging').html(paging);
		});
}).submit();

function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value = "";
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



