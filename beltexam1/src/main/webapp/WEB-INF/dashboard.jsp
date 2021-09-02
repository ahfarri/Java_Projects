<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome</title>
</head>
<body>
	<h1>Welcome, <c:out value="${user}" />!</h1>
	<a href="/logout">Logout</a>

	<h2>Ideas</h2>
	<a href="/low/likes">Low Likes</a>
	<a href="/high/likes">High Likes</a>
	<table>
		<thead>
			<tr>
				<th>Idea</th>
				<th>Created By:</th>
				<th>Likes</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items='${ideas}' var='idea'>			
			<tr>
				<td><a href="/ideas/${idea.id}">${idea.name}</a></td>
				
				<c:forEach items='${users}' var='user'>		
				<c:if test='${user.id == idea.creator}'>
					<td>${user.userName}</td>
				</c:if>
				</c:forEach>
				<c:if test='${idea.users.size() == 0}'>
					<c:set  var = "good" value="false">
					</c:set>
				</c:if>
				<c:forEach items='${idea.users}' var='user'>		
				<c:choose>
				<c:when test='${user.id == session}'>
				    	<c:set  var = "good" value="true">
					</c:set>
				</c:when>
				<c:when test='${user.id != session}'>
				    	<c:set  var = "good" value="false">
					</c:set>
				</c:when>
				</c:choose>
				</c:forEach>
				<td>${idea.users.size()}</td>
				<c:choose>
				<c:when test='${good =="true"}'>
					<td><a href="/unlikes/${idea.id}">Unlike</a></td>
				</c:when>
				<c:when test='${good =="false"}'>
					<td><a href="/likes/${idea.id}">Like</a></td>
				</c:when>
				</c:choose>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/ideas/new">Create an idea</a>
</body>
</html>

