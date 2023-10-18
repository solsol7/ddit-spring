<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:if test="${not empty message }">
	alert(${message })
</c:if>
<c:url value='/buyer' var="buyerInsert"></c:url>
<form:form action="${buyerInsert}" modelAttribute="buyer" enctype="multipart/form-data">
	<table class="col-md-6">
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
	<input type="submit" value="전송" />
</form:form>