<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre style="background-color: yellow;">
<%= pageContext.getAttribute("pageAttr") %>
<!-- 	a태그 - 페이지가 달라서 안나옴 -->
<!-- 	forward태그 - 페이지가 달라서 안나옴 -->
<!-- 	include - 어떤 방식으로 이동했든 jsp가 쪼개진거임 - jsp가 바뀌는 순간 그 속성은 사라짐 -->
<%= request.getAttribute("requestAttr") %>
<!-- 	a태그 - 이미 이전 request에 대한 응답이 나온 상태 - request없어진상태 -->
<!--	forward - 요청은 한번밖에 발생하지 않았음 - 요청이 b에 전달됨 - request안없어짐 =>나옴 -->
<%= session.getAttribute("sessionAttr") %>
<!-- 	a태그 - 타임아웃 시간이 지나지않거나 로그아웃하지 않으면 남아있음, 쿠키 지우거나 브라우저 종료하면 사라짐(쓰레기세션됨, 타임아웃 종료되면 없어짐) -->
<%= application.getAttribute("applicationAttr") %>
<!-- 	a태그 - 서버 꺼진 적 없으면(서버가 살아있다면) 어플리케이션 스코프는 계속 확장되고있음 -->
</pre>
</body>
</html>