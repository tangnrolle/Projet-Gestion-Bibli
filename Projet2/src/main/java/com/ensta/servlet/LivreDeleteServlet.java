package com.ensta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreServiceImpl;

@WebServlet("/livre_delete")
public class LivreDeleteServlet extends HttpServlet {
    LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idDuLivre = Integer.valueOf(request.getParameter("id"));
            request.setAttribute("idDuLivre", idDuLivre);
            request.setAttribute("titre", livreServiceImpl.getById(idDuLivre).getTitre());
            request.setAttribute("auteur", livreServiceImpl.getById(idDuLivre).getAuteur());
            request.setAttribute("isbn", livreServiceImpl.getById(idDuLivre).getIsbn());

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - LivreDeleteServlet.doGet");
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_delete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idDuLivre = Integer.valueOf(request.getParameter("id"));

            livreServiceImpl.delete(idDuLivre);

            response.sendRedirect("livre_list");

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - LivreDeleteServlet.doPost");
        }
    }
}
