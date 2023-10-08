package dao;

import dto.Compte;
import dto.Employe;
import dto.Operation;

import java.util.Optional;

public interface Operationdao {
    Optional<Operation> effectuerRetrait(Compte numero, double montant, Employe employe);

    Optional<Operation>  effectuerVirement(Compte compteSource, Compte compteDestinataire, double montant, Employe employe);

    Optional<Operation>  effectuerVersement(Compte numero, double montant, Employe employe);


    int supprimerOperation(int numero);

    Optional<Operation> chercherOperationParNumero(int matricule);



}
