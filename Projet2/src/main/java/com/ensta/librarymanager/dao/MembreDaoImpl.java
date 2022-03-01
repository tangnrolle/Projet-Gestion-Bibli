package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;
import com.ensta.librarymanager.modele.Abonnement;

public class MembreDaoImpl implements MembreDao {
    private static MembreDaoImpl instance;
    private Connection connection;
    private String idQuery = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?";
    private String listQuery = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;";
    private String createQuery = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?)";
    private String updateQuery = "UPDATE membre SET nom=?,prenom=?,adresse=?,email=?,telephone=?,abonnement=? WHERE id=?;";
    private String deleteQuery = "DELETE FROM membre WHERE id = ?;";
    private String countQuery = "SELECT COUNT(id) AS count FROM membre";

    private MembreDaoImpl() throws SQLException {
        setConnection(ConnectionManager.getConnection());
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static MembreDaoImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new MembreDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Membre> getList() throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(listQuery);

            List<Membre> output = new ArrayList<Membre>();

            while (rs.next()) {
                output.add(new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"),
                        Abonnement.valueOf(rs.getString("abonnement").toUpperCase())));
            }

            return output;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - MembreDaoImpl.getList");
        }
    }

    @Override
    public Membre getById(int id) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstmnt = conn.prepareStatement(idQuery);
            pstmnt.setInt(1, id);

            ResultSet rs = pstmnt.executeQuery();

            if (rs.next()) {

                Membre output = new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"),
                        Abonnement.valueOf(rs.getString("abonnement").toUpperCase()));

                return output;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - MembreDaoImpl.getById");
        }

        return null;
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement)
            throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstmnt = conn.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);

            pstmnt.setString(1, nom);
            pstmnt.setString(2, prenom);
            pstmnt.setString(3, adresse);
            pstmnt.setString(4, email);
            pstmnt.setString(5, telephone);
            pstmnt.setString(6, abonnement.toString());

            pstmnt.executeQuery();
            ResultSet rs = pstmnt.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("Membre créé avec succès");
                return id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - MembreDaoImpl.create");
        }
        return 0;
    }

    @Override
    public void update(Membre membre) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstmnt = conn.prepareStatement(updateQuery);

            pstmnt.setString(1, membre.getNom());
            pstmnt.setString(2, membre.getPrenom());
            pstmnt.setString(3, membre.getAdresse());
            pstmnt.setString(4, membre.getEmail());
            pstmnt.setString(5, membre.getTelephone());
            pstmnt.setString(6, membre.getAbonnement().toString());
            pstmnt.setInt(7, membre.getId());

            pstmnt.executeUpdate();
            System.out.println("Membre mis à jour avec succès");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - MembreDaoImpl.update");
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstmnt = conn.prepareStatement(deleteQuery);
            pstmnt.setInt(1, id);

            pstmnt.executeUpdate();
            System.out.println("Membre supprimé avec succès");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - MembreDaoImpl.delete");
        }
    }

    @Override
    public int count() throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(countQuery);

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erreur au niveau de la DAO - MembreDaoImpl.count");
        }
        return 0;
    }

}
