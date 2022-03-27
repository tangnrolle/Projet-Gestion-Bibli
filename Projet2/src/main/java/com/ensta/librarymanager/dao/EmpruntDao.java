package com.ensta.librarymanager.dao;

import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;

public interface EmpruntDao {
	public List<Emprunt> getList() throws DaoException;

	public List<Emprunt> getListCurrent() throws DaoException;

	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException;

	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException;

	public Emprunt getById(int id) throws DaoException;

	public int create(Emprunt emprunt) throws DaoException;

	public void update(Emprunt emprunt) throws DaoException;

	public int count() throws DaoException;
}
