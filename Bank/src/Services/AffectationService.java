package Services;

import dto.Affectation;
import dto.Client;
import dto.Employe;
import dto.Mission;
import implimentation.AffectaionDaoImp;
import implimentation.ClientDaoImpl;
import implimentation.EmployeDaoImpl;
import implimentation.MissionDaoImp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AffectationService {
    public void ajouterAffectation(){
        Scanner scanner = new Scanner(System.in);

        LocalDate dateDebut = LocalDate.now();

        System.out.print("Entrez la date de fin (au format yyyy-MM-dd) : ");
        String dateFinStr = scanner.nextLine();
        LocalDate dateFin = LocalDate.parse(dateFinStr);

        System.out.print("Entrez le code de la mission : ");
        int missionCode = scanner.nextInt();
        MissionDaoImp miss = new MissionDaoImp();
        Optional<Mission> missionOptionnal = miss.chercherMissionParCode(missionCode);

        System.out.print("Entrez le matricule de l'employé : ");
        int employeMatricule = scanner.nextInt();
        EmployeDaoImpl emp = new EmployeDaoImpl();
        Optional<Employe> employeOptional = emp.chercherEmploye(employeMatricule);


        try {

            Affectation affectation = new Affectation(dateDebut, dateFin, missionOptionnal.get(), employeOptional.get());
            AffectaionDaoImp affect = new AffectaionDaoImp();

            Optional<Affectation> resultat = affect.ajouterAffectation(affectation);

            if (resultat.isPresent()) {
                System.out.println("Affectation ajouté avec succès ! : ");
            } else {
                System.out.println("Échec de l'ajout d'affectation.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la saisie des données : " + e.getMessage());
        }

    }


    public void supprimer(){

        AffectaionDaoImp aff = new AffectaionDaoImp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le code de la mission :");
        int mission = scanner.nextInt();

        System.out.println("Entrez le matricule de client :");
        int employe = scanner.nextInt();


        int rslt = aff.supprimerAffectation(mission, employe);
        if (rslt != 0){
            System.out.println("L'affectation a été supprimée ");
        }else{
            System.out.println("L'affectation n'est pas supprimée");
            supprimer();
        }

    }


    public void affectationEmploye(){
        AffectaionDaoImp aff = new AffectaionDaoImp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le matricule de l'employe :");
        int matricule = scanner.nextInt();

        Optional<List<Affectation>> affectations = aff.affectationEmplye(matricule);

        if(affectations.isPresent()){
            List<Affectation> toutAffectation = affectations.get();
            for (Affectation affectation : toutAffectation) {
                System.out.println("-------------------------");
                System.out.println("Nom d'employé : " + affectation.getEmploye().getNom());
                System.out.println("Nom de mission : " + affectation.getMission().getNom());
                System.out.println("Description de mission : " + affectation.getMission().getDescription());
                System.out.println("Date de debut : " + affectation.getDateDebut());
                System.out.println("Date de fin : " + affectation.getDateFin());

            }
        } else {
            System.out.println("Aucunne affectation trouvée");
        }
        }



    public void afficherStatistiques() {
        AffectaionDaoImp aff = new AffectaionDaoImp();
        int[] statistiques = aff.statistiquesAffectations();

        if (statistiques != null && statistiques.length == 3) {
            int totalAffectations = statistiques[0];
            int totalEmployes = statistiques[1];
            int totalMissions = statistiques[2];

            System.out.println("Statistiques des affectations :");
            System.out.println("Nombre total d'affectations : " + totalAffectations);
            System.out.println("Nombre total d'employés distincts : " + totalEmployes);
            System.out.println("Nombre total de missions distinctes : " + totalMissions);
        } else {
            System.out.println("Erreur lors de la récupération des statistiques.");
        }
    }





}

