package dao;

import dto.Compte;
import dto.Employe;

import java.util.List;
import java.util.Optional;

public interface Comptedao {

    Optional<List<Compte>> chercherCompte(int client);
    Optional<Compte> chercherCompteparNum(int numero);
    int supprimerCompte(int numero);
    int[] afficherCompteparOp(int numeroOp);
    boolean modifierEtat(int numero, Compte.Etat nouvelEtat);








}
