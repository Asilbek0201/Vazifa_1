<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Group List Page</title>
  <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>

<div class="container mt-5">
  <div class="row">
    <div class="col-md-10 offset-1">
      <table class="table">
        <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Name</th>
          <th scope="col">Student Count</th>
          <th scope="col">Created At</th>
          <th scope="col">Created By</th>
          <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${groups}" var="group">
          <tr>
            <td><c:out value="${group.getId()}"/></td>
            <td><c:out value="${group.getName()}"/></td>
            <td><c:out value="${group.getStudentCount()}"/></td>
            <td><c:out value="${group.getCreatedAt()}"/></td>
            <td><c:out value="${group.getCreatedBy()}"/></td>
            <td>
              <a href="/group/delete/${group.getId()}" class="btn btn-danger">Delete</a>  ||
              <a href="/group/update/${group.getId()}" class="btn btn-warning">Update</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
