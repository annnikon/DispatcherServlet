<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Departments</title>
      <link rel="stylesheet" href="../css/style.css">
  </head>
  <body>
  <p style="color:darkslategrey;">${status}</p>
  <br/>
  <c:if test="${not empty departments}">

          <span>All Departments</span>
          <table border="1">
              <th>Department name</th>
              <th>Info</th>
              <th>Action</th>

              <c:forEach items="${departments}" var="department">

                  <tr>
                      <form method="get" action="departments">
                      <input type="hidden" name="name" value="${department.getName()}"/>
                      <td>${department.getName()}</td>
                      <td>${department.getInfo()}</td>
                      <td><input type="submit" name="action" value="edit"/></td>
                      <td><input type="submit" name="action" value="delete"/></td>
                      </form>
                      <form method="get" action="employees">
                          <input type="hidden" name="departmentName" value="${department.getName()}"/>
                          <td><input type="submit" name="action" value="list"/></td>
                      </form>

                  </tr>

              </c:forEach>
          </table>

  </c:if>


              <c:if test="${empty departments}">
              There are no any departments
              </c:if>
<form method="get" action="departments">
    <label>You can add new department: </label>
  <input type="submit" name="action" value="add"/>
</form>

  </body>
</html>
