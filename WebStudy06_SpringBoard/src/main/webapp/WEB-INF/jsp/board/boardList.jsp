<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<table class="table table-bordered">
	<thead class="table-light">
		<tr>
			<th>행번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="boardList" value="${paging.dataList }" />
		<c:if test="${empty boardList }">
			<tr>
				<td colspan="5">검색 조건에 맞는 글이 없음.</td>
			</tr>
		</c:if>
		<c:if test="${not empty boardList }">
			<c:forEach items="${boardList }" var="board">
				<tr>
					<td>${board.rnum }</td>
					<td>
						<a href="<c:url value='/board/${board.boNo }'/>">
							${board.boTitle }
						</a>
						[${board.fileCnt }]
					</td>
					<td>${board.boWriter }</td>
					<td>${board.boDate }</td>
					<td>${board.boHit }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				${paging.pagingHTML }
				<div id="searchUI"  class="row g-3 d-flex justify-content-center">
					<div class="col-auto">
						<form:select path="simpleCondition.searchType" class="form-select">
							<form:option label="전체" value="" />
							<form:option value="title" label="제목" />
							<form:option value="writer" label="작성자" />
							<form:option value="content" label="내용" />
						</form:select>
					</div>
					<div class="col-auto">
						<form:input path="simpleCondition.searchWord" class="form-control" placeholder="검색키워드"/>
					</div>
					<div class="col-auto">
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
					</div>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form:form modelAttribute="simpleCondition" method="get" id="searchForm" class="border">
	<h4>전송 UI</h4>
	<form:input path="searchType" readonly="readonly" placeholder="searchType"/>
	<form:input path="searchWord" readonly="readonly" placeholder="searchWord"/>
	<input type="text" name="page" readonly="readonly" placeholder="page"/>
</form:form>

<script>
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


















