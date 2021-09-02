<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of All Languages</title>
</head>
<body>
<h1>All Languages</h1>
<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Creator</th>
            <th>Version</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${languages}" var="l">
        <tr>
            <td><a href="/languages/${l.id}">${l.name}</a></td>
            <td><c:out value="${l.creator}"/></td>
            <td><c:out value="${l.currentVersion}"/></td>
			<td><a href="/languages/edit/${l.id}">Edit</a>|<a href="/languages/delete/${l.id}">Delete</a></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<h1>New Language</h1>
<form:form action="/languages" method="post" modelAttribute="language">
    <p>
        <form:label path="name">Name</form:label>
        <form:errors path="name"/>
        <form:input path="name"/>
    </p>
    <p>
        <form:label path="creator">Creator</form:label>
        <form:errors path="creator"/>
        <form:textarea path="creator"/>
    </p>
    <p>
        <form:label path="currentVersion">Version</form:label>
        <form:errors path="currentVersion"/>
        <form:input path="currentVersion"/>
    </p>
      
    <input type="submit" value="Submit"/>
</form:form>  
</body>
</html>