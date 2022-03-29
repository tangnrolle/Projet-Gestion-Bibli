package com.ensta.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreServiceImpl;

@WebServlet("/emprunt_return")
public class EmpruntReturnServlet extends HttpServlet {
    EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
    LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("listEmpruntNonRendus", this.empruntServiceImpl.getListCurrent());
            int idEmprunt = (request.getParameter("id") == null) ? -1 : Integer.valueOf(request.getParameter("id"));

            if (idEmprunt != -1) {
                request.setAttribute("idSelectedEmprunt", idEmprunt);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - EmpruntReturnServlet.doGet");
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idEmprunt = Integer.valueOf(request.getParameter("id"));

            empruntServiceImpl.returnBook(empruntServiceImpl.getById(idEmprunt).getLivre().getId());

            response.sendRedirect("dashboard");

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - EmpruntReturnServlet.doPost");
        }
    }
}
