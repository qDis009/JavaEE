package com.example.servlets;

import db.Countries;
import db.DBManager;
import db.Items;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/edit")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id=0;
        try {
            id=Long.parseLong(request.getParameter("id"));
        }catch (Exception e){
            e.printStackTrace();
        }
        Items item= DBManager.getItem(id);
        if(item!=null){
            ArrayList<Countries> countries=(ArrayList<Countries>)DBManager.getCountries();
            request.setAttribute("countries",countries);
            request.setAttribute("item",item);
            request.getRequestDispatcher("/edit.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/404.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id=Long.parseLong(request.getParameter("id"));
        String name=request.getParameter("name");
        int amount=Integer.parseInt(request.getParameter("amount"));
        int price=Integer.parseInt(request.getParameter("price"));
        long countryId=Long.parseLong(request.getParameter("manufacturer_id"));
        Countries cnt=DBManager.getCountry(countryId);
        String redirect="/";
        if(cnt!=null){
            Items item=DBManager.getItem(id);
            if(item!=null){
                item.setName(name);
                item.setAmount(amount);
                item.setPrice(price);
                item.setManufacturer(cnt);
                if(DBManager.saveItem(item)){
                    redirect="/edit?id="+id+"&success";
                }else{
                    redirect="/edit?id="+id+"&error";
                }
            }
        }
        response.sendRedirect(redirect);
    }
}
