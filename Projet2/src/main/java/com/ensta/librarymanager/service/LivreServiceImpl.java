package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;

public class LivreServiceImpl implements LivreService {

    private static LivreServiceImpl instance;

    public LivreServiceImpl() {
    }

    public static LivreServiceImpl getInstance() {
        if (instance == null) {
            instance = new LivreServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Livre> getList() throws ServiceException {
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            List<Livre> output = livreDaoImpl.getList();
            return output;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - LivreServiceImpl.getList");
        }
    }

    @Override
    public List<Livre> getListDispo() throws ServiceException {
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
            List<Livre> output = new ArrayList<Livre>();
            List<Livre> input = livreDaoImpl.getList();
            for (int i = 0; i < livreDaoImpl.count(); i++) {
                if (empruntServiceImpl.isLivreDispo(input.get(i).getId())) {
                    output.add(input.get(i));
                }
            }
            return output;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - LivreServiceImpl.getListDispo");
        }
    }

    @Override
    public Livre getById(int id) throws ServiceException {
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();

            if (id < 0) {
                System.out.println("Cet identifiant ne correspond à aucun livre");
                throw new ServiceException("Erreur au niveau du service - LivreServiceImpl.getById");
            }

            else {
                Livre output = livreDaoImpl.getById(id);
                return output;
            }

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - LivreServiceImpl.getById");
        }
    }

    @Override
    public int create(Livre livre) throws ServiceException {
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            if (livre.getTitre() == null || livre.getTitre().equals("")) {
                throw new ServiceException("Le titre d'un livre ne peut pas être vide");
            } else {
                return livreDaoImpl.create(livre);
            }

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - LivreServiceImpl.create");
        }
    }

    @Override
    public void update(Livre livre) throws ServiceException {
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            if (livre.getTitre() == null || livre.getTitre().equals("")) {
                System.out.println("Le titre d'un livre ne peut pas être vide");
            } else {
                livreDaoImpl.update(livre);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - LivreServiceImpl.update");
        }

    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            livreDaoImpl.delete(id);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - LivreServiceImpl.delete");
        }
    }

    @Override
    public int count() throws ServiceException {
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            int output = livreDaoImpl.count();
            return output;

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur au niveau du service - LivreServiceImpl.count");
        }
    }

}
