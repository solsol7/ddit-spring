<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body data-context-path="<%=request.getContextPath() %>">

	<form id="memoForm" method="post" action="<%=request.getContextPath() %>/memo">
		<input type="text" name="writer" placeholder="작성자" required /> 
		<input type="text" name="email" placeholder="이메일" required /> 
		<input type="text" name="content" placeholder="내용" /> 
		<input type="submit" value="등록" />
	</form>
	<table border="1">
		<thead>
			<tr>
				<th>작성번호</th>
				<th>작성자</th>
				<th>메일</th>
				<th>작성일자</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody id="listBody">

		</tbody>
	</table>


<!-- Modal -->
<div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">메모 수정</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>    
      <div class="modal-body">
					<form id="modalForm">
						<table>
							<tr><td><input type="hidden" id="code" name="code" placeholder="코드" /></td></tr>
							<tr><td>작성자<input type="text" id="writer" name="writer"/></td></tr>
							<tr><td>이메일<input type="text" id="email" name="email"/></td></tr>
							<tr><td>내용<input type="text" id="content" name="content"  /></td></tr>
						</table>
					</form>        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" id="modBtn" class="btn btn-primary">수정</button>
      </div>
    </div>
  </div>
</div>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/app/memo/memo.js"></script>
</body>
</html>