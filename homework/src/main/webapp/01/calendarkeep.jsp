<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
}
#calendar td{
	border : 1px solid black;
}

</style>

<%
	
	
// 	int year;
// 	int month;
// 	int date;
// 	int allDate = 1;
// 	int day;	//0 : 일요일, 6 : 토요일
	

/*
	int allDate = 0;
	int date = 0;
	for(int i=1; i<=2022; i++){	
		for(int j=1; j<=12; j++){ 
			if(j==2){
				if(i%4==0 && i%400==0 || i%4==0 && i%100!=0){
					date = 29;
				}else{
					date = 28;
				}
			}
			if(j==1||j==3||j==5||j==7||j==8||j==10||j==12){
				date=31;
			}else if(j==4||j==6||j==9||j==11){
				date=30;
			}
			allDate += date;
		}
	}
*/
%>
<%

	int year = 2023;
	int month = 8;
	
	if(request.getParameter("year")!=null){
		year = Integer.parseInt(request.getParameter("year"));
	}
	if(request.getParameter("month")!=null){
		month = month = Integer.parseInt(request.getParameter("month"));
	}
	
	Map<String, Integer> map = getMonthInfo(year, month);
	Map<String, Integer> prevMap = null;
	Map<String, Integer> nextMap = null;
	if(month==1){
		prevMap = getMonthInfo(year-1, 12);
	}else{
		prevMap = getMonthInfo(year-1, month-1);		
	}
	if(month==1){
		nextMap = getMonthInfo(year-1, 12);
	}else{
		nextMap = getMonthInfo(year-1, month-1);
	}
%>
<%!

public Map<String, Integer> getMonthInfo(int yearParam, int monthParam){

	int year = yearParam;
	int month = monthParam;
	
	LocalDate startDate = LocalDate.of(year, month, 1);
	DayOfWeek day = startDate.getDayOfWeek();
	int dayNum = day.getValue();
	
	int totalDate = 0;
	
	if(totalDate==2){
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
	
	
	LocalDate endDate = LocalDate.of(year, month, totalDate);
	DayOfWeek lastDay = endDate.getDayOfWeek();
	int lastDayNum = lastDay.getValue();
	
	Map<String, Integer> map = new HashMap<>();
	map.put("year", year);
	map.put("month", month);
	map.put("dayNum", dayNum);
	map.put("totalDate", totalDate);
	map.put("lastDayNum", lastDayNum);
	
	return map;
}
%>
</head>
<body>
<table id="displayMonth">
	<tr>
		<td><button type="button" id="btnPrev" onclick="goPrev()">◁◁</button></td>
		<td><h4> <%=map.get("year") %>, <%=map.get("month") %> </h4></td>
		<td><button type="button" id="btnNext">▷▷</button></td>
	</tr>
</table>
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
<table id="calendar">
	<tr>
		<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
	</tr>
	<%

		int cnt = 1;
		if(map.get("dayNum")!=7){
	%>
 			<tr>
	<%
			for(int j=0; j<map.get("dayNum"); j++){
	%>
 				<td></td>
	<%
			}
			for(int j=0; j<7-map.get("dayNum"); j++){
	%>
 				<td><%=j+1 %></td>
	<%
				cnt++;
			}
	%>
 			</tr>
	<%
		}
		int totalDate = map.get("totalDate")+1;
		while(cnt!=totalDate){
	%>
			<tr>
	<%
			for(int j=0; j<7; j++){
	%>
 				<td><%=cnt%></td>
	<%	
				cnt++;
				if(cnt==totalDate) break;
			}

	%>

	<%
		}
		if(map.get("lastDayNum")!=6){
			for(int i=1; i<=7-map.get("lastDayNum")-1; i++){
	%>
					<td style="color:lightgray"><%=i %></td>
	<%
			}
		}
	%>
	 			</tr>

</table>

</body>
</html>