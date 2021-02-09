<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${allMeals}" var="ObjMeal">
        <tr style="${ObjMeal.getExcess() == true ? 'background-color: red':'background-color: green'}">
            <td>${ObjMeal.dateTime()} </td>
            <td>${ObjMeal.getDescription()}</td>
            <td>${ObjMeal.getCalories()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
