<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<table>
	<tr>
		<th>글번호</th>
		<td>${board.boNo }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${board.boTitle }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${board.boWriter }</td>
	</tr>
	<tr>
		<th>IP</th>
		<td>${board.boIp }</td>
	</tr>
	<tr>
		<th>
			첨부파일
		</th>
		<td>
		<c:if test="${not empty board.attatchList }">
			<c:forEach items="${board.attatchList}" var="atch">
			<c:url value="/board/${boNo}/boFile/${atch.attNo }" var="downloadUrl"/>
<!-- 				/board/23/boFile/2 -->
				<a href="${downloadUrl }"
					title="${atch.attFancysize },${atch.attDownload}명 다운">
					${atch.attFilename }
				</a>
			</c:forEach>
		</c:if>
		</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${board.boMail }</td>
	</tr>
	<tr>
		<th>비번</th>
		<td>${board.boPass }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${board.boContent }</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${board.boDate }</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${board.boHit }</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="<c:url value='/board/${boNo }/edit' />">글수정</a>
			<a href="javascript:;" onclick="deleteFunction();">글삭제</a>
<!-- 			상세페이지를 타고옴 -> 이미 경로변수 있음 -> board.boNo라고 안해도 됨 -->
		</td>
	</tr>
</table>
<form id="deleteForm" method="post" action="<c:url value='/board/${boNo }' />">
	<input type="text" name="_method" value="delete" />
	<input type="password" name="boPass" />
	<security:csrfInput/>
</form>

<script>
	function deleteFunction(){
		let password = prompt("비밀번호 입력 : ");
		if(password){
			deleteForm.boPass.value = password;
			deleteForm.requestSubmit();
		}
	}
</script>