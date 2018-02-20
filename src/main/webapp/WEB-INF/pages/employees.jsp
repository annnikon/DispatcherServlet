<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<p style="color:darkslategrey;">${status}</p>
<br/>
<c:if test="${not empty employees}">

    <span>Employees at ${departmentName}</span>
    <table border="1">
        <th>Email</th>
        <th>Name</th>
        <th>Birthday</th>
        <th>Room</th>
        <th>Action</th>
        <c:forEach items="${employees}" var="employee">

            <tr>
                <td>${employee.getEmail()}</td>
                <td>${employee.getName()}</td>
                <td>${employee.getBirthday()}</td>
                <td>${employee.getRoom()}</td>
                <form action="employees">
                    <input type="hidden" name="departmentName" value="${departmentName}">
                    <input type="hidden" name="email" value="${employee.getEmail()}">
                <td><input type="submit" name="action" value="edit"/></td>
                <td><input type="submit" name="action" value="delete"/></td>
                </form>
            </tr>

        </c:forEach>

    </table>

</c:if>
<c:if test="${empty employees}">This department has not employees yet</c:if>

<form action="employees">
    <label>You can <input type="submit" name="action" value="add"/>
        new employee </label>
    <input type="hidden" name="departmentName" value="${departmentName}">

</form>
<a href="departments?action=list">Go back</a>

</body>
</html>
