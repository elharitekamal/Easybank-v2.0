package implimentation;

import dao.Employedao;
import dto.Employe;
import helper.Connectionbd;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeDaoImpl implements Employedao {


    @Override
    public Optional<Employe> ajouterEmploye(Employe employe) {
        Connection con = Connectionbd.getConn();

        try {
            String sql = "INSERT INTO employe (matricule, nom, prenom, dateNaissance, telephone, email, adress, dateRecrutement) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, employe.getMatricule());
            stmt.setString(2, employe.getNom());
            stmt.setString(3, employe.getPrenom());
            stmt.setDate(4, java.sql.Date.valueOf(employe.getDateNaissance()));
            stmt.setString(5, employe.getTelephone());
            stmt.setString(6, employe.getEmail());
            stmt.setString(7, employe.getAdress());
            stmt.setDate(8, java.sql.Date.valueOf(employe.getDateRecrutement()));

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                return Optional.of(employe);
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.of(employe);
        }
    }

    @Override
    public int supprimerEmploye(int matricule) {
        Connection con = Connectionbd.getConn();

        try {
            String sql = "DELETE FROM employe WHERE matricule = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, matricule);

            int rowsDeleted = stmt.executeUpdate();

            return rowsDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Optional<Employe> chercherEmploye(int matricule) {
        Connection con = Connectionbd.getConn();

        try {
            String sql = "SELECT * FROM employe WHERE matricule = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, matricule);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int matriculeRetrieved = resultSet.getInt("matricule");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                LocalDate dateNaissance = resultSet.getDate("dateNaissance").toLocalDate();
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                String adress = resultSet.getString("adress");
                LocalDate dateRecrutement = resultSet.getDate("dateRecrutement").toLocalDate();

                Employe employe = new Employe(nom,  prenom,  dateNaissance,  telephone,  adress,  matriculeRetrieved,  dateRecrutement,      email);
                return Optional.of(employe);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Employe>> listEmploye() {
        Connection con = Connectionbd.getConn();
        try {
            String sql = "SELECT * FROM employe";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            List<Employe> employes = new ArrayList<>();

            while (resultSet.next()) {
                int matricule = resultSet.getInt("matricule");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                LocalDate dateNaissance = resultSet.getDate("dateNaissance").toLocalDate();
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                String adresse = resultSet.getString("adress");
                LocalDate dateRecrutement = resultSet.getDate("dateRecrutement").toLocalDate();

                Employe employe = new Employe(nom, prenom, dateNaissance, telephone, adresse, matricule, dateRecrutement, email);
                employes.add(employe);
            }

            return Optional.of(employes);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employe> chercherEmployeTel(String telephone) {
        Connection con = Connectionbd.getConn();

        try {
            String sql = "SELECT * FROM employe WHERE telephone = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, telephone);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int matriculeRetrieved = resultSet.getInt("matricule");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                LocalDate dateNaissance = resultSet.getDate("dateNaissance").toLocalDate();
                String telephonne = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                String adress = resultSet.getString("adress");
                LocalDate dateRecrutement = resultSet.getDate("dateRecrutement").toLocalDate();

                Employe employe = new Employe(nom,  prenom,  dateNaissance,  telephonne,  adress,  matriculeRetrieved,  dateRecrutement,      email);
                return Optional.of(employe);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employe> modifierEmploye(Employe employeModifie) {
        Connection con = Connectionbd.getConn();

        try {
            String sql = "UPDATE employe SET nom = ?, prenom = ?, dateNaissance = ?, telephone = ?, email = ?, adress = ?, dateRecrutement = ? WHERE matricule = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, employeModifie.getNom());
            stmt.setString(2, employeModifie.getPrenom());
            stmt.setDate(3, java.sql.Date.valueOf(employeModifie.getDateNaissance()));
            stmt.setString(4, employeModifie.getTelephone());
            stmt.setString(5, employeModifie.getEmail());
            stmt.setString(6, employeModifie.getAdress());
            stmt.setDate(7, java.sql.Date.valueOf(employeModifie.getDateRecrutement()));
            stmt.setInt(8, employeModifie.getMatricule());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                return Optional.of(employeModifie);
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.of(employeModifie);
        }    }


}
