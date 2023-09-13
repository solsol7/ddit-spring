<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> 세션 (HttpSession)</h4>
<pre>
	세션
		시간(ConnectLess) : 클라이언트가 어플리케이션을 사용하고 있는 기간.
			최초의 요청이 발생하면 세션이 생성되고, 사용 종료 이벤트가 발생하면 세션이 제거됨.
			사용 종료 이벤트 종류
			1. log out
			2. timeout 이내에 새로운 요청이 발생하지 않으면, 종료로 간주함.
				(이번 요청과 다음번 요청 사이의 인터벌이 타임아웃 이상으로 벌어졌을 때)
			3. session tracking mode에 따라 세션 아이디가 재전송되지 않을 때.
				ex) 클라이언트 측에서 쿠키를 제거함.
			4. 브라우저 종료
		통로(ConnectFull) : connection open(==session create)~connection close(==session destroy)
		
		세션 유지 방법(세션 식별 방법 필요)
		 - 세션이 유지되기 위해 세션 식별자가 클라이언트에서 서버로 재전송되는 방법론 : tracking mode
		 	클라이언트가 세션아이디 갖고있어야하고
		 	그 아이디가 다시 서버로 재전송되어야함
		 세션 생성 시점 : <%=new Date(session.getCreationTime()) %>
		 세션 아이디 : <%=session.getId() %>
		 현재 세션 내에서 발생한 마지막 요청 시점 : <%=new Date(session.getLastAccessedTime()) %>
		 1. Cookie
		 2. URL : <a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">세션 파라미터를 통한 세션 유지</a>
		 3. SSL
</pre>
</body>
</html>