package Services;

import dto.Client;
import dto.Compte;
import dto.CompteCourant;
import dto.Employe;
import implimentation.ClientDaoImpl;
import implimentation.CompteCourantImp;
import implimentation.CompteImp;
import implimentation.EmployeDaoImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;


public class CompteCourantService {
    public CompteCourant ajouterCompteC() {
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
            System.out.println("Client not found.");
            return null;
        }
        Client client = clientOptional.get();

        System.out.print("Enter Employe: ");
        int employeM = scanner.nextInt();
        EmployeDaoImpl emp = new EmployeDaoImpl();
        Optional<Employe> employeOptional = emp.chercherEmploye(employeM);

        if (!employeOptional.isPresent()) {
            System.out.println("Employe not found.");
            return null;
        }
        Employe employe = employeOptional.get();

        System.out.print("Enter Decouvert: ");
        double decouvert = scanner.nextDouble();

        try {
            CompteCourant comC = new CompteCourant(numero, solde, dateCreation, etat, client, employe, numero, decouvert);
            CompteCourantImp compteCourantImp = new CompteCourantImp();
            Optional<CompteCourant> resultat = compteCourantImp.ajouterCompteC(comC);

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




    public void modifierCompteCourant() {
        CompteCourantImp comC = new CompteCourantImp();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le numéro du compte courant à modifier : ");
        int numeroCompteCourant = scanner.nextInt();
        CompteCourant compteCourant = comC.chercherCompteC(numeroCompteCourant);

        if (compteCourant != null) {
            System.out.print("Entrez le nouveau solde : ");
            double nouveauSolde = scanner.nextDouble();

            System.out.print("Entrez le nouveau découvert : ");
            double nouveauDecouvert = scanner.nextDouble();

            // Mettez à jour les données du compte courant
            compteCourant.setSolde(nouveauSolde);
            compteCourant.setDecouvert(nouveauDecouvert);

            // Appelez la méthode pour modifier le compte courant
            Optional<CompteCourant> resultat = comC.modifierCompteCourant(compteCourant);

            if (resultat.isPresent()) {
                System.out.println("Compte courant modifié avec succès !");
            } else {
                System.out.println("Échec de la modification du compte courant.");
            }
        } else {
            System.out.println("Compte courant non trouvé.");
        }
    }

}




