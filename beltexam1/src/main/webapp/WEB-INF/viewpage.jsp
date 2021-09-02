<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${idea.name}</h1>

<h3>Created By: ${user}</h3>

<h1>Users who liked your idea:</h1>
	<table>
		<thead>
			<tr>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items='${users}' var='user'>			
			<tr>
				<td>${user.userName}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
<c:if test='${session == idea.creator}'>
	<a href="/ideas/${idea.id}/edit">Edit</a>
</c:if>
</body>
</html>