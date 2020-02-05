<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>CRUD</title>
</head>
<body>
<h1>Nonogram list</h1>
<form method="post" action="/delete">
    <table border="1">
        <tr>
            <th>name</th>
            <th>width</th>
            <th>height</th>
            <th>creation time</th>
            <th>delete</th>
        </tr>
        <c:forEach var="nonogram" items="${sessionScope.nonograms}">
            <tr>
                <td>${nonogram.name}</td>
                <td>${nonogram.width}</td>
                <td>${nonogram.height}</td>
                <td>${nonogram.creationTime}</td>
                <td><input type="checkbox" value="${nonogram.id}" name="nonogramId"></td>
            </tr>
        </c:forEach>

    </table>
    <input type="submit" value="delete">
</form>

<h1>Add new Nonogram</h1>
<form method="post" action="/add">
    <input type="text" name="name" placeholder="Name">
    <input type="number" name="width" placeholder="Width">
    <input type="number" name="height" placeholder="Height">
    <br>
    <input type="submit" value="submit">
</form>

</body>
</html>
