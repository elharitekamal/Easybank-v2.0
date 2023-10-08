package implimentation;

import dao.Comptedao;
import dto.*;
import helper.Connectionbd;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompteImp implements Comptedao {

    @Override
    public Optional<List<Compte>> chercherCompte(int clientId) {
        List<Compte> comptes = new ArrayList<>();

        Connection con = Connectionbd.getConn();
        try {
            String sql = "SELECT c.numero, c.solde, c.dateCreation, c.client, c.employe, c.etat,\n" +
                    "       cc.decouvert AS decouvert,\n" +
                    "       ce.tauxInteret AS tauxInteret\n" +
                    "FROM compte c\n" +
                    "LEFT JOIN compteCourant cc ON c.numero = cc.num_compte\n" +
                    "LEFT JOIN compteEpargne ce ON c.numero = ce.num_compte\n" +
                    "WHERE c.client = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, clientId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int numero = resultSet.getInt("numero");

                int clienti = resultSet.getInt("client");
                ClientDaoImpl cl = new ClientDaoImpl();
                Optional<Client> client = cl.chercherClient(clienti);

                int employei = resultSet.getInt("employe");
                EmployeDaoImpl em = new EmployeDaoImpl();
                Optional<Employe> employe = em.chercherEmploye(employei);
                double solde = resultSet.getDouble("solde");
                LocalDate dateCreation = resultSet.getDate("dateCreation").toLocalDate();
                String etatString = resultSet.getString("etat");
                Compte.Etat etat = Compte.Etat.valueOf(etatString);
                double decouvert = resultSet.getDouble("decouvert");
                double tauxInteret = resultSet.getDouble("tauxInteret");

                Compte compte = new Compte(numero, solde, dateCreation, etat, client.get(), employe.get());
                if (decouvert != 0.0) {
                    CompteCourant compteCourant = new CompteCourant(numero, solde, dateCreation, etat, client.get(), employe.get(), numero, decouvert);
                    comptes.add(compteCourant);
                } else if (tauxInteret != 0.0) {
                    CompteEpargne compteEpargne = new CompteEpargne(numero, solde, dateCreation, etat, client.get(), employe.get(), numero, tauxInteret);
                    comptes.add(compteEpargne);
                } else {
                    comptes.add(compte);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return comptes.isEmpty() ? Optional.empty() : Optional.of(comptes);
    }

    @Override
    public boolean modifierEtat(int numero, Compte.Etat nouvelEtat) {
        Connection con = Connectionbd.getConn();
        try {
            String sql = "UPDATE compte SET etat = ? WHERE numero = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setObject(1, nouvelEtat.name(), Types.OTHER);
            stmt.setInt(2, numero);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Optional<List<Compte>> listComptes() {
        List<Compte> comptes = new ArrayList<>();

        Connection con = Connectionbd.getConn();
        try {
            String sql = "SELECT c.numero, c.solde, c.dateCreation, c.client, c.employe, c.etat,\n" +
                    "       cc.decouvert AS decouvert,\n" +
                    "       ce.tauxInteret AS tauxInteret\n" +
                    "FROM compte c\n" +
                    "LEFT JOIN compteCourant cc ON c.numero = cc.num_compte\n" +
                    "LEFT JOIN compteEpargne ce ON c.numero = ce.num_compte";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int numero = resultSet.getInt("numero");

                int clientId = resultSet.getInt("client");
                ClientDaoImpl cl = new ClientDaoImpl();
                Optional<Client> client = cl.chercherClient(clientId);

                int employeId = resultSet.getInt("employe");
                EmployeDaoImpl em = new EmployeDaoImpl();
                Optional<Employe> employe = em.chercherEmploye(employeId);
                double solde = resultSet.getDouble("solde");
                LocalDate dateCreation = resultSet.getDate("dateCreation").toLocalDate();
                String etatString = resultSet.getString("etat");
                Compte.Etat etat = Compte.Etat.valueOf(etatString);
                double decouvert = resultSet.getDouble("decouvert");
                double tauxInteret = resultSet.getDouble("tauxInteret");

                Compte compte = new Compte(numero, solde, dateCreation, etat, client.get(), employe.get());
                if (decouvert != 0.0) {
                    CompteCourant compteCourant = new CompteCourant(numero, solde, dateCreation, etat, client.get(), employe.get(), numero, decouvert);
                    comptes.add(compteCourant);
                } else if (tauxInteret != 0.0) {
                    CompteEpargne compteEpargne = new CompteEpargne(numero, solde, dateCreation, etat, client.get(), employe.get(), numero, tauxInteret);
                    comptes.add(compteEpargne);
                } else {
                    comptes.add(compte);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return comptes.isEmpty() ? Optional.empty() : Optional.of(comptes);
    }

    public Optional<Compte> chercherCompteparNum(int numero) {
        Connection con = Connectionbd.getConn();

        try {
            String sql = "SELECT c.numero, c.solde, c.dateCreation, c.client, c.employe, c.etat,\n" +
                    "       cc.decouvert AS decouvert,\n" +
                    "       ce.tauxInteret AS tauxInteret\n" +
                    "FROM compte c\n" +
                    "LEFT JOIN compteCourant cc ON c.numero = cc.num_compte\n" +
                    "LEFT JOIN compteEpargne ce ON c.numero = ce.num_compte\n" +
                    "WHERE c.numero = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, numero);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int numerro = resultSet.getInt("numero");

                int clienti = resultSet.getInt("client");
                ClientDaoImpl cl = new ClientDaoImpl();
                Optional<Client> client = cl.chercherClient(clienti);

                int employei = resultSet.getInt("employe");
                EmployeDaoImpl em = new EmployeDaoImpl();
                Optional<Employe> employe = em.chercherEmploye(employei);
                double solde = resultSet.getDouble("solde");
                LocalDate dateCreation = resultSet.getDate("dateCreation").toLocalDate();
                String etatString = resultSet.getString("etat");
                Compte.Etat etat = Compte.Etat.valueOf(etatString);
                double decouvert = resultSet.getDouble("decouvert");
                double tauxInteret = resultSet.getDouble("tauxInteret");

                Compte compte = new Compte(numerro, solde, dateCreation, etat, client.get(), employe.get());
                if (decouvert != 0.0) {
                    CompteCourant compteCourant = new CompteCourant(numerro, solde, dateCreation, etat, client.get(), employe.get(), numerro, decouvert);
                    return Optional.of(compteCourant);
                } else if (tauxInteret != 0.0) {
                    CompteEpargne compteEpargne = new CompteEpargne(numerro, solde, dateCreation, etat, client.get(), employe.get(), numerro, tauxInteret);
                    return Optional.of(compteEpargne);
                } else {
                    return Optional.of(compte);
                }
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    @Override
    public int supprimerCompte(int numero) {
        Connection con = Connectionbd.getConn();

        try {
            String sql = "DELETE FROM compte WHERE numero = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, numero);

            int rowsDeleted = stmt.executeUpdate();

            return rowsDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int[] afficherCompteparOp(int numeroOp) {
        Connection con = Connectionbd.getConn();

        try {

            String operationSql = "SELECT compte1, compte2 FROM operation WHERE numero = ?";
            PreparedStatement operationStmt = con.prepareStatement(operationSql);
            operationStmt.setInt(1, numeroOp);
            ResultSet operationResultSet = operationStmt.executeQuery();

            if (operationResultSet.next()) {
                int compte1Numero = operationResultSet.getInt("compte1");
                int compte2Numero = operationResultSet.getInt("compte2");


                int[] comptes = {compte1Numero, compte2Numero};

                return comptes;
            } else {
                int[] comptesVides = {0, 0};
                return comptesVides;            }

        } catch (SQLException e) {
            e.printStackTrace();
            int[] comptesVides = {0, 0};
            return comptesVides;        }
    }


}
