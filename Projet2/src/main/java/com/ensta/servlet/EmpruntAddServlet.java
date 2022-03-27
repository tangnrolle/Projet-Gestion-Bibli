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

@WebServlet("/emprunt_add")
public class EmpruntAddServlet extends HttpServlet {
    EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
    LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
    MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("listLivre", this.livreServiceImpl.getList());
            request.setAttribute("listeMembre", this.membreServiceImpl.getList());

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - EmpruntListServlet.doGet");
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
        ;
    }
}
