<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 8/11/2025
  Time: 11:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Customer - Pahana Edu</title>
</head>
<body>
<h2>Add New Customer</h2>

<% String error = (String) request.getAttribute("error");
    if (error != null) { %>
<p style="color:red;"><%= error %></p>
<% } %>

<form action="addCustomer" method="post">
    Account Number: <input type="text" name="accountNumber" required maxlength="50"><br><br>
    Name: <input type="text" name="name" required maxlength="100"><br><br>
    Address: <input type="text" name="address" maxlength="255"><br><br>
    Telephone: <input type="text" name="telephone" maxlength="20"><br><br>
    Units Consumed: <input type="number" name="unitsConsumed" min="0" value="0" required><br><br>

    <input type="submit" value="Add Customer">
</form>

<p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
