<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.example.pahanaedu.model.Customer" %>
<%
    Customer c = (Customer) request.getAttribute("customer");
    if (c == null) {
        response.sendRedirect("customers");
        return;
    }
%>
<html>
<head>
    <title>Edit Customer</title>
</head>
<body>
<h2>Edit Customer</h2>

<% String error = (String) request.getAttribute("error");
    if (error != null) { %>
<p style="color:red;"><%= error %></p>
<% } %>

<form action="editCustomer" method="post">
    <input type="hidden" name="id" value="<%= c.getId() %>">
    Account Number: <input type="text" name="accountNumber" value="<%= c.getAccountNumber() %>" required maxlength="50"><br><br>
    Name: <input type="text" name="name" value="<%= c.getName() %>" required maxlength="100"><br><br>
    Address: <input type="text" name="address" value="<%= c.getAddress() %>" maxlength="255"><br><br>
    Telephone: <input type="text" name="telephone" value="<%= c.getTelephone() %>" maxlength="20"><br><br>
    Units Consumed: <input type="number" name="unitsConsumed" min="0" value="<%= c.getUnitsConsumed() %>" required><br><br>

    <input type="submit" value="Update Customer">
</form>

<p><a href="customers">Back to Customer List</a></p>
</body>
</html>
