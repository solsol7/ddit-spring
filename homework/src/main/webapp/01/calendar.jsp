<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
 * 4의 배수이고 100의 배수가 아니면 윤년
 * 4의 배수이고 400의 배수이면 윤년
 * 윤년이면 2월은 29, 아니면 28일
 * 1, 3, 5, 7, 8, 10, 12 > 31일
 * 4, 6, 9, 11 > 30일
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#displayMonth{
	margin : 0px auto;
}
#calendar{
	text-align : center;
	margin : 0px auto;
	margin-top : 30px;
	width : 70%;
	height : 400px;
}
#calendar th{
	border : 1px solid black;
	background-color: #ffffe0;
}
#calendar td{
	border : 1px solid black;
}
#searchForm{
	display : inline;
	margin : 0px auto;
}
</style>
<%
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date now = new Date();
	String nowDate = format.format(now);

	String[] nowDateArr = nowDate.split("-");
	int currentYear = Integer.parseInt(nowDateArr[0]);
	int currentMonth = Integer.parseInt(nowDateArr[1]);
	int currentDate = Integer.parseInt(nowDateArr[2]);

	int year = currentYear;
	int month = currentMonth;
	int prevMonth = month-1;
	
	if(request.getParameter("year")!=null && request.getParameter("year")!=""){
		year = Integer.parseInt(request.getParameter("year"));
	}
	if(request.getParameter("month")!=null){
		month = Integer.parseInt(request.getParameter("month"));
	}
	
	
	LocalDate startDate = LocalDate.of(year, month, 1);
	DayOfWeek day = startDate.getDayOfWeek();
	int dayNum = day.getValue();
	
	int totalDate = 0;
	
	if(month==2){
		if(month%4==0 && month%400==0 || month%4==0 && month%100!=0){
			totalDate = 29;
		}else{
			totalDate = 28;
		}
	}
	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
		totalDate=31;
	}else if(month==4||month==6||month==9||month==11){
		totalDate=30;
	}
	
	
	/////
	int prevDate = 0;
	if(prevMonth==2){
		if(month%4==0 && month%400==0 || month%4==0 && month%100!=0){
			prevDate = 29;
		}else{
			prevDate = 28;
		}
	}
	if(prevMonth==1||prevMonth==3||prevMonth==5||prevMonth==7||prevMonth==8||prevMonth==10||prevMonth==12){
		prevDate=31;
	}else if(prevMonth==4||prevMonth==6||prevMonth==9||prevMonth==11){
		prevDate=30;
	}
	
	LocalDate endDate = LocalDate.of(year, month, totalDate);
	DayOfWeek lastDay = endDate.getDayOfWeek();
	int lastDayNum = lastDay.getValue();
	

%>
</head>
<body>
<table id="displayMonth">
	<tr>
		<td><button type="button" id="btnPrev" onclick="goPrev()">◁◁</button></td>
		<td><h4> <%=year %>, <%= month%> </h4></td>
				<td><button type="button" id="btnNext" onclick="goNext()">▷▷</button></td>
		
	</tr>
</table>
<div id="searchForm">
<form>
YEAR<input type="text" name="year">
MONTH<select name="month">
<%
	for(int i=1; i<=12; i++){
%>
		<option><%=i %></option>
<%
	}
%>
	
</select>
<button type="submit" >검색</button>
</form>
</div>
<table id="calendar">
	<tr>
		<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
	</tr>
	<%

		int cnt = 1;
		if(dayNum!=7){
	%>
 			<tr>
	<%
			for(int j=0; j<dayNum; j++){
	%>
 				<td style="color:lightgray"><%=prevDate-dayNum+j+1 %></td>
	<%
			}
			for(int j=0; j<7-dayNum; j++){
			if(currentYear==year && currentMonth ==month && currentDate == cnt){
					
	%>
 				<td style="background-color: #e0ffff"><%=cnt%></td>
	<%	
				}else{
	%>
	 				<td><%=j+1 %></td>
	<%			
				}
				cnt++;
			}
	%>
 			</tr>
	<%
		}
		totalDate = totalDate+1;
		while(cnt!=totalDate){
	%>
			<tr>
	<%
			for(int j=0; j<7; j++){
				
				if(currentYear==year && currentMonth ==month && currentDate == cnt){
					
	%>
 				<td style="background-color: #e0ffff"><%=cnt%></td>
	<%	
				}else{
	%>
	 				<td><%=cnt%></td>
	<%			
				}
				cnt++;
				if(cnt==totalDate) break;
			}
		}
		if(lastDayNum!=6){
			for(int i=1; i<=7-lastDayNum%7-1; i++){
	%>
					<td style="color:lightgray"><%=i %></td>
	<%
			}
		}
	%>
	 			</tr>
</table>
<script>
function goPrev(){
	if(<%=month%>==1){
		location.href = "./calendar.jsp?year=<%=year-1%>&month=12";
	}else{
		location.href = "./calendar.jsp?year=<%=year%>&month=<%=month-1%>";
	}
}

function goNext(){
	if(<%=month%>==12){
		location.href = "./calendar.jsp?year=<%=year+1%>&month=1";
	}else{
		location.href = "./calendar.jsp?year=<%=year%>&month=<%=month+1%>";
	}
}
</script>
</body>
</html>