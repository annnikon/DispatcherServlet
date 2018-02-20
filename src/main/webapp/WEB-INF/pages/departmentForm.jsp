<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit | Create new department</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<form action="departments" method="post">
    <input type="hidden" name="name" value="${name}">
    Name: <input type="text" name="newName" value="${newName}"/>
    Info: <input type="text" name="newInfo" value="${newInfo}"/>

    <c:if test="${param.action eq 'add'}">
        <input type="submit" name="action" value="add"/>
    </c:if>

    <c:if test="${param.action eq 'edit'}">
        <input type="submit" name="action" value="edit"/>
    </c:if>

    <p style="color: brown">${warning}</p>

    <c:forEach items="${errors}" var="entry">
        <p style="color:red;"><strong>${entry.key}: </strong>${entry.value}</p>
    </c:forEach>


</form>

</body>
</html>
