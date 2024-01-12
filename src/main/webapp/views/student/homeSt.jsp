<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.vazifa_1.student.model.Student" %>

<html>
<head>
    <title>Student List Page</title>
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
                    <th scope="col">Full Name</th>
                    <th scope="col">Age</th>
                    <th scope="col">Group Id</th>
                    <th scope="col">Created At</th>
                    <th scope="col">Created By</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td><c:out value="${student.id}"/></td>
                        <td><c:out value="${student.fullName}"/></td>
                        <td><c:out value="${student.age}"/></td>
                        <td><c:out value="${student.group.id}"/></td>
                        <td>
                            <fmt:formatDate value="${student.createdAt}"/>
                        </td>
                        <td><c:out value="${student.createdBy}"/></td>
                        <td>
                            <a href="/student/delete/${student.id}" class="btn btn-danger">Delete</a> ||
                            <a href="/student/update/${student.id}" class="btn btn-warning">Update</a>
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


