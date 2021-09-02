<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href=/css/style.css>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand bg-dark navbar-dark">
<img src="/images/icon.jpeg" alt="icon" width=45 class="rounded-circle">
<h1 class="text-white col-4 p-2 f2">Lookify</h1>
<small class="text-light col-6">All your favorite music in one place.</small>
<a href="/dashboard"  class="col-1 a btn btn-warning">Dashboard</a>
</nav>

<div>
	<form:form class="form-control" action="/create/new" method="post" modelAttribute="song">
	    <p>
	        <form:label path="title">Title</form:label>
	        <form:errors path="title"/>
	        <form:input class="form-control" path="title"/>
	    </p>
	    <p>
	        <form:label path="artist">Artist</form:label>
	        <form:errors path="artist"/>
	        <form:input class="form-control" path="artist"/>
	    </p>
	    <p>
	        <form:label path="rating">Rating (1-10)</form:label>
	        <form:errors path="rating"/>
	        <form:input class="form-control" type="number" path="rating"/>
	    </p>
	      
	    <input class="btn btn-info" type="submit" value="Add Song"/>
	</form:form> 
</div>
</body>
</html>