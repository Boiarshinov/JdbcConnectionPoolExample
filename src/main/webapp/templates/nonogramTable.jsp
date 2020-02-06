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
