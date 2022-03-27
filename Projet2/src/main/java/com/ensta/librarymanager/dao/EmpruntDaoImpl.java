package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntDaoImpl implements EmpruntDao {
    private static EmpruntDaoImpl instance;
    private Connection connection;
    private String idQuery = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
            + "FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre "
            + "WHERE e.id = ?;";
    private String listQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,dateRetour "
            + "FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre "
            + "ORDER BY dateRetour DESC;";
    private String listCurrentQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
            + "FROM emprunt AS e INNER JOIN membre ON membre.id= e.idMembre INNER JOIN livre ON livre.id= e.idLivre "
            + "WHERE dateRetour IS NULL;";
    private String listCurrentByMembreQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
            + "FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre "
            + "WHERE dateRetour IS NULL AND membre.id = ?;";
    private String listCurrentByLivreQuery = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
            + "FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre "
            + "WHERE dateRetour IS NULL AND livre.id = ?;";
    private String createQuery = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
    private String updateQuery = "UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?;";
    private String countQuery = "SELECT COUNT(id) AS count FROM emprunt;";

    private EmpruntDaoImpl() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static EmpruntDaoImpl getInstance() {
        if (instance == null) {
            instance = new EmpruntDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Emprunt> getList() throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(listQuery);

            List<Emprunt> output = new ArrayList<Emprunt>();

            while (rs.next()) {
                output.add(new Emprunt(rs.getInt("id"),
                        new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
                                rs.getString("email"), rs.getString("adresse"), rs.getString("telephone"),
                                Abonnement.valueOf(rs.getString("abonnement").toUpperCase())),
                        new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),
                                rs.getString("isbn")),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour") == null ? null : rs.getDate("dateRetour").toLocalDate()));
            }

            conn.close();
            return output;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - EmpruntDaoImpl.getList");
        }
    }

    @Override
    public List<Emprunt> getListCurrent() throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(listCurrentQuery);

            List<Emprunt> output = new ArrayList<Emprunt>();

            while (rs.next()) {
                output.add(new Emprunt(rs.getInt("id"),
                        new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
                                rs.getString("email"), rs.getString("adresse"), rs.getString("telephone"),
                                Abonnement.valueOf(rs.getString("abonnement").toUpperCase())),
                        new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),
                                rs.getString("isbn")),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour") == null ? null : rs.getDate("dateRetour").toLocalDate()));
            }

            conn.close();
            return output;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - EmpruntDaoImpl.getListCurrent");
        }
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(listCurrentByMembreQuery);

            List<Emprunt> output = new ArrayList<Emprunt>();

            while (rs.next()) {
                output.add(new Emprunt(rs.getInt("id"),
                        new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
                                rs.getString("email"), rs.getString("adresse"), rs.getString("telephone"),
                                Abonnement.valueOf(rs.getString("abonnement").toUpperCase())),
                        new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),
                                rs.getString("isbn")),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour") == null ? null : rs.getDate("dateRetour").toLocalDate()));
            }

            conn.close();
            return output;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - EmpruntDaoImpl.getListCurrentByMembre");
        }
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(listCurrentByLivreQuery);

            List<Emprunt> output = new ArrayList<Emprunt>();

            while (rs.next()) {
                output.add(new Emprunt(rs.getInt("id"),
                        new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
                                rs.getString("email"), rs.getString("adresse"), rs.getString("telephone"),
                                Abonnement.valueOf(rs.getString("abonnement").toUpperCase())),
                        new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),
                                rs.getString("isbn")),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour") == null ? null : rs.getDate("dateRetour").toLocalDate()));
            }

            conn.close();
            return output;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - EmpruntDaoImpl.getListCurrentByLivre");
        }
    }

    @Override
    public Emprunt getById(int id) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstmnt = conn.prepareStatement(idQuery);
            pstmnt.setInt(1, id);

            ResultSet rs = pstmnt.executeQuery();

            if (rs.next()) {

                Emprunt output = new Emprunt(rs.getInt("id"),
                        new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
                                rs.getString("email"), rs.getString("adresse"), rs.getString("telephone"),
                                Abonnement.valueOf(rs.getString("abonnement").toUpperCase())),
                        new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"),
                                rs.getString("isbn")),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour") == null ? null : rs.getDate("dateRetour").toLocalDate());

                conn.close();
                return output;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - EmpruntDaoImpl.getById");
        }
        return null;
    }

    @Override
    public int create(Emprunt emprunt) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstmnt = conn.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);

            pstmnt.setInt(1, emprunt.getMembre().getId());
            pstmnt.setInt(2, emprunt.getLivre().getId());
            pstmnt.setString(3, emprunt.getDateEmprunt().toString());

            pstmnt.executeUpdate();
            ResultSet rs = pstmnt.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("Emprunt créé avec succès");
                conn.close();
                return id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - EmpruntDaoImpl.create");
        }
        return 0;

    }

    @Override
    public void update(Emprunt emprunt) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstmnt = conn.prepareStatement(updateQuery);

            pstmnt.setInt(1, emprunt.getMembre().getId());
            pstmnt.setInt(2, emprunt.getLivre().getId());
            pstmnt.setString(3, emprunt.getDateEmprunt().toString());
            pstmnt.setString(4, emprunt.getDateRetour().toString());
            pstmnt.setInt(5, emprunt.getId());

            pstmnt.executeUpdate();
            System.out.println("Emprunt mis à jour avec succès");
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - EmpruntDaoImpl.update");
        }

    }

    @Override
    public int count() throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(countQuery);

            if (rs.next()) {
                conn.close();
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - EmpruntDaoImpl.count");
        }
        return 0;
    }

}
