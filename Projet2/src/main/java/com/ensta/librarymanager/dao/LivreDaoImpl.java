package com.ensta.librarymanager.dao;

import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;

public class LivreDaoImpl implements LivreDao {
    private static LivreDaoImpl instance;

    private LivreDaoImpl() {
    }

    public static LivreDaoImpl getInstance() {
        if (instance == null) {
            instance = new LivreDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Livre> getList() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Livre getById(int id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws DaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void update(Livre livre) throws DaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int id) throws DaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public int count() throws DaoException {
        // TODO Auto-generated method stub
        return 0;
    }

}
