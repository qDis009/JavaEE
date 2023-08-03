package com.example.servlets;

import db.DBManager;
import db.Items;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id=0;
        try {
            id=Long.parseLong(request.getParameter("id"));
        }catch (Exception e){

        }
        Items item=DBManager.getItem(id);
        if(item!=null){
            request.setAttribute("item",item);
            request.getRequestDispatcher("/details.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/404.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
