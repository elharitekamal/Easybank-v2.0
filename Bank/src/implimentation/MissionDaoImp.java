package implimentation;

import dao.Missiondao;
import dto.Employe;
import dto.Mission;
import helper.Connectionbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MissionDaoImp implements Missiondao {
    @Override
    public Optional<Mission> ajouterMission(Mission mission) {
        Connection con = Connectionbd.getConn();
        try {
            String sql = "INSERT INTO mission (code, nom, description, employe_id) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, mission.getCode());
            stmt.setString(2, mission.getNom());
            stmt.setString(3, mission.getDescription());
            stmt.setInt(4, mission.getEmploye().getMatricule());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                return Optional.of(mission);
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.of(mission);
        }
    }

    @Override
    public int supprimerMission(int code) {
        Connection con = Connectionbd.getConn();

        try {
            String sql = "DELETE FROM mission WHERE code = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, code);

            int rowsDeleted = stmt.executeUpdate();

            return rowsDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Optional<List<Mission>> listerMission() {
        List<Mission> missions = new ArrayList<>();
        Connection con = Connectionbd.getConn();

        try {
            String sql = "SELECT * FROM mission";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int code = resultSet.getInt("code");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                int employeId = resultSet.getInt("employe_id");
                EmployeDaoImpl emp = new EmployeDaoImpl();
                Optional<Employe> employeOptional = emp.chercherEmploye(employeId);

                Mission mission = new Mission(code, nom, description, employeOptional.get());
                missions.add(mission);
            }

            return Optional.of(missions);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Mission> chercherMissionParCode(int code) {
        Connection con = Connectionbd.getConn();
        try {
            String sql = "SELECT * FROM mission WHERE code = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, code);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int missionCode = resultSet.getInt("code");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                int employeId = resultSet.getInt("employe_id");

                EmployeDaoImpl emp = new EmployeDaoImpl();
                Optional<Employe> employeOptional = emp.chercherEmploye(employeId);

                Mission mission = new Mission(missionCode, nom, description, employeOptional.get());
                return Optional.of(mission);
            } else {
                return Optional.empty(); // Aucune mission trouvée avec le code spécifié
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty(); // Gestion de l'erreur
        }
    }

}
