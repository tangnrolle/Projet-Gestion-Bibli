package com.ensta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;

@WebServlet("/membre_details")
public class MembreDetailsServlet extends HttpServlet {
    MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
    EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));

            request.setAttribute("idDuMembre", id);
            request.setAttribute("prenomDuMembre", membreServiceImpl.getById(id).getPrenom());
            request.setAttribute("nomDuMembre", membreServiceImpl.getById(id).getNom());
            request.setAttribute("adresseDuMembre", membreServiceImpl.getById(id).getAdresse());
            request.setAttribute("emailDuMembre", membreServiceImpl.getById(id).getEmail());
            request.setAttribute("telephoneDuMembre", membreServiceImpl.getById(id).getTelephone());
            request.setAttribute("aboDuMembre", membreServiceImpl.getById(id).getAbonnement());
            request.setAttribute("empruntsEnCours", empruntServiceImpl.getListCurrentByMembre(id));

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - MembreDetailsServlet.doGet");
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            String prenom = request.getParameter("prenom");
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");
            String abonnement = request.getParameter("abonnement");

            Membre updateMembre = membreServiceImpl.getById(id);

            updateMembre.setPrenom(prenom);
            updateMembre.setNom(nom);
            updateMembre.setAdresse(adresse);
            updateMembre.setEmail(email);
            updateMembre.setTelephone(telephone);
            updateMembre.setAbonnement(Abonnement.valueOf(abonnement));

            membreServiceImpl.update(updateMembre);

            response.sendRedirect("membre_details?id=" + id);

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ServletException("Erreur au niveau du servlet - MembreDetailsServlet.doPost");
        }
    }
}