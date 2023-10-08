package dao;

import dto.Affectation;

import java.util.List;
import java.util.Optional;

public interface AffectationDao {
    Optional<Affectation> ajouterAffectation(Affectation affectation);
    int supprimerAffectation(int mission, int employe);

    Optional<List<Affectation>> affectationEmplye(int matricule);






}
