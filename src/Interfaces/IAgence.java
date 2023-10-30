package Interfaces;

import DTO.Agence;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IAgence  {

    Optional<Agence> cherchebyAdresse(String adresse);
    HashMap<String,String> afficherContact();

    Optional<Agence> ajouter(Agence agence);

    int supprimer(Integer id);

    Optional<Agence> update(Agence agence);
    Optional<Agence> cherchebyId(Integer id);
    List<Agence> afficher();

}
