package Services;

import dto.Client;
import dto.Employe;
import implimentation.ClientDaoImpl;
import implimentation.EmployeDaoImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientService {

    public void ajouterClient() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Veuillez saisir les informations du client :");

        System.out.print("Code du client : ");
        int code = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
        String dateNaissanceStr = scanner.nextLine();
        LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr, formatter);
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();
        System.out.print("Adresse : ");
        String adress = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();

        try {

            Client client = new Client(nom, prenom, dateNaissance, telephone, adress, code, email);
            ClientDaoImpl clientDao = new ClientDaoImpl();
            Optional<Client> resultat = clientDao.ajouterClient(client);

            if (resultat.isPresent()) {
                System.out.println("Client ajouté avec succès ! Code : " + resultat.get().getCode());
            } else {
                System.out.println("Échec de l'ajout de client.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la saisie des données : " + e.getMessage());
        }

    }


    public void supprimerClient(){
        ClientDaoImpl client = new ClientDaoImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le code de client que vous souhaitez SUPPRIMER :");
        int code = scanner.nextInt();
        int rslt = client.supprimerClient(code);
        if (rslt != 0){
            System.out.println("L'employee a été supprimé ");
        }else{
            System.out.println("Vueillez valider le code que vous avez entrez");
            supprimerClient();
        }

    }





    public void chercherClient(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le code de client à rechercher : ");
        int code = scanner.nextInt();

        ClientDaoImpl clt = new ClientDaoImpl();
        Optional<Client> cltTrouve = clt.chercherClient(code);

        if (cltTrouve.isPresent()) {
            Client client = cltTrouve.get();
            System.out.println("Employé trouvé :");
            System.out.println("Code : " + client.getCode());
            System.out.println("Nom : " + client.getNom());
            System.out.println("Prénom : " + client.getPrenom());
            System.out.println("Date de naissance : " + client.getDateNaissance());
            System.out.println("Telephone : " + client.getTelephone());

        } else {
            System.out.println("Aucun client trouvé avec le code " + code);
        }
    }

    public void afficherList() {
        ClientDaoImpl emp = new ClientDaoImpl();
        Optional<List<Client>> toutCli = emp.listClient();

        if (toutCli.isPresent()) {
            List<Client> clients = toutCli.get();

            for (Client client : clients) {
                System.out.println("-------------------------");
                System.out.println("Code : " + client.getCode());
                System.out.println("Nom : " + client.getNom());
                System.out.println("Prénom : " + client.getPrenom());
                System.out.println("Date de naissance : " + client.getDateNaissance());
                System.out.println("Telephone : " + client.getTelephone());
            }
        } else {
            System.out.println("Aucun client trouvé");
        }
    }


    public void chercherClientTel(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le telephone de client à rechercher : ");
        String telephone = scanner.nextLine();

        ClientDaoImpl clt = new ClientDaoImpl();
        Optional<Client> cltTrouve = clt.chercherClientparTel(telephone);

        if (cltTrouve.isPresent()) {
            Client client = cltTrouve.get();
            System.out.println("Employé trouvé :");
            System.out.println("Code : " + client.getCode());
            System.out.println("Nom : " + client.getNom());
            System.out.println("Prénom : " + client.getPrenom());
            System.out.println("Date de naissance : " + client.getDateNaissance());
            System.out.println("Telephone : " + client.getTelephone());

        } else {
            System.out.println("Aucun client trouvé avec le code " + telephone);
        }
    }

    public void modifierClient() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Modification d'un client :");

        System.out.print("Code du client à modifier : ");
        int code = scanner.nextInt();
        scanner.nextLine();

        ClientDaoImpl clientDao = new ClientDaoImpl();
        Optional<Client> clientExistant = clientDao.chercherClient(code);

        if (clientExistant.isPresent()) {
            Client client = clientExistant.get();

            System.out.print("Nouveau prénom (laissez vide pour ne pas changer) : ");
            String nouveauPrenom = scanner.nextLine();
            if (!nouveauPrenom.isEmpty()) {
                client.setPrenom(nouveauPrenom);
            }

            System.out.print("Nouveau Email (laissez vide pour ne pas changer) : ");
            String nouveauEmail = scanner.nextLine();
            if (!nouveauEmail.isEmpty()) {
                client.setEmail(nouveauEmail);
            }

            System.out.print("Nouveau Telephone (laissez vide pour ne pas changer) : ");
            String nouveauTelephone = scanner.nextLine();
            if (!nouveauTelephone.isEmpty()) {
                client.setTelephone(nouveauTelephone);
            }

            System.out.print("Nouveau Adress (laissez vide pour ne pas changer) : ");
            String nouveauAdress= scanner.nextLine();
            if (!nouveauAdress.isEmpty()) {
                client.setAdress(nouveauAdress);
            }

            System.out.print("Nouveau nom (laissez vide pour ne pas changer) : ");
            String nouveauNom = scanner.nextLine();
            if (!nouveauNom.isEmpty()) {
                client.setNom(nouveauNom);
            }

            System.out.print("Nouvelle date de naissance (AAAA-MM-JJ, laissez vide pour ne pas changer) : ");
            String nouvelleDateNaissanceStr = scanner.nextLine();
            if (!nouvelleDateNaissanceStr.isEmpty()) {
                LocalDate nouvelleDateNaissance = LocalDate.parse(nouvelleDateNaissanceStr, formatter);
                client.setDateNaissance(nouvelleDateNaissance);
            }

            Optional<Client> resultat = clientDao.modifierClient(client);

            if (resultat.isPresent()) {
                System.out.println("Client modifié avec succès !");
            } else {
                System.out.println("Échec de la modification du client.");
            }
        } else {
            System.out.println("Le client avec le code spécifié n'existe pas.");
        }
    }


}
