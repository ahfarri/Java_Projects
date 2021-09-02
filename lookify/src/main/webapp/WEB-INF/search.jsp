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

<div class="row mt-5 mb-5">
	<h2 class="col text-center navy">Songs by Artist: ${artist}</h2>
	<form class="col" action="/search" method="post">
	    <input type="search" name="artist" class="col-8 mt-2 pb-2">
	    <input type="submit" value="New Search" class="btn btn-info col-3"/>
	</form>
</div>

<table class="table table-hover">
    <thead>
    	<tr>
        	<th>Name</th>
        	<th>Rating</th>
       		<th>Actions</th>
       	</tr>
    </thead>
    <tbody>
    	<c:forEach items='${search}' var='song'>			
        <tr>
            <td><a href="/songs/${song.id}" class="a text-warning">${song.title}</a></td>
            <td>${song.rating}</td>
            <td><a href="/delete/${song.id}" class="a navy">Delete</a></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>