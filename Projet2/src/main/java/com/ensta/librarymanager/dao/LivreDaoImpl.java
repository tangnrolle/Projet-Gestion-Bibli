package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreDaoImpl implements LivreDao {
    private static LivreDaoImpl instance;
    private Connection connection;
    private String idQuery = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;";
    private String listQuery = "SELECT id, titre, auteur, isbn FROM livre;";
    private String createQuery = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);";
    private String updateQuery = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;";
    private String deleteQuery = "DELETE FROM livre WHERE id = ?;";
    private String countQuery = "SELECT COUNT(id) AS count FROM livre;";

    private LivreDaoImpl() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static LivreDaoImpl getInstance() {
        if (instance == null) {
            instance = new LivreDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Livre> getList() throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(listQuery);

            List<Livre> output = new ArrayList<Livre>();

            while (rs.next()) {
                output.add(new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"),
                        rs.getString("isbn")));
            }

            conn.close();
            return output;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - LivreDaoImpl.getList");
        }
    }

    @Override
    public Livre getById(int id) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstmnt = conn.prepareStatement(idQuery);
            pstmnt.setInt(1, id);

            ResultSet rs = pstmnt.executeQuery();

            if (rs.next()) {

                Livre output = new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"),
                        rs.getString("isbn"));

                conn.close();
                return output;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - LivreDaoImpl.getById");
        }

        return null;
    }

    @Override
    public int create(Livre livre) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstmnt = conn.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);

            pstmnt.setString(1, livre.getTitre());
            pstmnt.setString(2, livre.getAuteur());
            pstmnt.setString(3, livre.getIsbn());

            pstmnt.executeUpdate();
            ResultSet rs = pstmnt.getGeneratedKeys();

            if (rs.next()) {
                int idLivre = rs.getInt(1);
                System.out.println("Livre créé avec succès");
                conn.close();
                return idLivre;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - LivreDaoImpl.create");
        }
        return 0;
    }

    @Override
    public void update(Livre livre) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstmnt = conn.prepareStatement(updateQuery);

            pstmnt.setString(1, livre.getTitre());
            pstmnt.setString(2, livre.getAuteur());
            pstmnt.setString(3, livre.getIsbn());
            pstmnt.setInt(4, livre.getId());

            pstmnt.executeUpdate();
            System.out.println("Livre mis à jour avec succès");
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - LivreDaoImpl.update");
        }

    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstmnt = conn.prepareStatement(deleteQuery);
            pstmnt.setInt(1, id);

            pstmnt.executeUpdate();
            System.out.println("Livre supprimé avec succès");
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - LivreDaoImpl.delete");
        }

    }

    @Override
    public int count() throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(countQuery);

            if (rs.next()) {
                int count = rs.getInt("count");
                conn.close();
                return count;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - LivreDaoImpl.count");
        }
        return 0;
    }

}
