<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url value="/buyer/${buyerId }" var="updateUrl" />
<!-- 핸들러어뎁터는 pathVariable을 자동으로 모델에 추가해줌-> 그래서 buyer.buyerId 아니어도 됨 -->
<form:form action="${updateUrl}" method="post" modelAttribute="buyer" enctype="multipart/form-data">
<%-- <form action="<%=request.getContextPath() %>/buyer/${buyerId}" method="post"> --%>
	<input type="hidden" name="_method" value="put" />
<!-- 	브라우저는 ajax말고는 put 요청을 보낼 수 없음 -> post로 한다음 _method 이름의 파라미터 보내면 그 값으로 바꿔주기 -->
<!-- 	->filter 등록 : HiddenHttpMethodFilter -->

<table class="col-md-6">
<!-- 	경로변수에 있는 애의 이름과 VO의 이름이 동일하면 hidden태그로 id 따로 안넣어줘도됨 -->
	<tr>
		<th>사업자등록증사본</th>
		<td>
			<input type="file" name="buyerImage" class="form-control" accept="image/*" />
		</td>
	</tr>
	<tr>
		<th>제조사명</th>
		<td>
			<form:input path="buyerName" class="form-control" required="true"/>
			<form:errors path="buyerName" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>분류</th>
		<td>
			<form:select path="buyerLgu" items="${lprodList }" itemValue="lprodGu"
			itemLabel="lprodNm"  class="form-select" />
			<form:errors path="buyerLgu" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>은행</th>
		<td>
			<form:input path="buyerBank" class="form-control"/>
			<form:errors path="buyerBank" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>계좌</th>
		<td>
			<form:input path="buyerBankno" class="form-control"/>
			<form:errors path="buyerBankno" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>계좌주</th>
		<td>
			<form:input path="buyerBankname" class="form-control"/>
			<form:errors path="buyerBankname" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>
			<form:input path="buyerZip" class="form-control"/>
			<form:errors path="buyerZip" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>주소1</th>
		<td>
			<form:input path="buyerAdd1" class="form-control"/>
			<form:errors path="buyerAdd1" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>주소2</th>
		<td>
			<form:input path="buyerAdd2" class="form-control"/>
			<form:errors path="buyerAdd2" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>전번</th>
		<td>
			<form:input path="buyerComtel" class="form-control"/>
			<form:errors path="buyerComtel" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>팩스</th>
		<td>
			<form:input path="buyerFax" class="form-control"/>
			<form:errors path="buyerFax" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>메일</th>
		<td>
			<form:input path="buyerMail" class="form-control"/>
			<form:errors path="buyerMail" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>담당자</th>
		<td>
			<form:input path="buyerCharger" class="form-control"/>
			<form:errors path="buyerCharger" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>내선번호</th>
		<td>
			<form:input path="buyerTelext" class="form-control"/>
			<form:errors path="buyerTelext" element="span" cssClass="error" />
		</td>
	</tr>

</table>
<input type="submit" value="PUT 요청 전송" />
</form:form>