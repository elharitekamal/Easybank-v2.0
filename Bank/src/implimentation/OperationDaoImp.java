package implimentation;

import dao.Operationdao;
import dto.Compte;
import dto.Employe;
import dto.Operation;
import helper.Connectionbd;
import java.sql.*;
import java.util.Optional;

public class OperationDaoImp implements Operationdao {
    public Optional<Operation> effectuerRetrait(Compte numero, double montant, Employe employe) {
        Connection con = Connectionbd.getConn();

        try {
            String soldeQuery = "SELECT solde FROM compte WHERE numero = ?";
            PreparedStatement soldeStmt = con.prepareStatement(soldeQuery);
            soldeStmt.setInt(1, numero.getNumero());

            try (ResultSet soldeResultSet = soldeStmt.executeQuery()) {
                if (!soldeResultSet.next()) {
                    System.out.print("Le compte n'existe pas ");
                    return Optional.empty();
                }
                double solde = soldeResultSet.getDouble("solde");
                if (solde < montant) {
                    System.out.print("Le solde est insuffisant ");
                    return Optional.empty();
                }
            }

            String retraitQuery = "UPDATE compte SET solde = solde - ? WHERE numero = ?";
            try (PreparedStatement retraitStmt = con.prepareStatement(retraitQuery)) {
                retraitStmt.setDouble(1, montant);
                retraitStmt.setInt(2, numero.getNumero());
                retraitStmt.executeUpdate();
            }

            String operationQuery = "INSERT INTO operation (datecreation, type, montant, employe, compte1) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement operationStmt = con.prepareStatement(operationQuery, Statement.RETURN_GENERATED_KEYS)) {

                operationStmt.setDate(1, new Date(System.currentTimeMillis())); // Utilisez java.sql.Date.valueOf pour insérer une LocalDate
                operationStmt.setObject(2, Operation.TYPE.retrait.name(), Types.OTHER);
                operationStmt.setDouble(3, montant);
                operationStmt.setInt(4, employe.getMatricule());
                operationStmt.setInt(5, numero.getNumero());

                int rowsAffected = operationStmt.executeUpdate();
                if (rowsAffected == 1) {
                    ResultSet generatedKeys = operationStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int operationId = generatedKeys.getInt(1);
                        Operation operation = new Operation(new Date(System.currentTimeMillis()), montant, Operation.TYPE.retrait, employe, numero, null);
                        return Optional.of(operation);
                    }
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    @Override
    public Optional<Operation> effectuerVirement(Compte compteSource, Compte compteDestinataire, double montant, Employe employe) {
        Connection con = Connectionbd.getConn();

        try {
            String soldeQuery = "SELECT solde FROM compte WHERE numero = ?";
            PreparedStatement soldeStmt = con.prepareStatement(soldeQuery);
            soldeStmt.setInt(1, compteSource.getNumero());
            ResultSet soldeResultSet = soldeStmt.executeQuery();

            double soldeSource = soldeResultSet.getDouble("solde");

            if (soldeSource < montant) {
                System.out.print("Le solde source est insuffisant");
                return Optional.empty();
            }

            String miseAJourSourceQuery = "UPDATE compte SET solde = solde - ? WHERE numero = ?";
            PreparedStatement miseAJourSourceStmt = con.prepareStatement(miseAJourSourceQuery);
            miseAJourSourceStmt.setDouble(1, montant);
            miseAJourSourceStmt.setInt(2, compteSource.getNumero());
            miseAJourSourceStmt.executeUpdate();

            String miseAJourDestinataireQuery = "UPDATE compte SET solde = solde + ? WHERE numero = ?";
            PreparedStatement miseAJourDestinataireStmt = con.prepareStatement(miseAJourDestinataireQuery);
            miseAJourDestinataireStmt.setDouble(1, montant);
            miseAJourDestinataireStmt.setInt(2, compteDestinataire.getNumero());
            miseAJourDestinataireStmt.executeUpdate();

            String transactionQuery = "INSERT INTO operation (datecreation, type, montant, employe, compte1, compte2) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement transactionStmt = con.prepareStatement(transactionQuery);
            transactionStmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            transactionStmt.setObject(2, Operation.TYPE.virement.name(), Types.OTHER);
            transactionStmt.setDouble(3, montant);
            transactionStmt.setInt(4, employe.getMatricule());
            transactionStmt.setInt(5, compteSource.getNumero());
            transactionStmt.setInt(6, compteDestinataire.getNumero());

            transactionStmt.executeUpdate();

            Operation operation = new Operation(new Date(System.currentTimeMillis()), montant, Operation.TYPE.virement, employe, compteSource, compteDestinataire);
            return Optional.of(operation);

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Operation> effectuerVersement(Compte numero, double montant, Employe employe) {
        Connection con = Connectionbd.getConn();

        try {

            String retraitQuery = "UPDATE compte SET solde = solde + ? WHERE numero = ?";
            try (PreparedStatement retraitStmt = con.prepareStatement(retraitQuery)) {
                retraitStmt.setDouble(1, montant);
                retraitStmt.setInt(2, numero.getNumero());
                retraitStmt.executeUpdate();
            }

            String operationQuery = "INSERT INTO operation (datecreation, type, montant, employe, compte1) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement operationStmt = con.prepareStatement(operationQuery, Statement.RETURN_GENERATED_KEYS)) {

                operationStmt.setDate(1, new Date(System.currentTimeMillis()));
                operationStmt.setObject(2, Operation.TYPE.versement.name(), Types.OTHER);
                operationStmt.setDouble(3, montant);
                operationStmt.setInt(4, employe.getMatricule());
                operationStmt.setInt(5, numero.getNumero());

                int rowsAffected = operationStmt.executeUpdate();
                if (rowsAffected == 1) {
                    ResultSet generatedKeys = operationStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int operationId = generatedKeys.getInt(1);
                        Operation operation = new Operation(new Date(System.currentTimeMillis()), montant, Operation.TYPE.retrait, employe, numero, null);
                        return Optional.of(operation);
                    }
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }    }

    @Override
    public int supprimerOperation(int numero) {
        Connection con = Connectionbd.getConn();

        try {
            String deleteQuery = "DELETE FROM operation WHERE numero = ?";
            PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, numero);

            int rowsAffected = deleteStmt.executeUpdate();

            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Optional<Operation> chercherOperationParNumero(int numero) {
        Connection con = Connectionbd.getConn();

        try {
            String selectQuery = "SELECT * FROM operation WHERE numero = ?";
            PreparedStatement selectStmt = con.prepareStatement(selectQuery);
            selectStmt.setInt(1, numero);

            ResultSet resultSet = selectStmt.executeQuery();

            if (resultSet.next()) {
                int operationId = resultSet.getInt("numero");
                Date dateCreation = resultSet.getDate("datecreation");
                String type = resultSet.getString("type");
                double montant = resultSet.getDouble("montant");
                int employeId = resultSet.getInt("employe");
                EmployeDaoImpl emp = new EmployeDaoImpl();
                Optional<Employe> employeOptional = emp.chercherEmploye(employeId);

                Operation operation = new Operation(operationId, dateCreation, montant, Operation.TYPE.valueOf(type), employeOptional.get());
                return Optional.of(operation);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
