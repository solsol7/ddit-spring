<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08</title>
</head>
<body>
<h4>HttpServletResponse</h4>
<pre>
	1. Response Line : Status Code(응답 상태 코드)
		Status Code : 서버에서 요청 처리 결과의 성공 여부를 표현하는 상태 코드
			Http : connectLess + stateLess
		 100~ : ING... webSocket(connectFull) - 한번 연결을 의도적으로 수립해놓으면 직접끊기 전까지 연결 유지
		 200~ : OK
		 300~ : 요청 처리가 최종적으로 완료되려면, 클라이언트로부터 추가 액션이 필요함. response body가 없음.
		 	302, 307(Moved) + Location(자원의 새로운 주소) 헤어 사용
		 	304(Not Modified) : 일반적으로 브라우저는 정적 자원에 대해 캐싱을 해서 사용함.
		 		  한번 캐싱된 자원이 변경된 적 없으므로, 캐시 자원을 그대로 사용하라는 표현.
		 400~ : 처리 실패의 원인이 클라이언트측에 있을 때.
		 	404 (Not Found) - URI가 잘못됨
		 	405 (Method Not Allowed) - method가 잘못됨
		 	400 (Bad Request, 요청 검증에 주로 활용됨.)
		 	
		 	--어플리케이션의 보호를 위한 접근 제어에서 활용됨.
		 	401 (UnAuthorized)
		 	403 (Forbidden)
		 	
		 	406 (Not Acceptable) : 클라이언트가 요청한 Mime content를 전송할 수 없음. - 응답데이터를 못만듦
		 			request header(Accept), response header(Content-Type)
		 	415 (UnSupported Media Type) : 클라이언트가 전송한 content를 판독할 수 없음
		 			request header(Content-Type)
		 500~ : 처리 실패의 원인이 서버측에 있을 때. 500(Internal Server Error)
	2. Response Header
	3. Response Body(Content Body, Message Body)
</pre>
</body>
</html>