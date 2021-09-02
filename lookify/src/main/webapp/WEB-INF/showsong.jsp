<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
	<div class="f container-sm bg-light p-2 mt-5 body">
			<div class="row">
	            <p class="col txtsize">Title:</p>
	            <p class="col info text-warning">${song.title}</p>
			</div>
			<div class="row">
	            <p class="col txtsize">Artist:</p>
	            <p class="col info">${song.artist}</p>
			</div>
			<div class="row">
	            <p class="col txtsize text-white">Rating (1-10):</p>
	            <p class="col info">${song.rating}</p>
			</div>
			<a href="/delete/${song.id}" class="text-white a">Delete</a>
	</div>
</div>
</body>
</html>