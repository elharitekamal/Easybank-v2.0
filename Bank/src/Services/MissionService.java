package Services;

import dto.Client;
import dto.Compte;
import dto.Employe;
import dto.Mission;
import implimentation.ClientDaoImpl;
import implimentation.EmployeDaoImpl;
import implimentation.MissionDaoImp;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MissionService {

    public void ajouterMission() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Entrez le code de mission : ");
            int code = scanner.nextInt();

            System.out.print("Entrez le nom de la mission : ");
            scanner.nextLine();
            String nom = scanner.nextLine();

            System.out.print("Entrez la description : ");
            String description = scanner.nextLine();

            System.out.print("Entrez l'identifiant de l'employé : ");
            int employeId = scanner.nextInt();

            EmployeDaoImpl emp = new EmployeDaoImpl();
            Optional<Employe> employeOptional = emp.chercherEmploye(employeId);

            if (employeOptional.isPresent()) {
                Mission mission = new Mission(code, nom, description, employeOptional.get());

                MissionDaoImp missionN = new MissionDaoImp();
                Optional<Mission> resultat = missionN.ajouterMission(mission);

                if (resultat.isPresent()) {
                    System.out.println("Mission ajoutée avec succès ! Code : " + resultat.get().getCode());
                } else {
                    System.out.println("Échec de l'ajout de mission.");
                }
            } else {
                System.out.println("L'employé avec l'identifiant spécifié n'existe pas.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur lors de la saisie des données : " + e.getMessage());
        }
    }




    public void supprimer(){
        MissionDaoImp mission = new MissionDaoImp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le code d'operation que vous souhaitez SUPPRIMER :");
        int code = scanner.nextInt();
        int rslt = mission.supprimerMission(code);
        if (rslt != 0){
            System.out.println("L'operation a été supprimé ");
        }else{
            System.out.println("Vueillez valider le code que vous avez entrez");
            supprimer();
        }
    }


    public void afficherList() {
        MissionDaoImp miss = new MissionDaoImp();
        Optional<List<Mission>> toutMission = miss.listerMission();

        if (toutMission.isPresent()) {
            List<Mission> missions = toutMission.get();

            for (Mission mission : missions) {
                System.out.println("-------------------------");
                System.out.println("Code : " + mission.getCode());
                System.out.println("Nom : " + mission.getNom());
                System.out.println("Description : " + mission.getDescription());
                System.out.println("Numero de l'employé : " + mission.getEmploye().getMatricule());
            }
        } else {
            System.out.println("Aucunne Mission");
        }
    }
}
