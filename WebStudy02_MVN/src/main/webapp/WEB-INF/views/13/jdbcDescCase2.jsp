<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/jdbcDesc.jsp</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	1. 드라이버를 빌드패스에 추가
	2. 드라이버(클래스) 로딩
	3. Connection 생성
	4. 쿼리 객체 생성
	5. 쿼리 실행
	6. 결과 집합 핸들링(select..)
	7. close(***) - try with resource 구문 활용
</pre>

<!-- 	비동기요청발생 -> 비동기요청의 응답컨텐츠는 json -> json데이터 받아서 tr태그 동적으로 생성 -->
<table>
   <thead>
      <tr>
         <th>PROPERTY_NAME</th>
         <th>PROPERTY_VALUE</th>
         <th>DESCRIPTION</th>
      </tr>
   </thead>
   <tbody id="listBody">
   </tbody>
</table>
<script src="<%=request.getContextPath() %>/resources/js/app/13/jdbcDesc.js">

</script>
</body>
</html>