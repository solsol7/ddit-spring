<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
</c:if>

<h4>가입(수정) 양식</h4>
<form:form enctype="multipart/form-data" modelAttribute="member">
	<!-- action생략 -> 여기까지 올 때 사용했던 주소 사용 : /member/memberInsert.do -->
	<!-- form태그 -> 동기요청, 파라미터 전송 -->
   <table>

      <tr>
         <th><spring:message code="member.memId" /> </th>
         <td>
         	<form:input path="memId" class="form-control"  />
         	<form:errors path="memId" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memPass" /> </th>
         <td>
         	<form:input path="memPass" class="form-control" />
         	<form:errors path="memPass" element="span" cssClass="error" />
      </tr>
      <tr>
      	<th><spring:message code="member.memImg" /> </th>
      	<td>
      		<input type="file" name="memImage" accept="image/*" class="form-control" />
<!--       			accept =>이미지 파일만 입력할 수 있도록 제한 -->
      	</td>
      </tr>
      <tr>
         <th><spring:message code="member.memName" /> </th>
         <td>
        	<form:input path="memName" class="form-control"  />
        	<form:errors path="memName" element="span" cssClass="error"></form:errors>
            <span class="error">${errors.memName}</span></td>
      </tr>
      <tr>
         <th><spring:message code="member.memRegno1" /> </th>
         <td>
         	<form:input path="memRegno1" class="form-control"  />
            <form:errors path="memRegno1" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memRegno2" /> </th>
         <td>
         	<form:input path="memRegno2" class="form-control"  />
            <form:errors path="memRegno2" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memBir" /> </th>
         <td><form:input type="date" path="memBir" class="form-control"  />
         <form:errors path="memBir" element="span" cssClass="error" /></td>
      </tr>
      <tr>
         <th><spring:message code="member.memZip" /> </th>
         <td><form:input path="memZip" class="form-control"  />
         <form:errors path="memZip" element="span" cssClass="error" /></td>
      </tr>
      <tr>
        <th><spring:message code="member.memAdd1" /> </th>
         <td><form:input path="memAdd1" class="form-control"  />
         <form:errors path="memAdd1" element="span" cssClass="error" /></td>
      </tr>
      <tr>
         <th><spring:message code="member.memAdd2" /> </th>
         <td><form:input path="memAdd2" class="form-control"  />
         <form:errors path="memAdd2" element="span" cssClass="error" /></td>
      </tr>
      <tr>
         <th><spring:message code="member.memHometel" /> </th>
         <td><form:input path="memHometel" class="form-control"  />
         <form:errors path="memHometel" element="span" cssClass="error" /></td>
      </tr>
      <tr>
         <th><spring:message code="member.memComtel" /> </th>
         <td><form:input path="memComtel" class="form-control"  />
         <form:errors path="memComtel" element="span" cssClass="error" /></td>
      </tr>
      <tr>
         <th><spring:message code="member.memHp" /> </th>
         <td><form:input path="memHp" class="form-control"  />
         <form:errors path="memHp" element="span" cssClass="error" /></td>
      </tr>
      <tr>
         <th><spring:message code="member.memMail" /> </th>
         <td><form:input path="memMail" class="form-control"  />
         <form:errors path="memMail" element="span" cssClass="error" /></td>
      </tr>
      <tr>
         <th><spring:message code="member.memJob" /> </th>
         <td><form:input path="memJob" class="form-control"  />
         <form:errors path="memJob" element="span" cssClass="error" /></td>
      </tr>
      <tr>
         <th><spring:message code="member.memLike" /> </th>
         <td><form:input path="memLike" class="form-control" />
         <form:errors path="memLike" element="span" cssClass="error" /></td>
      </tr>
      <tr>
         <th><spring:message code="member.memMemorial" /> </th>
         <td><form:input path="memMemorial" class="form-control"/>
         <form:errors path="memMemorial" element="span" cssClass="error" /></td>
      </tr>
      <tr>
         <th><spring:message code="member.memMemorialday" /> </th>
         <td><form:input type="date" path="memMemorialday" class="form-control"/>
         <form:errors path="memMemorialday" element="span" cssClass="error" /></td>
      </tr>

      <tr>
         <td colspan="2"><input type="submit" value="전송"
            class="btn btn-primary" /> <input type="reset" value="취소"
            class="btn btn-warning" /></td>
      </tr>


   </table>

</form:form>



<%-- 
<form method="post" enctype="multipart/form-data">
	<!-- action생략 -> 여기까지 올 때 사용했던 주소 사용 : /member/memberInsert.do -->
	<!-- form태그 -> 동기요청, 파라미터 전송 -->
   <table>

      <tr>
         <th>회원아이디</th>
        
         <td>
         	<form
         <input type="text" name="memId" class="form-control"
            required value="${member.memId}" />
            
            <span class="error">${errors.memId}</span>
         </td>
      </tr>
      <tr>
         <th>비밀번호</th>
         <td><input type="text" name="memPass" class="form-control"
            required  /><span class="error">${errors.memPass}</span></td>
      </tr>
      <tr>
      	<th>회원이미지</th>
      	<td>
      		<input type="file" name="memImage" accept="image/*" class="form-control" />
<!--       			accept =>이미지 파일만 입력할 수 있도록 제한 -->
      	</td>
      </tr>
      <tr>
         <th>회원명</th>
         <td><input type="text" name="memName" class="form-control"
            required value="${member.memName}" /><span class="error">${errors.memName}</span></td>
      </tr>
      <tr>
         <th>주민번호1</th>
         <td><input type="text" name="memRegno1" class="form-control"
            value="${member.memRegno1}" /><span class="error">${errors.memRegno1}</span></td>
      </tr>
      <tr>
         <th>주민번호2</th>
         <td><input type="text" name="memRegno2" class="form-control"
            value="${member.memRegno2}" /><span class="error">${errors.memRegno2}</span></td>
      </tr>
      <tr>
         <th>생일</th>
         <td><input type="date" name="memBir" class="form-control"
            value="${member.memBir}" /><span class="error">${errors.memBir}</span></td>
      </tr>
      <tr>
         <th>우편번호</th>
         <td><input type="text" name="memZip" class="form-control"
            required value="${member.memZip}" /><span class="error">${errors.memZip}</span></td>
      </tr>
      <tr>
         <th>주소1</th>
         <td><input type="text" name="memAdd1" class="form-control"
            required value="${member.memAdd1}" /><span class="error">${errors.memAdd1}</span></td>
      </tr>
      <tr>
         <th>주소2</th>
         <td><input type="text" name="memAdd2" class="form-control"
            required value="${member.memAdd2}" /><span class="error">${errors.memAdd2}</span></td>
      </tr>
      <tr>
         <th>집전번</th>
         <td><input type="text" name="memHometel" class="form-control"
            value="${member.memHometel}" /><span class="error">${errors.memHometel}</span></td>
      </tr>
      <tr>
         <th>회사전번</th>
         <td><input type="text" name="memComtel" class="form-control"
            value="${member.memComtel}" /><span class="error">${errors.memComtel}</span></td>
      </tr>
      <tr>
         <th>휴대폰</th>
         <td><input type="text" name="memHp" class="form-control"
            value="${member.memHp}" /><span class="error">${errors.memHp}</span></td>
      </tr>
      <tr>
         <th>이메일</th>
         <td><input type="text" name="memMail" class="form-control"
            required value="${member.memMail}" /><span class="error">${errors.memMail}</span></td>
      </tr>
      <tr>
         <th>직업</th>
         <td><input type="text" name="memJob" class="form-control"
            value="${member.memJob}" /><span class="error">${errors.memJob}</span></td>
      </tr>
      <tr>
         <th>취미</th>
         <td><input type="text" name="memLike" class="form-control"
            value="${member.memLike}" /><span class="error">${errors.memLike}</span></td>
      </tr>
      <tr>
         <th>기념일</th>
         <td><input type="text" name="memMemorial" class="form-control"
            value="${member.memMemorial}" /><span class="error">${errors.memMemorial}</span></td>
      </tr>
      <tr>
         <th>기념일자</th>
         <td><input type="date" name="memMemorialday"
            class="form-control" value="${member.memMemorialday}" /><span
            class="error">${errors.memMemorialday}</span></td>
      </tr>

      <tr>
         <td colspan="2"><input type="submit" value="전송"
            class="btn btn-primary" /> <input type="reset" value="취소"
            class="btn btn-warning" /></td>
      </tr>


   </table>

</form>
--%>