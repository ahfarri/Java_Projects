<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Language</title>
</head>
<body>
	<a href="/languages">Dashboard</a>
	<p>${language.name}</p>
	<p>${language.creator}</p>
	<p>${language.currentVersion}</p>  
	<a href="/languages/edit/${language.id}">Edit</a>
	<a href="/languages/delete/${language.id}">Delete</a>
</body>
</html>