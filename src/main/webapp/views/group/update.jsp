<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Group Update</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<form method="post">
    <div class="mb-3">
        <label for="name" class="form-label">Group name</label>
        <input type="text" class="form-control" id="name" name="name" value="${group.getName()}">
    </div>
    <button type="submit" class="btn btn-success">Update</button>
    <a href="/" class="btn btn-warning">Back</a>
</form>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
