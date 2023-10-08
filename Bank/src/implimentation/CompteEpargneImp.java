package implimentation;

import dao.CompteDepargnedao;
import dto.Client;
import dto.Compte;
import dto.CompteEpargne;
import dto.Employe;
import helper.Connectionbd;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class CompteEpargneImp implements CompteDepargnedao {
    @Override
    public Optional<CompteEpargne> ajouterCompteE(CompteEpargne compteEparn) {
        Connection con = Connectionbd.getConn();
        try {
            String compteInsertSQL = "INSERT INTO compte (numero, solde, dateCreation, etat, client, employe) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(compteInsertSQL);

            stmt.setInt(1, compteEparn.getNumero());
            stmt.setDouble(2, compteEparn.getSolde());
            stmt.setDate(3, java.sql.Date.valueOf(compteEparn.getDateCreation()));
            stmt.setObject(4, compteEparn.getEtat().name(), Types.OTHER);
            stmt.setInt(5, compteEparn.getClient().getCode());
            stmt.setInt(6, compteEparn.getEmploye().getMatricule());

            stmt.executeUpdate();

            String compteEparneInsertSQL = "INSERT INTO compteEpargne (num_compte, tauxInteret) " +
                    "VALUES (?, ?)";
            stmt = con.prepareStatement(compteEparneInsertSQL);

            stmt.setInt(1, compteEparn.getNumero());
            stmt.setDouble(2, compteEparn.getTauxInteret());

            stmt.executeUpdate();

            return Optional.of(compteEparn);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }



    @Override
    public CompteEpargne chercherCompteEpargneParNumero(int numeroCompte) {
        Connection con = Connectionbd.getConn();
        try {
            String sql = "SELECT ce.num_compte, ce.tauxInteret, c.numero, c.solde, c.dateCreation, c.etat, c.client, c.employe " +
                    "FROM compte c " +
                    "INNER JOIN compteEpargne ce ON c.numero = ce.num_compte " +
                    "WHERE ce.num_compte = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, numeroCompte);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int numCompte = resultSet.getInt("num_compte");
                double tauxInteret = resultSet.getDouble("tauxInteret");
                int numero = resultSet.getInt("numero");
                double solde = resultSet.getDouble("solde");
                LocalDate dateCreation = resultSet.getDate("dateCreation").toLocalDate();
                String etatString = resultSet.getString("etat");
                Compte.Etat etat = Compte.Etat.valueOf(etatString);
                int clienti = resultSet.getInt("client");
                ClientDaoImpl cl = new ClientDaoImpl();
                Optional<Client> client = cl.chercherClient(clienti);

                int employei = resultSet.getInt("employe");
                EmployeDaoImpl em = new EmployeDaoImpl();
                Optional<Employe> employe = em.chercherEmploye(employei);


                CompteEpargne compteEpargne = new CompteEpargne(numero, solde, dateCreation, etat, client.get(), employe.get(), numCompte, tauxInteret);
                return compteEpargne;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public Optional<CompteEpargne> modifierCompteEpargne(CompteEpargne compteEpargneModifie) {
        Connection con = Connectionbd.getConn();
        try {
            String compteUpdateSQL = "UPDATE compte SET solde = ? WHERE numero = ?";
            PreparedStatement compteStmt = con.prepareStatement(compteUpdateSQL);
            compteStmt.setDouble(1, compteEpargneModifie.getSolde());
            compteStmt.setInt(2, compteEpargneModifie.getNum_compte());
            int rest = compteStmt.executeUpdate();

            String compteEpargneUpdateSQL = "UPDATE compteEpargne SET tauxInteret = ? WHERE num_compte = ?";
            PreparedStatement compteEpargneStmt = con.prepareStatement(compteEpargneUpdateSQL);
            compteEpargneStmt.setDouble(1, compteEpargneModifie.getTauxInteret());
            compteEpargneStmt.setInt(2, compteEpargneModifie.getNum_compte());
            int res = compteEpargneStmt.executeUpdate();

            if (rest != 0 && res != 0) {
                return Optional.of(compteEpargneModifie);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
