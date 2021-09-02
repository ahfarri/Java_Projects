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
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
<img src="/images/icon.jpeg" alt="icon" width=45 class="rounded-circle">
<h1 class="text-white col-4 p-2 f2">Lookify</h1>
<small class="text-light col-8">All your favorite music in one place.</small>
</nav> 
<div class="f container-md">
    <div>
        <div class="row justify-content-around align-items-center p-2">
            <a href="/songs/new" class="a btn btn-info col-2">Add New</a>
            <a href="/search/topTen" class="a btn btn-warning col-2 justify-self-center">Top Songs</a>
        	<form class="col-7 form-inline" action="/search" method="post">
           		<input type="search" name="artist" class="col-8 pb-2">
           		<input type="submit" value="Search Artists" class="btn btn-dark col-3"/>    
        	</form>
        </div>
        <div class="flex">
            <table class="table table-width table-hover">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Rating</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items='${songs}' var='song'>
                <tr>
                    <td><a href="/songs/${song.id}" class="a navy">${song.title}</a></td>
                    <td>${song.rating}</td>
                    <td><a href="/delete/${song.id}" class="text-warning a">Delete</a></td>
                </tr>
                </c:forEach>
            </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>