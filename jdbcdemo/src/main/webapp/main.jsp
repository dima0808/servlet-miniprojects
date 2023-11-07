<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Students</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<button onclick="window.location.href='add-student-form.html';">Add student</button>

<table>

    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>

    <c:forEach var="student" items="${students}">
        <c:url value="MainServlet" var="tempUpdateLink">
            <c:param name="command" value="LOAD" />
            <c:param name="id" value="${student.id}" />
        </c:url>

        <c:url value="MainServlet" var="tempDeleteLink">
            <c:param name="command" value="DELETE" />
            <c:param name="id" value="${student.id}" />
        </c:url>
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.email}</td>
            <td><a href="${tempUpdateLink}">update</a> |
                <a href="${tempDeleteLink}"
                   onclick="if (!(confirm('Are you sure you want to delete student?'))) return false">delete</a></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
