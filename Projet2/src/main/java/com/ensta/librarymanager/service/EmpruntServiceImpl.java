package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;

public class EmpruntServiceImpl implements EmpruntService {
    public static EmpruntServiceImpl instance;

    public EmpruntServiceImpl() {
    }

    public static EmpruntServiceImpl getInstance() {
        if (instance == null) {
            instance = new EmpruntServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Emprunt> getList() throws ServiceException {
        try {
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            List<Emprunt> output = empruntDaoImpl.getList();
            return output;

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.getList");
        }
    }

    @Override
    public List<Emprunt> getListCurrent() throws ServiceException {
        try {
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            List<Emprunt> output = empruntDaoImpl.getListCurrent();
            return output;

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.getListCurrent");
        }
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
        try {
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();

            if (idMembre < 0) {
                System.out.println("Cet identifiant ne correspond à aucun membre");
                throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.getListCurrentByMembre");
            }

            else {
                List<Emprunt> output = empruntDaoImpl.getListCurrentByMembre(idMembre);
                return output;
            }

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.getListCurrentByMembre");
        }
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
        try {
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();

            if (idLivre < 0) {
                System.out.println("Cet identifiant ne correspond à aucun livre");
                throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.getListCurrentByLivre");
            }

            else {
                List<Emprunt> output = empruntDaoImpl.getListCurrentByLivre(idLivre);
                return output;
            }

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.getListCurrentByLivre");
        }
    }

    @Override
    public Emprunt getById(int id) throws ServiceException {
        try {
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();

            if (id < 0) {
                System.out.println("Cet identifiant ne correspond à aucun emprunt");
                throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.getListCurrentByLivre");
            }

            else {
                Emprunt output = empruntDaoImpl.getById(id);
                return output;
            }

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.getListCurrentByLivre");
        }
    }

    public void create(Emprunt emprunt) throws ServiceException {
        try {
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            empruntDaoImpl.create(emprunt);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.create");
        }
    }

    @Override
    public void returnBook(int idLivre) throws ServiceException {
        try {
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();

            Emprunt retour = empruntDaoImpl.getListCurrentByLivre(idLivre).get(0);

            if (retour != null) {
                retour.setDateRetour(LocalDate.now());
                empruntDaoImpl.update(retour);
            }

            else {
                System.out.println("Ce livre n'a pas été emprunté");
            }

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.returnBook");
        }
    }

    @Override
    public int count() throws ServiceException {
        try {
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            return (empruntDaoImpl.count());

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.count");
        }
    }

    @Override
    public boolean isLivreDispo(int idLivre) throws ServiceException {
        try {
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();

            List<Emprunt> dispo = empruntDaoImpl.getListCurrentByLivre(idLivre);

            if (dispo.size() == 0 || dispo == null)
                return true;

            else
                return false;

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.count");
        }
    }

    @Override
    public boolean isEmpruntPossible(Membre membre) throws ServiceException {
        try {
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();

            int quota = membre.getAbonnement().getquota();
            int nb_emprunts = empruntDaoImpl.getListCurrentByMembre(membre.getId()).size();

            if (nb_emprunts < quota) {
                return true;
            }

            else
                return false;

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - EmpruntServiceImpl.count");
        }
    }

}
