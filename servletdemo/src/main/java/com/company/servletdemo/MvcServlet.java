package com.company.servletdemo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
//import java.io.PrintWriter;

@WebServlet(name = "MvcServlet", urlPatterns = "/MvcServlet")

public class MvcServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] nameArr = {"Dmytro", "Vlad", "Adolf", "Joseph"};
        request.setAttribute("names", nameArr);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc_page.jsp");
        dispatcher.forward(request, response);

//        response.setContentType("text/html");
//
//        PrintWriter pw = response.getWriter();
//
//        pw.println("<html><body>");
//        pw.println("cho");
//        pw.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}