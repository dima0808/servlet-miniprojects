<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<body>

Some text I hope it will work :)<br>

<c:forEach var="temp" items="${names}">
    ${temp}<br>
</c:forEach>

</body>
</html>
