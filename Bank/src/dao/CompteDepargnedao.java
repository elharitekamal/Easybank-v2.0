package dao;

import dto.Client;
import dto.CompteCourant;
import dto.CompteEpargne;

import java.util.Optional;

public interface CompteDepargnedao {


    Optional<CompteEpargne> ajouterCompteE(CompteEpargne compteEparn);
    CompteEpargne chercherCompteEpargneParNumero(int numeroCompte);
    Optional<CompteEpargne> modifierCompteEpargne(dto.CompteEpargne compteEpargneModifie);


}
