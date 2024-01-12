<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Update</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<form method="post">
    <div class="mb-3">
        <label for="fullName" class="form-label">Student Full Name</label>
        <input type="text" class="form-control" id="fullName" name="fullName" value="${student.getFullName()}">
    </div>
    <div class="mb-3">
        <label for="age" class="form-label">Age</label>
        <input type="number" class="form-control" id="age" name="age" value="${student.getAge()}">
    </div>
    <div class="mb-3">
        <label for="groupId" class="form-label">Group ID</label>
        <input type="number" class="form-control" id="groupId" name="groupId" value="${student.getGroupId()}">
    </div>
    <button type="submit" class="btn btn-success">Update</button>
    <a href="/" class="btn btn-warning">Back</a>
</form>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
