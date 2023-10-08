package Services;

import dto.*;
import implimentation.ClientDaoImpl;
import implimentation.CompteImp;
import implimentation.EmployeDaoImpl;

import java.util.Comparator;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CompteService {

    public void chercherToutCompte(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le code client pour rechercher le compte : ");
        int client = scanner.nextInt();

        CompteImp cnt = new CompteImp();
        Optional<List<Compte>> comptesOption  = cnt.chercherCompte(client);

        if (comptesOption.isPresent()) {
            List<Compte> comptes = comptesOption.get();

            for (Compte compte : comptes) {
                System.out.println("Numéro de compte : " + compte.getNumero());
                System.out.println("Solde : " + compte.getSolde());
                System.out.println("Date de création : " + compte.getDateCreation());
                System.out.println("Etat : " + compte.getEtat());

                if (compte instanceof CompteCourant) {
                    CompteCourant compteCourant = (CompteCourant) compte;
                    System.out.println("Découvert : " + compteCourant.getDecouvert());
                } else if (compte instanceof CompteEpargne) {
                    CompteEpargne compteEpargne = (CompteEpargne) compte;
                    System.out.println("Taux d'Intérêt : " + compteEpargne.getTauxInteret());
                }

                System.out.println();
            }
        } else {
            System.out.println("Aucun compte trouvé pour le client " + client);
        }

    }


    public void modifierEtat(){
        CompteImp compteDao = new CompteImp();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le numero de compte : ");
        int numero = scanner.nextInt();

        System.out.print("Entrez l'etat de compte : ");
        System.out.print("Pour actif Taper 1 : ");
        System.out.print("Pour inactif Taper 2: ");
        int choix = scanner.nextInt();
        Compte.Etat etat ;
        if(choix==1){
             etat = Compte.Etat.actif;
        }else{
             etat = Compte.Etat.inactif;
        }

        boolean success =  compteDao.modifierEtat(numero, etat);

        if (success) {
            System.out.println("L'état du compte a été modifié avec succès !");
        } else {
            System.out.println("Impossible de modifier l'état du compte.");
        }
    }



    public void  listerCompte(){
        CompteImp cnt = new CompteImp();
        Optional<List<Compte>> comptesOption  = cnt.listComptes();

        if (comptesOption.isPresent()) {
            List<Compte> comptes = comptesOption.get();

            for (Compte compte : comptes) {
                System.out.println("Numéro de compte : " + compte.getNumero());
                System.out.println("Solde : " + compte.getSolde());
                System.out.println("Date de création : " + compte.getDateCreation());
                System.out.println("Etat : " + compte.getEtat());

                if (compte instanceof CompteCourant) {
                    CompteCourant compteCourant = (CompteCourant) compte;
                    System.out.println("Découvert : " + compteCourant.getDecouvert());
                } else if (compte instanceof CompteEpargne) {
                    CompteEpargne compteEpargne = (CompteEpargne) compte;
                    System.out.println("Taux d'Intérêt : " + compteEpargne.getTauxInteret());
                }

                System.out.println();
            }
        } else {
            System.out.println("Aucun compte trouvé ");
        }
    }


    public void ListerParStatut() {
        Scanner scanner = new Scanner(System.in);
        CompteImp toutCompte = new CompteImp();
        Optional<List<Compte>> listeDeComptes = toutCompte.listComptes();
        
        System.out.print("Entrez l'etat des comptes à lister : ");
        System.out.print("Pour actif Taper 1 : ");
        System.out.print("Pour inactif Taper 2: ");
        int choix = scanner.nextInt();

        Compte.Etat etatRecherche;
        if(choix==1) {
            etatRecherche = Compte.Etat.actif;
        }else{
            etatRecherche = Compte.Etat.inactif;

        }
        listeDeComptes.ifPresent(comptes -> {
            comptes.stream()
                    .filter(compte -> compte.getEtat() == etatRecherche)
                    .forEach(compte -> {
                        System.out.println("Numéro de compte : " + compte.getNumero());
                        System.out.println("Solde : " + compte.getSolde());
                        System.out.println("Date de création : " + compte.getDateCreation());
                        System.out.println("Etat : " + compte.getEtat());

                        if (compte instanceof CompteCourant) {
                            CompteCourant compteCourant = (CompteCourant) compte;
                            System.out.println("Découvert : " + compteCourant.getDecouvert());
                        } else if (compte instanceof CompteEpargne) {
                            CompteEpargne compteEpargne = (CompteEpargne) compte;
                            System.out.println("Taux d'Intérêt : " + compteEpargne.getTauxInteret());
                        }
                        System.out.println();
                    });
        });
    }


    public void supprimerCompte(){
        CompteImp cnt = new CompteImp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le numero de compte que vous souhaitez SUPPRIMER :");
        int numero = scanner.nextInt();
        int rslt = cnt.supprimerCompte(numero);
        if (rslt != 0){
            System.out.println("Le compte a été supprimé ");
        }else{
            System.out.println("Vueillez valider le numero que vous avez entrez");
            supprimerCompte();
        }

    }




    public void ListerParDateCreation() {
        CompteImp toutCompte = new CompteImp();
        Optional<List<Compte>> listeDeComptes = toutCompte.listComptes();

        if (!listeDeComptes.isPresent()) {
            System.out.println("La liste de comptes est vide.");
            return;
        }

        listeDeComptes.ifPresent(comptes -> {
            comptes.sort(Comparator.comparing(Compte::getDateCreation));

            System.out.println("Liste des comptes triés par date de création :");
            comptes.forEach(compte -> {
                System.out.println("Numéro de compte : " + compte.getNumero());
                System.out.println("Solde : " + compte.getSolde());
                System.out.println("Date de création : " + compte.getDateCreation());
                System.out.println("État : " + compte.getEtat());

                if (compte instanceof CompteCourant) {
                    CompteCourant compteCourant = (CompteCourant) compte;
                    System.out.println("Découvert : " + compteCourant.getDecouvert());
                } else if (compte instanceof CompteEpargne) {
                    CompteEpargne compteEpargne = (CompteEpargne) compte;
                    System.out.println("Taux d'Intérêt : " + compteEpargne.getTauxInteret());
                }

                System.out.println();
            });
        });
    }

    public void chercherCompteparOp() {
        CompteImp compte = new CompteImp();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Tapez le numéro de l'opération pour chercher le(s) compte(s) : ");
        int numeroOp = scanner.nextInt();

        int[] numerosComptes = compte.afficherCompteparOp(numeroOp);

        if (numerosComptes[0] != 0) {
            for (int numeroCompte : numerosComptes) {
                Optional<Compte> compteOptional = compte.chercherCompteparNum(numeroCompte);
                if (compteOptional.isPresent()) {
                    Compte cnt = compteOptional.get();
                    System.out.println("Numéro de compte : " + cnt.getNumero());
                    System.out.println("Solde : " + cnt.getSolde());
                    System.out.println("Date de création : " + cnt.getDateCreation());
                    System.out.println("Etat : " + cnt.getEtat());

                    if (cnt instanceof CompteCourant) {
                        CompteCourant compteCourant = (CompteCourant) cnt;
                        System.out.println("Découvert : " + compteCourant.getDecouvert());
                    } else if (cnt instanceof CompteEpargne) {
                        CompteEpargne compteEpargne = (CompteEpargne) cnt;
                        System.out.println("Taux d'Intérêt : " + compteEpargne.getTauxInteret());
                    }

                    System.out.println();
                }
            }
        } else {
            System.out.println("Aucun compte trouvé pour l'opération spécifiée.");
        }
    }


}
