<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
	<table class="table table-bordered">
	<tr>
		<th><spring:message code="member.memImage" /></th>
		<td>
			<c:if test="${not empty member.memImg }">
				<img src="data:image/*;base64,${member.memImgBase64 }"/>
			</c:if>
		</td>
	</tr>
	<tr>
		<th><spring:message code="member.memId" /></th>
		<td>${member.memId }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memPass" /></th>
		<td>${member.memPass }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memName" /></th>
		<td>${member.memName }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memRegno1" /></th>
		<td>${member.memRegno1 }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memRegno2" /></th>
		<td>${member.memRegno2 }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memBir" /></th>
		<td>${member.memBir }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memZip" /></th>
		<td>${member.memZip }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memAdd1" /></th>
		<td>${member.memAdd1 }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memAdd2" /></th>
		<td>${member.memAdd2 }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memHometel" /></th>
		<td>${member.memHometel }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memComtel" /></th>
		<td>${member.memComtel }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memHp" /></th>
		<td>${member.memHp }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memMail" /></th>
		<td>${member.memMail }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memJob" /></th>
		<td>${member.memJob }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memLike" /></th>
		<td>${member.memLike }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memMemorial" /></th>
		<td>${member.memMemorial }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memMemorialday" /></th>
		<td>${member.memMemorialday }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memMileage" /></th>
		<td>${member.memMileage }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memDelete" /></th>
		<td>${member.memDelete }</td>
	</tr>
</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
