package com.example.pahanaedu.controller;

import com.example.pahanaedu.dao.CustomerDAO;
import com.example.pahanaedu.model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/editCustomer")
public class EditCustomerServlet extends HttpServlet {

    private CustomerDAO dao = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect("customers");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            resp.sendRedirect("customers");
            return;
        }

        Customer customer = dao.getById(id);
        if (customer == null) {
            resp.sendRedirect("customers");
            return;
        }

        req.setAttribute("customer", customer);
        req.getRequestDispatcher("edit-customer.jsp").forward(req, resp);
    }

    // POST handles the update
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String account = req.getParameter("accountNumber");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        String unitsStr = req.getParameter("unitsConsumed");

        // server-side validation
        if (account == null || account.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            req.setAttribute("error", "Account number and name are required.");
            req.setAttribute("customer", new Customer(id, account, name, address, telephone, 0));
            req.getRequestDispatcher("edit-customer.jsp").forward(req, resp);
            return;
        }

        int units = 0;
        try {
            units = Integer.parseInt(unitsStr);
            if (units < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Units consumed must be a non-negative integer.");
            req.setAttribute("customer", new Customer(id, account, name, address, telephone, 0));
            req.getRequestDispatcher("edit-customer.jsp").forward(req, resp);
            return;
        }

        // uniqueness check for account_number
        Customer existing = dao.getByAccountNumber(account);
        if (existing != null && existing.getId() != id) {
            req.setAttribute("error", "Account number is already used by another customer.");
            req.setAttribute("customer", new Customer(id, account, name, address, telephone, units));
            req.getRequestDispatcher("edit-customer.jsp").forward(req, resp);
            return;
        }

        Customer updated = new Customer(id, account, name, address, telephone, units);
        boolean ok = dao.updateCustomer(updated);
        if (ok) {
            resp.sendRedirect("customers");
        } else {
            req.setAttribute("error", "Failed to update customer.");
            req.setAttribute("customer", updated);
            req.getRequestDispatcher("edit-customer.jsp").forward(req, resp);
        }
    }
}
