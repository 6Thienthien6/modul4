<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 10/3/2022
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Save</title>
</head>
<body>
<c:forEach items="${condiment}" var="item">
    <h3>${item}</h3>
</c:forEach>
</body>
</html>
