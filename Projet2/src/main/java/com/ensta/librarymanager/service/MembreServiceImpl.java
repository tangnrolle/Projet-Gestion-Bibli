package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Membre;

public class MembreServiceImpl implements MembreService {

    private static MembreServiceImpl instance;

    public MembreServiceImpl() {
    }

    public static MembreServiceImpl getInstance() {
        if (instance == null) {
            instance = new MembreServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Membre> getList() throws ServiceException {
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            List<Membre> output = membreDaoImpl.getList();
            return output;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - MembreServiceImpl.getList");
        }
    }

    @Override
    public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
            List<Membre> output = new ArrayList<Membre>();
            List<Membre> input = membreDaoImpl.getList();
            for (int i = 0; i < membreDaoImpl.count(); i++) {
                if (empruntServiceImpl.isEmpruntPossible(input.get(i))) {
                    output.add(input.get(i));
                }
            }
            return output;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - MembreServiceImpl.getListMembreEmpruntPossible");
        }
    }

    @Override
    public Membre getById(int id) throws ServiceException {
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();

            if (id < 0 || id > membreDaoImpl.count()) {
                System.out.println("Cet identifiant ne correspond à aucun membre");
                return null;
            }

            else {
                Membre output = membreDaoImpl.getById(id);
                return output;
            }

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - MembreServiceImpl.getById");
        }
    }

    @Override
    public int create(Membre membre)
            throws ServiceException {
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            if (membre.getNom() == null || membre.getNom().equals("") || membre.getPrenom() == null
                    || membre.getPrenom().equals("")) {
                throw new ServiceException("Le nom ou le prenom d'un membre ne peuvent pas être vides");
            } else {
                membreDaoImpl.create(membre);
                return 1;
            }

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - MembreServiceImpl.create");
        }
    }

    @Override
    public void update(Membre membre) throws ServiceException {
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            if (membre.getNom() == null || membre.getNom().equals("") || membre.getPrenom() == null
                    || membre.getPrenom().equals("")) {
                throw new ServiceException("Le nom ou le prenom d'un membre ne peuvent pas être vides");
            } else {
                membreDaoImpl.update(membre);
            }

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - MembreServiceImpl.update");
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            membreDaoImpl.delete(id);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - MembreServiceImpl.delete");
        }
    }

    @Override
    public int count() throws ServiceException {
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            int output = membreDaoImpl.count();
            return output;

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - MembreServiceImpl.count");
        }
    }

}
