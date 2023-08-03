package com.example.servlets;

import db.Countries;
import db.DBManager;
import db.Items;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/additem")
public class AddItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Countries> countries = DBManager.getCountries();
        request.setAttribute("countries", countries);
        request.getRequestDispatcher("/additem.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int price = Integer.parseInt(request.getParameter("price"));
        long countryId = Long.parseLong(request.getParameter("manufacturer_id"));
        Countries cnt = DBManager.getCountry(countryId);
        if (cnt != null) {
            Items it = new Items(null, name, price, amount, cnt);
            DBManager.addItem(it);
            response.sendRedirect("/additem?success");
        } else {
            response.sendRedirect("/additem?error");
        }
    }
}
