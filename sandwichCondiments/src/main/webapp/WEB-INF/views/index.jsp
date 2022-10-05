<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sandwich Condiments</title>
</head>
<body>
<h1>Sandwich Condiments</h1>

<form action="/save" method="post">
    <input type="checkbox" id="vehicle1" name="condiment" value="Bike">
    <label for="vehicle1"> Lettuce</label>
    <input type="checkbox" id="vehicle2" name="condiment" value="Lettuce">
    <label for="vehicle2"> Tomato</label>
    <input type="checkbox" id="vehicle3" name="condiment" value="Tomato">
    <label for="vehicle2"> Mustard</label>
    <input type="checkbox" id="vehicle4" name="condiment" value="Mustard">
    <label for="vehicle3"> Sprouts</label><br><br>
    <hr>
    <input type="submit" value="Save">
</form>

</body>
</html>