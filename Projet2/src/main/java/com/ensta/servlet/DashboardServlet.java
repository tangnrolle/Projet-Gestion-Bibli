package com.ensta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
    MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
    EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("livreCount", this.livreServiceImpl.count());
            request.setAttribute("membreCount", this.membreServiceImpl.count());
            request.setAttribute("empruntCount", this.empruntServiceImpl.count());
            request.setAttribute("listEmpruntEnCours", this.empruntServiceImpl.getListCurrent());

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
