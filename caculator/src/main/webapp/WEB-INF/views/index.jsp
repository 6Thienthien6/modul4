<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Caculator</title>
</head>
<body>
<h1>Caculator</h1>
<form action="/request" method="post">
    <input type="number" name="number1" value="${number1}">
    <input type="number" name="number2" value="${number2}">
    <br><br>
    <button type="submit" value='+' name="caculator">Addition(+)</button>
    <button type="submit" value='-' name="caculator">Subtraction(-)</button>
    <button type="submit" value='*' name="caculator">Multiplication(x)</button>
    <button type="submit" value='/' name="caculator">Division(/)</button>
</form>
<div>
    <p>Result Division: </p>
    <p>${result}</p>
</div>
</body>
</html>