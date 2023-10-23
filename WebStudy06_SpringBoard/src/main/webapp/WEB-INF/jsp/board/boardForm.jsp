<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script src="<c:url value='/resources/js/ckeditor/ckeditor.js'/>"></script>

<form:form modelAttribute="newBoard" enctype="multipart/form-data">
<table>
	<tr>
		<th><label for="boTitle">제목</label></th>
		<td><form:input type="text" path="boTitle" class="form-control"
				 />
			<form:errors path="boTitle" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th><label for="boWriter">작성자</label></th>
		<td>
			<form:input type="text" path="boWriter" class="form-control" />  
  			<form:errors path="boWriter" element="span" cssClass="error" />
  		</td>  
	</tr>
	<tr>
		<th><label for="boIp">IP</label></th>
<%-- 		<%=request.getRemoteAddr() %> - 클라이언트 address --%>
<%-- 		<%=request.getLocalAddr() %> --%>
		<td>
			<input type="text" name="boIp" class="form-control" 
				value="${pageContext.request.remoteAddr }" readonly />
			<form:errors path="boIp" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th><label for="boMail">이메일</label></th>
		<td><form:input type="email" path="boMail" class="form-control" />
			<form:errors path="boMail" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th><label for="boPass">비번</label></th>
		<td><form:input type="password" path="boPass" class="form-control"
				 />
			<form:errors path="boPass" element="span" cssClass="error" /></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<input type="file" name="boFile" />
			<input type="file" name="boFile" />
			<input type="file" name="boFile" />
		</td>
	</tr>
	<tr>
		<th><label for="boContent">내용</label></th>
		<td>
			<form:textarea path="boContent" />
			<form:errors path="boContent" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="저장" />
			<input type="reset" value="취소" />
		</td>
	</tr>
</table>
</form:form>

<script>
	CKEDITOR.replace("boContent", {
		filebrowserImageUploadUrl:"<c:url value='/board/image?type=image' />"
	});
</script>
