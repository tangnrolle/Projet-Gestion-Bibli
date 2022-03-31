package com.ensta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.service.LivreServiceImpl;

@WebServlet("/livre_details")
public class LivreDetailsServlet extends HttpServlet {
    LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));

            request.setAttribute("idDuLivre", id);
            request.setAttribute("titreDuLivre", livreServiceImpl.getById(id).getTitre());
            request.setAttribute("auteurDuLivre", livreServiceImpl.getById(id).getAuteur());
            request.setAttribute("isbnDuLivre", livreServiceImpl.getById(id).getIsbn());

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - LivreDetailsServlet.doPost");
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            String titre = request.getParameter("titre");
            String auteur = request.getParameter("auteur");
            String isbn = request.getParameter("isbn");

            Livre updateLivre = livreServiceImpl.getById(id);

            updateLivre.setTitre(titre);
            updateLivre.setAuteur(auteur);
            updateLivre.setIsbn(isbn);

            livreServiceImpl.update(updateLivre);

            response.sendRedirect("livre_details?id=" + id);

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - LivreAddServlet.doPost");
        }
    }
}
