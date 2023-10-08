import Services.*;
import implimentation.CompteCourantImp;
import implimentation.CompteEpargneImp;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        EmployeService employeService = new EmployeService();
        ClientService clientService = new ClientService();
        CompteService compteService = new CompteService();
        CompteEpargneService compteepargne = new CompteEpargneService();
        CompteCourantService comptecourant = new CompteCourantService();
        OperationService operationService = new OperationService();
        MissionService missionService = new MissionService();
        AffectationService affectationService = new AffectationService();

        while (running) {
            System.out.println("Menu Principal :");
            System.out.println("1. Gestion des Employés");
            System.out.println("2. Gestion des Clients");
            System.out.println("3. Gestion des Comptes");
            System.out.println("4. Gestion des Opérations");
            System.out.println("5. Gestion des Missions");
            System.out.println("6. Gestion des Affectations");
            System.out.println("0. Quitter");
            System.out.print("Entrez votre choix : ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    gestionEmployes(employeService, scanner);
                    break;
                case 2:
                    gestionClients(clientService, scanner);
                    break;
                case 3:
                    gestionComptes(compteService, comptecourant, compteepargne, scanner);
                    break;
                case 4:
                    gestionOperations(operationService, scanner);
                    break;
                case 5:
                    gestionMissions(missionService, scanner);
                    break;
                case 6:
                    gestionAffectations(affectationService, scanner);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez sélectionner une option valide.");
                    break;
            }
        }
    }

    public static void gestionEmployes(EmployeService employeService, Scanner scanner) {
        boolean employesMenu = true;

        while (employesMenu) {
            System.out.println("Gestion des Employés :");
            System.out.println("1. Ajouter un employé");
            System.out.println("2. Supprimer un employé");
            System.out.println("3. Chercher un employé par matricule");
            System.out.println("4. Chercher un employé par telephone");
            System.out.println("5. Modifier un employé");
            System.out.println("6. Afficher touts les employés");
            System.out.println("0. Retour au Menu Principal");
            System.out.print("Entrez votre choix : ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    employeService.ajouterEmploye();
                    break;
                case 2:
                    employeService.supprimerEmploye();
                    break;
                case 3:
                    employeService.chercherEmploye();
                    break;
                case 4:
                    employeService.chercherEmployeparTel();
                    break;
                case 5:
                    employeService.modifierEmploye();
                    break;
                case 6:
                    employeService.afficherList();
                    break;
                case 0:
                    employesMenu = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez sélectionner une option valide.");
                    break;
            }
        }
    }

    public static void gestionClients(ClientService clientService, Scanner scanner) {
        boolean clientMenu = true;

        while (clientMenu) {
            System.out.println("Gestion des Clients :");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Supprimer un client");
            System.out.println("3. Chercher un client par code");
            System.out.println("4. Chercher un client par telephone");
            System.out.println("5. Modifier un client");
            System.out.println("6. Afficher touts les clients");
            System.out.println("0. Retour au Menu Principal");
            System.out.print("Entrez votre choix : ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    clientService.ajouterClient();
                    break;
                case 2:
                    clientService.supprimerClient();
                    break;
                case 3:
                    clientService.chercherClient();
                    break;
                case 4:
                    clientService.chercherClientTel();
                    break;
                case 5:
                    clientService.modifierClient();
                    break;
                case 6:
                    clientService.afficherList();
                    break;
                case 0:
                    clientMenu = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez sélectionner une option valide.");
                    break;
            }
        }
    }

    public static void gestionComptes(CompteService compteService, CompteCourantService comptecourantservice, CompteEpargneService compteepargneservice, Scanner scanner) {
        boolean compteMenu = true;

        while (compteMenu) {
            System.out.println("Gestion des comptes :");
            System.out.println("1. Ajouter un compte courant");
            System.out.println("2. Ajouter un compte epargne");
            System.out.println("3. Supprimer un compte");
            System.out.println("4. Chercher un compte par client");
            System.out.println("5. changer le statut de compte");
            System.out.println("6. Afficher touts les comptes");
            System.out.println("7. Afficher les comptes par statuts");
            System.out.println("8. Afficher les comptes par date de création");
            System.out.println("9. Modifier un compte epargne");
            System.out.println("10. Modifier un compte courant");
            System.out.println("11. Chercher compte par opération");
            System.out.println("0. Retour au Menu Principal");
            System.out.print("Entrez votre choix : ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    comptecourantservice.ajouterCompteC();
                    break;
                case 2:
                    compteepargneservice.ajouterCompteE();
                    break;
                case 3:
                    compteService.supprimerCompte();
                    break;
                case 4:
                    compteService.chercherToutCompte();
                    break;
                case 5:
                    compteService.modifierEtat();
                    break;
                case 6:
                    compteService.listerCompte();
                    break;
                case 7:
                    compteService.ListerParStatut();
                    break;
                case 8:
                    compteService.ListerParDateCreation();
                    break;
                case 9:
                    compteepargneservice.modifierCompteEpargne();
                    break;
                case 10:
                    comptecourantservice.modifierCompteCourant();
                    break;
                case 11:
                    compteService.chercherCompteparOp();
                    break;
                case 0:
                    compteMenu = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez sélectionner une option valide.");
                    break;
            }
        }
    }

    public static void gestionOperations(OperationService operationService, Scanner scanner) {
        boolean opMenu = true;

        while (opMenu) {
            System.out.println("Gestion des Operations :");
            System.out.println("1. Effectuer un virement");
            System.out.println("2. Effectuer un retrait");
            System.out.println("3. Effectuer un versement");
            System.out.println("4. Supprimer une opération");
            System.out.println("5. Chercher une opération par numéro");
            System.out.println("0. Retour au Menu Principal");
            System.out.print("Entrez votre choix : ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    operationService.virement();
                    break;
                case 2:
                    operationService.retrait();
                    break;
                case 3:
                    operationService.versement();
                    break;
                case 4:
                    operationService.supprimer();
                    break;
                case 5:
                    operationService.chercherOperation();
                    break;
                case 0:
                    opMenu = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez sélectionner une option valide.");
                    break;
            }
        }    }

    public static void gestionMissions(MissionService missionService, Scanner scanner) {
        boolean missionMenu = true;

        while (missionMenu) {
            System.out.println("Gestion des missions :");
            System.out.println("1. Ajouter une mission");
            System.out.println("2. Supprimer une mission");
            System.out.println("3. Afficher list des missions");
            System.out.println("0. Retour au Menu Principal");
            System.out.print("Entrez votre choix : ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    missionService.ajouterMission();
                    break;
                case 2:
                    missionService.supprimer();
                    break;
                case 3:
                    missionService.afficherList();
                    break;
                case 0:
                    missionMenu = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez sélectionner une option valide.");
                    break;
            }
        }
    }

    public static void gestionAffectations(AffectationService affectationService, Scanner scanner) {
        boolean affectMenu = true;

        while (affectMenu) {
            System.out.println("Gestion des missions :");
            System.out.println("1. Créer une affectation");
            System.out.println("2. Supprimer une affectation");
            System.out.println("3. Afficher l'historique des affectations d'un employé");
            System.out.println("4. Afficher les statistiques des affactations");
            System.out.println("0. Retour au Menu Principal");
            System.out.print("Entrez votre choix : ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    affectationService.ajouterAffectation();
                    break;
                case 2:
                    affectationService.supprimer();
                    break;
                case 3:
                    affectationService.affectationEmploye();
                    break;
                case 4:
                    affectationService.afficherStatistiques();
                    break;
                case 0:
                    affectMenu = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez sélectionner une option valide.");
                    break;
            }
        }
    }

}






