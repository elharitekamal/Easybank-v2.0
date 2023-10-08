package dao;

import dto.Compte;
import dto.Mission;

import java.util.List;
import java.util.Optional;

public interface Missiondao {
     Optional<Mission> ajouterMission(Mission mission);
     int supprimerMission(int code);

     Optional<List<Mission>> listerMission();

     Optional<Mission> chercherMissionParCode(int code);

}
