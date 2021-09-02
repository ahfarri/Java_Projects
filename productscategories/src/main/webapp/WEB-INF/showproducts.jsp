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
<h1>${product.name}</h1>
<h3>Categories:</h3>

<c:forEach items='${product.categories}' var='category'>
	<p>-${category.name}
</c:forEach>			
	
<form action="/product/add" method="post">
    <p>
        <select name="categories">
            <c:forEach items='${freecategories}' var='free'>
				<option value="${free.id}">${free.name}</option>
			</c:forEach>
        </select>
    </p>
    <input type="submit" value="Add"/>
</form> 
</body>
</html>