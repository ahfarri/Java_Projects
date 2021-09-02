<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Create a new idea</h1>
<form:form action="/ideas/create" method="post" modelAttribute="newIdea">
    
    <p>
        <form:label path="name">Content:</form:label>
        <form:errors path="name"/>
        <form:input path="name"/>
    </p>
    <p>
    	<form:errors path="creator"/>
        <form:hidden path="creator" value="${user_id}"/>
    </p>
      
    <input type="submit" value="Create"/>
</form:form> 
</body>
</html>