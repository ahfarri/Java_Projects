<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/app.js"></script>
</head>
<body>
	<h1>Submitted Info:</h1>
	
	<p>Name: ${yourname}</p>
	<p>Dojo Location: ${location}</p>
	<p>Favorite Language: ${lang}</p>
	<p>Comment: ${comment}</p>
	
	<a href="/"><input type="submit" value="Go Back"/></a>
</body>
</html>