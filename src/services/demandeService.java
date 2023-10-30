package services;

import DAO.ImpAgence;
import DAO.ImpDemande;
import DTO.Agence;
import DTO.Demande;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.util.Optional;

public class demandeService {
    ImpDemande impDemande;
    public demandeService(ImpDemande impDemande){
        this.impDemande = impDemande;
    }

    public boolean ajouter(Demande demande) {
        Optional<Demande> optdemande=impDemande.ajouter(demande);
        return optdemande.isPresent();
    }

}
