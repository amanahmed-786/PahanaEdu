<%@page import="java.util.List" %>
<%@page import="com.example.pahanaedu.model.Customer" %>
<%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
%>
<html>
<head><title>Customers - Pahana Edu</title></head>
<body>
<h2>Customers</h2>

<p><a href="add-customer.jsp">Add New Customer</a> | <a href="dashboard.jsp">Dashboard</a></p>

<table border="1" cellpadding="6" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Account No</th>
        <th>Name</th>
        <th>Address</th>
        <th>Telephone</th>
        <th>Units</th>
        <th>Actions</th> <!-- Added Actions column -->
    </tr>
    <% if (customers != null) {
        for (Customer c : customers) { %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getAccountNumber() %></td>
        <td><%= c.getName() %></td>
        <td><%= c.getAddress() %></td>
        <td><%= c.getTelephone() %></td>
        <td><%= c.getUnitsConsumed() %></td>
        <td>
            <a href="editCustomer?id=<%= c.getId() %>">Edit</a> |
            <a href="deleteCustomer?id=<%= c.getId() %>"
               onclick="return confirm('Are you sure you want to delete this customer?');">
                Delete
            </a>
        </td>
    </tr>
    <%   }
    } %>
</table>
</body>
</html>
