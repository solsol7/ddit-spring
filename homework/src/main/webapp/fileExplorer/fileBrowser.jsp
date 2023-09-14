
<%@page import="java.util.Arrays"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../node_modules/jquery.fancytree/dist/skin-win8/ui.fancytree.min.css" rel="stylesheet">
</head>
<body>
<%
	String url = "/";
	String realPath = application.getRealPath(url);
	File file = new File(realPath);
	File[] fileList = file.listFiles();
	String[] fileListStr = new String[fileList.length];
	for(int i=0; i<fileList.length; i++){
		fileListStr[i] = fileList[i].getName();
	}
%>
<%=Arrays.toString(fileListStr) %><br/>
</body>
</html>