<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<table class="table table-bordered">
	<thead>
		<tr>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>생일</th>
			<th>거주지역</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		
		<c:if test="${empty memberList }">
			<tr>
				<td colspan="5">검색 조건에 맞는 회원 없음.</td>
			</tr>
		</c:if>
		<c:if test="${not empty memberList }">
			<c:forEach items="${memberList }" var="member">
				<tr data-mem-id="${member.memId}">
					
					<!-- Button trigger modal -->
					<td><a href="javascript:;" onclick="detailClick('${member.memId}');"
						data-bs-target="#exampleModal" class="btn modBtn"
						data-bs-toggle="modal" >${member.memName }</a>[${member.prodCount }]</td>
					
					
					<td>${member.memHp }</td>
					<td>${member.memMail }</td>
					<td>${member.memBir }</td>
					<td>${member.memAdd1 }</td>
					<td>${member.memMileage }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h1 class="modal-title fs-5" id="exampleModalLabel">주소록 수정</h1>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body" id="modal-body">
					      <!-- 출력되는 곳 -->
				      </div>
				      <div class="modal-footer">
				      </div>
				    </div>
				  </div>
				</div>

<script>
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

</script>



