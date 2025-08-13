package com.example.pahanaedu.controller;

import com.example.pahanaedu.dao.CustomerDAO;
import com.example.pahanaedu.model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/customers")
public class ListCustomersServlet extends HttpServlet {

    private CustomerDAO dao = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = dao.getAllCustomers();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("list-customers.jsp").forward(req, resp);
    }
}
