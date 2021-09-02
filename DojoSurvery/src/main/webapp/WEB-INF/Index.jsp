<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>First JSP</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="js/app.js"></script>
</head>
<body>
    <h1>Your Info:</h1>
	<form action="/create" method="post">
		<div>
			<label for="">Your Name: </label>
			<input type="text" name="yourname" id="" />
		</div>
		<div>
			<label for="">Dojo Location:</label>
			<select name="location" id="">
				<option value="sanjose">San Jose</option>
				<option value="online">Online</option>
				<option value="burbank">Burbank</option>
				<option value="florida">Florida</option>
			</select>
		</div>
		<div>
			<label for="">Favorite Language:</label>
			<select name="language" id="">
				<option value="python">Python</option>
				<option value="js">JS</option>
				<option value="java">Java</option>
				<option value="html/css">Html/CSS</option>
			</select>
		</div>
		<div>
			<label for="">Comment (Optional):</label>
			<textarea name="comment" id="" cols="30" rows="10"></textarea>
		</div>
		<input type="submit" value="Button" />
	</form>
</body>
</head>
