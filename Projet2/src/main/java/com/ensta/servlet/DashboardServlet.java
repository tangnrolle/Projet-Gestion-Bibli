package com.ensta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreServiceImpl;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(this.livreServiceImpl.count());
            request.setAttribute("livreCount", this.livreServiceImpl.count());
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - DashboardServlet.doGet");
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
        ;
    }
}
