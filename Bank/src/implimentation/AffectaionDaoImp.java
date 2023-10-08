package implimentation;

import dao.AffectationDao;
import dto.Affectation;
import dto.Employe;
import dto.Mission;
import helper.Connectionbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AffectaionDaoImp implements AffectationDao {
    @Override
    public Optional<Affectation> ajouterAffectation(Affectation affectation) {
        Connection con = Connectionbd.getConn();

        try {
            String sql = "INSERT INTO affectation (datedebut, datefin, mission, employe) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(affectation.getDateDebut()));
            stmt.setDate(2, java.sql.Date.valueOf(affectation.getDateFin()));
            stmt.setInt(3, affectation.getMission().getCode());
            stmt.setInt(4, affectation.getEmploye().getMatricule());


            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.of(affectation);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public int supprimerAffectation(int mission, int employe) {
        Connection con = Connectionbd.getConn();
        try {
            String deleteQuery = "DELETE FROM affectation WHERE mission = ? and employe = ?";
            PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, mission);
            deleteStmt.setInt(2, employe);


            int rowsAffected = deleteStmt.executeUpdate();

            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Optional<List<Affectation>> affectationEmplye(int matricule) {
        List<Affectation> affectations = new ArrayList<>();
        Connection con = Connectionbd.getConn();

        try {
            String sql = "SELECT * from affectation where employe = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, matricule);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                LocalDate dateDebut = resultSet.getDate("datedebut").toLocalDate();
                LocalDate dateFin = resultSet.getDate("datefin").toLocalDate();
                int numMission = resultSet.getInt("mission");
                EmployeDaoImpl emp = new EmployeDaoImpl();
                Optional<Employe> employe = emp.chercherEmploye(matricule);

                MissionDaoImp miss = new MissionDaoImp();
                Optional<Mission> mission = miss.chercherMissionParCode(numMission);

                Affectation affectation = new Affectation(dateDebut, dateFin, mission.get(), employe.get());

                affectations.add(affectation);
            }

            return Optional.of(affectations);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }



    public int[] statistiquesAffectations() {
        Connection con = Connectionbd.getConn();
        int[] statistiques = new int[3];

        try {
            String nombreAffectationsSql = "SELECT COUNT(*) AS totalAffectations FROM affectation";
            PreparedStatement nombreAffectationsStmt = con.prepareStatement(nombreAffectationsSql);
            ResultSet nombreAffectationsResultSet = nombreAffectationsStmt.executeQuery();
            nombreAffectationsResultSet.next();
            statistiques[0] = nombreAffectationsResultSet.getInt("totalAffectations");

            String nombreEmployesSql = "SELECT COUNT(DISTINCT employe) AS totalEmployes FROM affectation";
            PreparedStatement nombreEmployesStmt = con.prepareStatement(nombreEmployesSql);
            ResultSet nombreEmployesResultSet = nombreEmployesStmt.executeQuery();
            nombreEmployesResultSet.next();
            statistiques[1] = nombreEmployesResultSet.getInt("totalEmployes");

            String nombreMissionsSql = "SELECT COUNT(DISTINCT mission) AS totalMissions FROM affectation";
            PreparedStatement nombreMissionsStmt = con.prepareStatement(nombreMissionsSql);
            ResultSet nombreMissionsResultSet = nombreMissionsStmt.executeQuery();
            nombreMissionsResultSet.next();
            statistiques[2] = nombreMissionsResultSet.getInt("totalMissions");

            return statistiques;

        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Gestion d'erreur : renvoyer null en cas d'erreur
        }
    }






}
