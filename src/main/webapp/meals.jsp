<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <c:forEach var="ObjMeal" items="${request.getAttribute(\"list\")}">
        <tr>
            <td><c:out value="${ObjMeal.getDateTime}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
