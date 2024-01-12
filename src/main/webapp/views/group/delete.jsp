<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Group Delete</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<form method="post">
    <div class="alert alert-danger">
        <h1>Are you sure this group <i><c:out value="${group.getName()}"/></i>?</h1>
    </div>
    <a href="/" class="btn btn-warning">Back</a>
    <button type="submit" class="btn btn-danger">Yes</button>
</form>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
