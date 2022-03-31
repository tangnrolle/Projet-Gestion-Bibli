package com.ensta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.modele.Livre;

@WebServlet("/livre_add")
public class LivreAddServlet extends HttpServlet {
    LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String titre = request.getParameter("titre");
            String auteur = request.getParameter("auteur");
            String isbn = request.getParameter("isbn");

            int id = livreServiceImpl.create(new Livre(titre, auteur, isbn));
            response.sendRedirect("livre_details?id=" + id);

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - LivreAddServlet.doPost");
        }
    }
}
