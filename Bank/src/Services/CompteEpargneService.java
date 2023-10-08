package Services;

import dto.*;
import implimentation.ClientDaoImpl;
import implimentation.CompteEpargneImp;
import implimentation.EmployeDaoImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class CompteEpargneService {

    public CompteEpargne ajouterCompteE() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Numero: ");
        int numero = scanner.nextInt();


        System.out.print("Enter Solde: ");
        double solde = scanner.nextDouble();

        String dateCreationStr = LocalDate.now().format(formatter);
        LocalDate dateCreation = LocalDate.parse(dateCreationStr, formatter);


        String etatStr = "actif";
        Compte.Etat etat = Compte.Etat.valueOf(etatStr);

        System.out.print("Enter Client: ");
        int clientC = scanner.nextInt();
        ClientDaoImpl cl = new ClientDaoImpl();
        Optional<Client> clientOptional = cl.chercherClient(clientC);

        if (!clientOptional.isPresent()) {
            System.out.println("Client n'existe pas.");
            return null;
        }
        Client client = clientOptional.get();

        System.out.print("Enter Employe: ");
        int employeM = scanner.nextInt();
        EmployeDaoImpl emp = new EmployeDaoImpl();
        Optional<Employe> employeOptional = emp.chercherEmploye(employeM);

        if (!employeOptional.isPresent()) {
            System.out.println("Emlpoyé n'existe pas");
            return null;
        }
        Employe employe = employeOptional.get();

        System.out.print("Enter Decouvert: ");
        double tautInteret = scanner.nextDouble();

        try {
            CompteEpargne comE = new CompteEpargne(numero, solde, dateCreation, etat, client, employe, numero, tautInteret);
            CompteEpargneImp compteEparneImp = new CompteEpargneImp();
            Optional<CompteEpargne> resultat = compteEparneImp.ajouterCompteE(comE);

            if (resultat.isPresent()) {
                System.out.println("Compte ajouté avec succès ! Numéro de compte : " + resultat.get().getNumero());
                return resultat.get();
            } else {
                System.out.println("Échec de l'ajout de compte.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la saisie des données : " + e.getMessage());
        }
        return null;
    }



    public void modifierCompteEpargne() {
        CompteEpargneImp comE = new CompteEpargneImp();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le numéro du compte épargne à modifier : ");
        int numeroCompteEpargne = scanner.nextInt();
        CompteEpargne compteEpargne = comE.chercherCompteEpargneParNumero(numeroCompteEpargne);
        if (compteEpargne != null) {
            System.out.print("Entrez le nouveau solde : ");
            double nouveauSolde = scanner.nextDouble();

            System.out.print("Entrez le nouveau taux d'intérêt : ");
            double nouveauTauxInteret = scanner.nextDouble();

            CompteEpargne compteEpargneModifie = new CompteEpargne();

            compteEpargneModifie.setSolde(nouveauSolde);
            compteEpargneModifie.setTauxInteret(nouveauTauxInteret);
            compteEpargneModifie.setNum_compte(numeroCompteEpargne);


            Optional<CompteEpargne> resultat = comE.modifierCompteEpargne(compteEpargneModifie);

            if (resultat.isPresent()) {
                System.out.println("Compte épargne modifié avec succès !");
            } else {
                System.out.println("Échec de la modification du compte épargne.");
            }
        } else {
            System.out.println("Compte épargne non trouvé.");
        }
    }






}
