<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<table class="table table-bordered">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>생일</th>
			<th>거주지역</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>

		<c:set var="memberList" value="${paging.dataList }"></c:set>
		<c:if test="${empty memberList }">
			<tr>
				<td colspan="7">검색 조건에 맞는 회원 없음.</td>
			</tr>
		</c:if>
		<c:if test="${not empty memberList }">
			<c:forEach items="${memberList }" var="member">
				<tr data-mem-id="${member.memId}" data-bs-toggle="modal"
					data-bs-target="#exampleModal">
					<!--
					Button trigger modal // 내 코드 1
					<tr data-mem-id="${member.memId}">
					<td><a href="javascript:;" onclick="detailClick('${member.memId}');"
						data-bs-target="#exampleModal" class="btn modBtn"
						data-bs-toggle="modal" >${member.memName }</a>[${member.prodCount }]</td>
					 -->
					<td>${member.rnum }</td>
					<td>${member.memName }[${member.prodCount }]</td>
					<td>${member.memHp }</td>
					<td>${member.memMail }</td>
					<td>${member.memBir }</td>
					<td>${member.memAdd1 }</td>
					<td>${member.memMileage }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				${paging.pagingHTML }
				<div id="searchUI">
					<form:select path="simpleCondition.searchType"  lass="form-select">
						<form:option label="전체" value=""/>
						<form:option value="name" label="이름"/>
						<form:option value="address" label="지역"/>
					</form:select>
					<form:input path="simpleCondition.searchWord" />
					<input type="button" value="검색" id="searchBtn"/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form:form modelAttribute="simpleCondition" id="searchForm" method="get">
	<form:input path="searchType" readonly="readonly" placeholder="searchType"/>
	<form:input path="searchWord" readonly="readonly" placeholder="searchWord"/>
	<input type="text" name="page" />	
</form:form>

<%-- <form id="searchForm"> --%>
<!-- 	<input type="text" name="searchType" /> -->
<!-- 	<input type="text" name="searchWord" /> -->
<!-- 	<input type="text" name="page" /> -->
<%-- </form> --%>

<!-- Modal  // 내 코드 2
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h1 class="modal-title fs-5" id="exampleModalLabel">주소록 수정</h1>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body" id="modal-body">
					      //출력되는곳
				      </div>
				      <div class="modal-footer">
				      </div>
				    </div>
				  </div>
				</div>
 -->
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">...</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>
<script>
// 	$(":input[name=searchType]").val("${simpleCondition.searchType}");
// 	$(":input[name=searchWord]").val("${simpleCondition.searchWord}");
	function fn_paging(page){
		searchForm.page.value = page;
		searchForm.requestSubmit();
	}
	
	$(searchUI).on("click", "#searchBtn", function(event){
		let inputs = $(this).parents("#searchUI").find(":input[name]");
		$.each(inputs, function(idx, ipt){
			let name = ipt.name;
			let value = $(ipt).val();
			$(searchForm).find(`:input[name=\${name}]`).val(value)
			$(searchForm).submit();
		})
	});

	// EDD(Event-Driven-Development)
	$(exampleModal).on("show.bs.modal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let who = $(trTag).data("memId");
	//	location.href = "${pageContext.request.contextPath}/member/memberView.do?who="+who;
		let url = "${pageContext.request.contextPath}/member/memberView.do?who="+who;
		$.get(url)	//ajax의 method : get과 똑같음
			.done(function(resp){	//success와 똑같은 역할
				$modal.find(".modal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});

<%--  //내 코드 3
function detailClick(memId){
	let settings = {
		url : "<%=request.getContextPath()%>/member/memberView.do?who="+memId,
		method : "get",
		dataType : "html", //Accept request header : Content-Type response header
		success : function(resp) {
			$('#modal-body').html(resp)
		},
		error : function(jqXhr, status, error) {
			console.log("jqXhr : ", jqXhr);
			console.log("status : ", status);
			console.log("errer : ", error);
		}
	};
	$.ajax(settings);
	
}
--%>
</script>


