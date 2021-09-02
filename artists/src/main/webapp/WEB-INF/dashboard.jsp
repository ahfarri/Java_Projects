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

	<h2>Works</h2>
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
			<c:forEach items='${works}' var='work'>			
			<tr>
				<td><a href="/works/${work.id}">${work.name}</a></td>
				
				<c:forEach items='${users}' var='user'>		
				<c:if test='${user.id == work.creator}'>
					<td>${user.userName}</td>
				</c:if>
				</c:forEach>
				<c:if test='${work.users.size() == 0}'>
					<c:set  var = "good" value="false">
					</c:set>
				</c:if>
				<c:forEach items='${work.users}' var='user'>		
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
				<td>${work.users.size()}</td>
				<c:choose>
				<c:when test='${good =="true"}'>
					<td><a href="/unlikes/${work.id}">Unlike</a></td>
				</c:when>
				<c:when test='${good =="false"}'>
					<td><a href="/likes/${work.id}">Like</a></td>
				</c:when>
				</c:choose>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/works/new">Create an idea</a>
</body>
</html>

