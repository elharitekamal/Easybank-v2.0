package implimentation;
import dao.CompteCourantdao;
import dto.Client;
import dto.Compte;
import dto.CompteCourant;
import dto.Employe;
import helper.Connectionbd;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class CompteCourantImp implements CompteCourantdao {
    @Override
    public Optional<CompteCourant> ajouterCompteC(CompteCourant compteCourant) {
        Connection con = Connectionbd.getConn();
        try {
            String compteInsertSQL = "INSERT INTO compte (numero, solde, dateCreation, etat, client, employe) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(compteInsertSQL);

            stmt.setInt(1, compteCourant.getNumero());
            stmt.setDouble(2, compteCourant.getSolde());
            stmt.setDate(3, java.sql.Date.valueOf(compteCourant.getDateCreation()));
            stmt.setObject(4, compteCourant.getEtat().name(), Types.OTHER);
            stmt.setInt(5, compteCourant.getClient().getCode());
            stmt.setInt(6, compteCourant.getEmploye().getMatricule());

            stmt.executeUpdate();

            String sql = "INSERT INTO compteCourant (num_compte, decouvert) " +
                    "VALUES (?, ?)";
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, compteCourant.getNumero());
            stmt.setDouble(2, compteCourant.getDecouvert());

            stmt.executeUpdate();

            return Optional.of(compteCourant);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }


    }




    @Override
    public CompteCourant chercherCompteC(int numeroCompte) {
        Connection con = Connectionbd.getConn();
        try {
            String sql = "SELECT cc.num_compte, cc.decouvert, c.numero, c.solde, c.dateCreation, c.etat, c.client, c.employe " +
                    "FROM compte c " +
                    "INNER JOIN compteCourant cc ON c.numero = cc.num_compte " +
                    "WHERE cc.num_compte = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, numeroCompte);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int numCompteCourant = resultSet.getInt("num_compte");
                double decouvert = resultSet.getDouble("decouvert");
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

                CompteCourant compteCourant = new CompteCourant(numero, solde, dateCreation, etat, client.get(), employe.get(), numCompteCourant, decouvert);
                return compteCourant;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<CompteCourant> modifierCompteCourant(CompteCourant compteCourantModifie) {
        Connection con = Connectionbd.getConn();
        try {
            String compteUpdateSQL = "UPDATE compte SET solde = ? WHERE numero = ?";
            PreparedStatement compteStmt = con.prepareStatement(compteUpdateSQL);
            compteStmt.setDouble(1, compteCourantModifie.getSolde());
            compteStmt.setInt(2, compteCourantModifie.getNum_compte());
            int rest = compteStmt.executeUpdate();

            String compteCourantUpdateSQL = "UPDATE compteCourant SET decouvert = ? WHERE num_compte = ?";
            PreparedStatement compteCourantStmt = con.prepareStatement(compteCourantUpdateSQL);
            compteCourantStmt.setDouble(1, compteCourantModifie.getDecouvert());
            compteCourantStmt.setInt(2, compteCourantModifie.getNum_compte());
            int res = compteCourantStmt.executeUpdate();

            if (rest != 0 && res != 0) {
                return Optional.of(compteCourantModifie);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


}
