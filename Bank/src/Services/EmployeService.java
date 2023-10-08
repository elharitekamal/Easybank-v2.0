package Services;

import dto.Employe;
import dto.Operation;
import implimentation.EmployeDaoImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeService {
    public void ajouterEmploye() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ajout d'un employé :");
        System.out.print("Matricule : ");
        int matricule = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
        String dateNaissanceStr = scanner.nextLine();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Adresse : ");
        String adress = scanner.nextLine();
        System.out.print("Date de recrutement (AAAA-MM-JJ) : ");
        String dateRecrutementStr = scanner.nextLine();

        LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr, formatter);
        LocalDate dateRecrutement = LocalDate.parse(dateRecrutementStr, formatter);

        try {

            Employe employe = new Employe( nom,  prenom,  dateNaissance,  telephone,  adress,  matricule,  dateRecrutement, email);
            EmployeDaoImpl employeDao = new EmployeDaoImpl();
            Optional<Employe> resultat = employeDao.ajouterEmploye(employe);

            if (resultat.isPresent()) {
                System.out.println("Employé ajouté avec succès ! Matricule : " + resultat.get().getMatricule());
            } else {
                System.out.println("Échec de l'ajout de l'employé.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la saisie des données : " + e.getMessage());
        }
    }



    public void supprimerEmploye(){
        EmployeDaoImpl employe = new EmployeDaoImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le matricule d'employée que vous souhaitez SUPPRIMER :");
        int matricule = scanner.nextInt();
        int rslt = employe.supprimerEmploye(matricule);
        if (rslt != 0){
            System.out.println("L'employee a été supprimé ");
        }else{
            System.out.println("Vueillez valider le matricule que vous avez entrez");
            supprimerEmploye();
        }

    }



    public void chercherEmploye(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le matricule de l'employé à rechercher : ");
        int matricule = scanner.nextInt();

        EmployeDaoImpl emp = new EmployeDaoImpl();
        Optional<Employe> empTrouve = emp.chercherEmploye(matricule);

        if (empTrouve.isPresent()) {
            Employe employe = empTrouve.get();
            System.out.println("Employé trouvé :");
            System.out.println("Matricule : " + employe.getMatricule());
            System.out.println("Nom : " + employe.getNom());
            System.out.println("Prénom : " + employe.getPrenom());
            System.out.println("Date de naissance : " + employe.getDateNaissance());
            System.out.println("Date de rectrutement : " + employe.getDateRecrutement());
            System.out.println("Email : " + employe.getEmail());
            System.out.println("Telephone : " + employe.getTelephone());

        } else {
            System.out.println("Aucun employé trouvé avec le matricule " + matricule);
        }
    }


    public void afficherList() {
        EmployeDaoImpl emp = new EmployeDaoImpl();
        Optional<List<Employe>> toutEmp = emp.listEmploye();

        if (toutEmp.isPresent()) {
            List<Employe> employes = toutEmp.get();

            for (Employe employe : employes) {
                System.out.println("-------------------------");
                System.out.println("Matricule : " + employe.getMatricule());
                System.out.println("Nom : " + employe.getNom());
                System.out.println("Prénom : " + employe.getPrenom());
                System.out.println("Date de naissance : " + employe.getDateNaissance());
                System.out.println("Date de recrutement : " + employe.getDateRecrutement());
                System.out.println("Email : " + employe.getEmail());
                System.out.println("Telephone : " + employe.getTelephone());
            }
        } else {
            System.out.println("Aucun employé trouvé");
        }
    }


    public void chercherEmployeparTel(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le numéro de l'employé à rechercher : ");
        String telephone = scanner.nextLine();

        EmployeDaoImpl emp = new EmployeDaoImpl();
        Optional<Employe> empTrouve = emp.chercherEmployeTel(telephone);

        if (empTrouve.isPresent()) {
            Employe employe = empTrouve.get();
            System.out.println("Employé trouvé :");
            System.out.println("Matricule : " + employe.getMatricule());
            System.out.println("Nom : " + employe.getNom());
            System.out.println("Prénom : " + employe.getPrenom());
            System.out.println("Date de naissance : " + employe.getDateNaissance());
            System.out.println("Date de rectrutement : " + employe.getDateRecrutement());
            System.out.println("Email : " + employe.getEmail());
            System.out.println("Telephone : " + employe.getTelephone());

        } else {
            System.out.println("Aucun employé trouvé avec le matricule " + telephone);
        }
    }


    public void modifierEmploye() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Modification d'un employé :");
        System.out.print("Matricule de l'employé à modifier : ");
        int matricule = scanner.nextInt();
        scanner.nextLine();

        EmployeDaoImpl employeDao = new EmployeDaoImpl();
        Optional<Employe> employeExistant = employeDao.chercherEmploye(matricule);

        if (employeExistant.isPresent()) {
            Employe employe = employeExistant.get();

            System.out.print("Nouveau nom (laissez vide pour ne pas changer) : ");
            String nouveauNom = scanner.nextLine();
            if (!nouveauNom.isEmpty()) {
                employe.setNom(nouveauNom);
            }

            System.out.print("Nouveau prénom (laissez vide pour ne pas changer) : ");
            String nouveauPrenom = scanner.nextLine();
            if (!nouveauPrenom.isEmpty()) {
                employe.setPrenom(nouveauPrenom);
            }

            System.out.print("Nouveau Téléphpone (laissez vide pour ne pas changer) : ");
            String nouveauTelephone = scanner.nextLine();
            if (!nouveauTelephone.isEmpty()) {
                employe.setTelephone(nouveauTelephone);
            }

            System.out.print("Nouveau Téléphpone (laissez vide pour ne pas changer) : ");
            String nouveauAdress = scanner.nextLine();
            if (!nouveauAdress.isEmpty()) {
                employe.setAdress(nouveauAdress);
            }

            System.out.print("Nouvelle date de naissance (AAAA-MM-JJ, laissez vide pour ne pas changer) : ");
            String nouvelleDateNaissanceStr = scanner.nextLine();
            if (!nouvelleDateNaissanceStr.isEmpty()) {
                LocalDate nouvelleDateNaissance = LocalDate.parse(nouvelleDateNaissanceStr);
                employe.setDateNaissance(nouvelleDateNaissance);
            }


            Optional<Employe> resultat = employeDao.modifierEmploye(employe);

            if (resultat.isPresent()) {
                System.out.println("Employé modifié avec succès !");
            } else {
                System.out.println("Échec de la modification de l'employé.");
            }
        } else {
            System.out.println("L'employé avec le matricule spécifié n'existe pas.");
        }
    }


}






