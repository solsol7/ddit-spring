<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
	
	
	
	leftOp = $('#leftOp').val();
	rightOp = $('#rightOp').val();
	operator = $('#operator').val();

$(function(){
	$('#calcSubmit').on('click',function(){
		$.ajax({
			url :  '<%=request.getContextPath() %>/calculate.do',
			data : 
			type : 'get',
			
			dataType : 'String',
			success : function(res){
				
			},
			error : function(xhr){
				
			}
			
		})	//ajax 끝
	})

})
</script>

</head>
<body>
<!-- 비동기 처리 기반의 사칙연산기. -->
<label><input type="radio" name="dataType" value="json" />JSON</label>
<label><input type="radio" name="dataType" value="xml" />XML</label>
<label><input type="radio" name="dataType" value="html" />HTML</label>
<form action="<%=request.getContextPath() %>/calculate.do">
	<input type="number" name="leftOp" id="leftOp" />
	<select name="operator" id="operator">
		<option value>연산자</option>
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option>
	</select>
	<input type="number" name="rightOp" id="rightOp"/>
	<input type="submit" id="calcSubmit" value="="/>
</form>
<div id="resultArea">
	<%=request.getAttribute("result") %>
</div>
</body>
</html>