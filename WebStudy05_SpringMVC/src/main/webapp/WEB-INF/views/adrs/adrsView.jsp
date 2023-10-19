<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form id="adrsForm" action="<%=request.getContextPath() %>/adrs/address" method="post">
	<input type="text" name="adrsName" placeholder="이름" required><br/>
	<input type="text" name="adrsHp" placeholder="휴대폰" required><br/>
	<input type="text" name="adrsAdd" placeholder="주소"><br/>
	<button type="submit">추가</button>
</form>
<table>
	<thead>
		<tr>
			<th>이름</th>
			<th>휴대폰</th>
			<th>주소</th>
		</tr>
	</thead>
	<tbody id="listBody">
	
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
				      <div class="modal-body">
					  <form id="modalForm">
					      <table>
					      		<tr><td><input type="hidden" name="adrsNo" id="modAdrsNo"/></td></tr>
								<tr><td>이름<input type="text" name="adrsName" id="modAdrsName"/></td></tr>
								<tr><td>휴대폰<input type="text" name="adrsHp" id="modAdrsHp"/></td></tr>
								<tr><td>주소<input type="text" name="adrsAdd" id="modAdrsAdd"/></td></tr>
						  </table>
					  </form>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				        <button type="button" class="btn btn-primary" id="saveBtn">Save changes</button>
				      </div>
				    </div>
				  </div>
				</div>

<script src="${pageContext.request.contextPath }/resources/js/app/adrs/address.js"></script>
