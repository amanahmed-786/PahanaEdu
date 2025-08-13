package com.example.pahanaedu.controller;

import com.example.pahanaedu.dao.CustomerDAO;
import com.example.pahanaedu.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/addCustomer")
public class AddCustomerServlet extends HttpServlet {

    private CustomerDAO dao = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // show the form
        req.getRequestDispatcher("add-customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // ensure proper encoding

        String account = req.getParameter("accountNumber");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        String unitsStr = req.getParameter("unitsConsumed");

        // Server-side validation
        if (account == null || account.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            req.setAttribute("error", "Account number and name are required.");
            req.getRequestDispatcher("add-customer.jsp").forward(req, resp);
            return;
        }

        int units = 0;
        try {
            units = Integer.parseInt(unitsStr);
            if (units < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Units consumed must be a non-negative integer.");
            req.getRequestDispatcher("add-customer.jsp").forward(req, resp);
            return;
        }

        // check uniqueness
        if (dao.getByAccountNumber(account) != null) {
            req.setAttribute("error", "Account number already exists. Choose another.");
            req.getRequestDispatcher("add-customer.jsp").forward(req, resp);
            return;
        }

        Customer c = new Customer(0, account, name, address, telephone, units);
        boolean added = dao.addCustomer(c);

        if (added) {
            resp.sendRedirect("customers"); // show customer list (we will create this)
        } else {
            req.setAttribute("error", "An error occurred while adding the customer.");
            req.getRequestDispatcher("add-customer.jsp").forward(req, resp);
        }
    }
}