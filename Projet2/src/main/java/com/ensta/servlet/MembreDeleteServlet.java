package com.ensta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.MembreServiceImpl;

@WebServlet("/membre_delete")
public class MembreDeleteServlet extends HttpServlet {
    MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idDuMembre = Integer.valueOf(request.getParameter("id"));
            request.setAttribute("idDuMembre", idDuMembre);
            request.setAttribute("prenomDuMembre", membreServiceImpl.getById(idDuMembre).getPrenom());
            request.setAttribute("nomDuMembre", membreServiceImpl.getById(idDuMembre).getNom());

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - MembreDeleteServlet.doGet");
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_delete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idDuMembre = Integer.valueOf(request.getParameter("id"));

            membreServiceImpl.delete(idDuMembre);

            response.sendRedirect("membre_list");

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - MembreDeleteServlet.doPost");
        }
    }
}