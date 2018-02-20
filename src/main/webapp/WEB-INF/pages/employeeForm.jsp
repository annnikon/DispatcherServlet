<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add | Edit employee</title>
</head>
<body>
<form action="employees" method="post">

    <br/>
    <input type="hidden" name="email" value="${email}" />
    <input type="hidden" name="departmentName" value="${departmentName}" />

    Email: <input type="text" name="newEmail" value="${newEmail}"/>
    <br/>
    Name: <input type="text" name="newName" value="${newName}"/>
    <br/>
    Birthday: <input type="text" name="newBirthday" value="${newBirthday}"/>
    <br/>
    Room: <input type="text" name="newRoom" value="${newRoom}"/>
    <br/>
    Department: <input type="text" name="newDepartmentName" value="${newDepartmentName}"/>
    <br/>


    <c:if test="${param.action eq 'add'}">
        <input type="submit" name="action" value="add"/>
    </c:if>

    <c:if test="${param.action eq 'edit'}">
        <input type="submit" name="action" value="edit"/>
    </c:if>

</form>

<p style="color: brown">${warning}</p>

<c:forEach items="${errors}" var="entry">
    <p style="color:red;"><strong>${entry.key}: </strong>${entry.value}</p>
</c:forEach>
</body>
</html>
