<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Student Update Form</title>
    <link type="text/css" rel="stylesheet" href="css/form-style.css">
</head>
<body>

<h3>Update student</h3>

<form action="MainServlet" method="GET">

    <input type="hidden" name="command" value="UPDATE" />
    <input type="hidden" name="id" value="${student.id}" />

    <table>
        <tbody>
        <tr>
            <td><label>First name:</label></td>
            <td><label><input type="text" name="firstName" value="${student.firstName}" /></label></td>
        </tr>
        <tr>
            <td><label>Last name:</label></td>
            <td><label><input type="text" name="lastName" value="${student.lastName}" /></label></td>
        </tr>
        <tr>
            <td><label>Email:</label></td>
            <td><label><input type="text" name="email" value="${student.email}" /></label></td>
        </tr>
        <tr>
            <td></td>
            <td><label><input type="submit" value="Save" /></label></td>
        </tr>
        </tbody>
    </table>

</form>

</body>
</html>
