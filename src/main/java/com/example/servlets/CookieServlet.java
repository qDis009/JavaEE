package com.example.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies=request.getCookies();
        String myCookieValue="No cookie data";
        if(cookies!=null){
            for(Cookie c:cookies){
                if(c.getName().equals("my_cookie_name")){
                    myCookieValue=c.getValue();
                    break;
                }
            }
        }
        request.setAttribute("cookieValue",myCookieValue);
        request.getRequestDispatcher("/cookie.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
